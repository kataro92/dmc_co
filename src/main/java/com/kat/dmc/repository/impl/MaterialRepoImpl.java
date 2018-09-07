package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.MaterialDto;
import com.kat.dmc.common.dto.MaterialImportDetailDto;
import com.kat.dmc.common.model.*;
import com.kat.dmc.repository.interfaces.MaterialExportDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialImportDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MaterialRepoImpl implements MaterialRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    MaterialImportDetailRepo importDetailRepo;

    @Autowired
    MaterialExportDetailRepo exportDetailRepo;
    
    @Override
    public List<MaterialEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(MaterialEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<MaterialEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(MaterialEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(MaterialEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public MaterialEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<MaterialEntity> findBySubgroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.materialSubgroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public MaterialEntity findByCode(String code) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.code), code));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<MaterialDto> findAllByImport() {
        String strSQL = "SELECT MS._id, " +
                " MS.code, " +
                " MS.material_group_code, " +
                " MS.material_subgroup_code, " +
                " MS.name, " +
                " MS.status " +
                " FROM material MS " +
                " WHERE MS.status=0 " +
                " AND MS._id IN (SELECT SUMMARY.material_id " +
                "FROM ( " +
                "  SELECT " +
                "    MID.material_id, " +
                "    MID.quantity " +
                "  FROM dmc_material_import_detail MID " +
                "  WHERE MID.status = 0 " +
                "  UNION ALL " +
                "  SELECT " +
                "    MED.material_id, " +
                "    -MED.quantity " +
                "  FROM dmc_material_export_detail MED " +
                "  WHERE MED.status = 0 " +
                ") AS SUMMARY " +
                "GROUP BY SUMMARY.material_id " +
                "HAVING sum(SUMMARY.quantity) > 0)";
        Query query = entityManager.createNativeQuery(strSQL);
        List<Object[]> lstResult = query.getResultList();
        List<MaterialDto> lstReturn = new ArrayList<>();
        for(Object[] objects : lstResult){
            lstReturn.add(object2Dto(objects));
        }
        return lstReturn;
    }

    @Override
    public List<MaterialImportDetailDto> findImpIdsByMaterialId(int id) {
        List<MaterialImportDetailDto> detailDtoList = new ArrayList<>();
        try {
            //Get all import
            List<DmcMaterialImportDetailEntity> importDetailEntities = importDetailRepo.findByIdMaterialId(id);
            //Get all export
            List<DmcMaterialExportDetailEntity> exportDetailEntities = exportDetailRepo.findByIdMaterialId(id);
            //TO-DO check date already OK for import
            for (DmcMaterialImportDetailEntity detailEntity : importDetailEntities) {
                detailDtoList.addAll(exactImportByExport(detailEntity, exportDetailEntities));
            }

            for (MaterialImportDetailDto importDetailDto : detailDtoList) {
                MaterialEntity materialEntity = findById(importDetailDto.getMaterialId());
                importDetailDto.setUnit(materialEntity.getUnit());
            }
        }catch (NoResultException ex){
            System.out.println(ex.getMessage());
        }
        return detailDtoList;
    }

    private List<MaterialImportDetailDto> exactImportByExport(DmcMaterialImportDetailEntity detailEntity,
                                     List<DmcMaterialExportDetailEntity> exportDetailEntities){
        List<MaterialImportDetailDto> detailDtos = new ArrayList<>();
        if(exportDetailEntities == null || exportDetailEntities.isEmpty()){

            MaterialImportDetailDto detailDto = new MaterialImportDetailDto();
            detailDto.setQuantity(detailEntity.getQuantity());
            detailDto.setPrice(detailEntity.getPrice());
            detailDto.setCode(detailEntity.getCode());
            detailDtos.add(detailDto);

        }
        for(DmcMaterialExportDetailEntity exportDetailEntity : exportDetailEntities){
            if(exportDetailEntity.getQuantity() >= detailEntity.getQuantity()){
                exportDetailEntity.setQuantity(exportDetailEntity.getQuantity() - detailEntity.getQuantity());

                MaterialImportDetailDto detailDto = new MaterialImportDetailDto();
                detailDto.setQuantity(detailEntity.getQuantity());
                detailDto.setPrice(detailEntity.getPrice());
                detailDto.setCode(detailEntity.getCode());
                detailDtos.add(detailDto);

                detailEntity.setQuantity(0);
                return detailDtos;
            }else if(detailEntity.getQuantity() != 0){

                MaterialImportDetailDto detailDto = new MaterialImportDetailDto();
                detailDto.setQuantity(detailEntity.getQuantity());
                detailDto.setPrice(detailEntity.getPrice());
                detailDto.setCode(detailEntity.getCode());
                detailDtos.add(detailDto);

                exportDetailEntity.setQuantity(0);
                detailEntity.setQuantity(detailEntity.getQuantity() - exportDetailEntity.getQuantity());
            }
        }
        return detailDtos;
    }

    private MaterialDto object2Dto(Object[] objects) {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId((Integer) objects[0]);
        materialDto.setCode((String) objects[1]);
        materialDto.setMaterialGroupCode((String) objects[2]);
        materialDto.setMaterialSubgroupCode((String) objects[3]);
        materialDto.setName((String) objects[4]);
        materialDto.setStatus((Integer) objects[5]);
//        materialDto.setCurrentImportId((Integer) objects[6]);
//        materialDto.setCurrentImportCode((String) objects[7]);
//        materialDto.setCurrentPrice((Integer) objects[8]);
        return materialDto;
    }
}
