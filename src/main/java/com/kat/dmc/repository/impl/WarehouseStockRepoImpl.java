package com.kat.dmc.repository.impl;

import com.kat.dmc.common.constant.DateConst;
import com.kat.dmc.common.dto.WarehouseStockDto;
import com.kat.dmc.common.model.DmcWarehouseStockEntity;
import com.kat.dmc.common.model.DmcWarehouseStockEntity_;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.repository.interfaces.WarehouseStockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

@Repository
public class WarehouseStockRepoImpl implements WarehouseStockRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;

    @Override
    public List<DmcWarehouseStockEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseStockEntity> criteriaQuery = builder.createQuery(DmcWarehouseStockEntity.class);
        Root<DmcWarehouseStockEntity> root = criteriaQuery.from(DmcWarehouseStockEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcWarehouseStockEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseStockEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<WarehouseStockDto> findImportStock(Date checkingDate) {
        String checkDate = DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD);
        String strSQL = "select dmi.warehouse_id, dmid.material_id " +
                "  , sum(dmid.quantity) quantity " +
                "  , sum(dmid.total) total " +
                "from dmc_material_import dmi, " +
                "dmc_material_import_detail dmid " +
                "WHERE dmi.id = dmid.material_import_id " +
                "AND dmi.status ="+ DEFAULT_ACTIVE.code() +" " +
                "AND dmi.import_date >= to_date(?1,'yyyyMMdd') " +
                "AND dmi.import_date < to_date(?2,'yyyyMMdd') + 1 " +
                "GROUP BY dmi.warehouse_id, dmid.material_id";

        Query query = entityManager.createNativeQuery(strSQL)
                .setParameter("1", checkDate)
                .setParameter("2", checkDate);
        List<Object[]> resultList = query.getResultList();
        List<WarehouseStockDto> clientDtos = new ArrayList<>();
        for(Object[] objects : resultList){
            clientDtos.add(object2Dto(objects));
        }
        return clientDtos;
    }

    @Override
    public List<WarehouseStockDto> findExportStock(Date checkingDate) {
        String checkDate = DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD);
        String strSQL = "select dmi.warehouse_id, dmid.material_id " +
                "  , -sum(dmid.quantity) quantity " +
                "  , -sum(dmid.total) total " +
                "from dmc_material_export dmi, " +
                "dmc_material_export_detail dmid " +
                "WHERE dmi.id = dmid.material_export_id " +
                "AND dmi.status = "+ DEFAULT_ACTIVE.code() +" " +
                "AND dmi.export_date >= to_date(?1,'yyyyMMdd') " +
                "AND dmi.export_date < to_date(?2,'yyyyMMdd') + 1 " +
                "GROUP BY dmi.warehouse_id, dmid.material_id";

        Query query = entityManager.createNativeQuery(strSQL)
                .setParameter("1", checkDate)
                .setParameter("2", checkDate);
        List<Object[]> resultList = query.getResultList();
        List<WarehouseStockDto> clientDtos = new ArrayList<>();
        for(Object[] objects : resultList){
            clientDtos.add(object2Dto(objects));
        }
        return clientDtos;
    }

    @Override
    public List<WarehouseStockDto> findTransferStock(Date checkingDate) {
        String checkDate = DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD);
        String strSQL = "select warehouse_id, material_id, sum(quantity) quantity, sum(total) total FROM " +
                "(select dmi.transfer_from_id warehouse_id, dmid.material_id " +
                ", -dmid.quantity quantity  " +
                ", -dmid.total total  " +
                "from dmc_material_transfer dmi,  " +
                "dmc_material_transfer_detail dmid  " +
                "WHERE dmi.id = dmid.material_transfer_id " +
                "AND dmi.transfer_date >= to_date(?1,'yyyyMMdd') " +
                "AND dmi.transfer_date < to_date(?2,'yyyyMMdd') + 1 " +
                "AND dmi.status = "+ DEFAULT_ACTIVE.code() +" " +
                "UNION ALL " +
                "select dmi.warehouse_id warehouse_id, dmid.material_id " +
                "  , dmid.quantity quantity " +
                "  , dmid.total total " +
                "from dmc_material_transfer dmi, " +
                "  dmc_material_transfer_detail dmid " +
                "WHERE dmi.id = dmid.material_transfer_id " +
                "AND dmi.transfer_date >= to_date(?3,'yyyyMMdd') " +
                "AND dmi.transfer_date < to_date(?4,'yyyyMMdd') + 1 " +
                "      AND dmi.status = "+ DEFAULT_ACTIVE.code() +") AS GROUPING " +
                "GROUP BY warehouse_id, material_id";

        Query query = entityManager.createNativeQuery(strSQL)
                .setParameter("1", checkDate)
                .setParameter("2", checkDate)
                .setParameter("3", checkDate)
                .setParameter("4", checkDate);
        List<Object[]> resultList = query.getResultList();
        List<WarehouseStockDto> clientDtos = new ArrayList<>();
        for(Object[] objects : resultList){
            clientDtos.add(object2Dto(objects));
        }
        return clientDtos;
    }
    @Override
    public List<WarehouseStockDto> findDismissStock(Date checkingDate) {
        String checkDate = DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD);
        String strSQL = "select dmi.warehouse_id, dmid.material_id " +
                "  , -sum(dmid.quantity) quantity " +
                "  , -sum(dmid.total) total " +
                "from dmc_material_dismiss dmi, " +
                "dmc_material_dismiss_detail dmid " +
                "WHERE dmi.id = dmid.material_dismiss_id " +
                "AND dmi.status = "+ DEFAULT_ACTIVE.code() +" " +
                "AND dmi.dismiss_date >= to_date(?1,'yyyyMMdd') " +
                "AND dmi.dismiss_date < to_date(?2,'yyyyMMdd') + 1 " +
                "GROUP BY dmi.warehouse_id, dmid.material_id";

        Query query = entityManager.createNativeQuery(strSQL)
                .setParameter("1", checkDate)
                .setParameter("2", checkDate);
        List<Object[]> resultList = query.getResultList();
        List<WarehouseStockDto> clientDtos = new ArrayList<>();
        for(Object[] objects : resultList){
            clientDtos.add(object2Dto(objects));
        }
        return clientDtos;
    }

    private WarehouseStockDto object2Dto(Object[] objects) {
        WarehouseStockDto warehouseStockDto = new WarehouseStockDto();
        warehouseStockDto.setWarehouseId((Integer) objects[0]);
        warehouseStockDto.setMaterialId((Integer) objects[1]);
        warehouseStockDto.setQuantity(((BigInteger) objects[2]).intValue());
        warehouseStockDto.setTotal((BigDecimal) objects[3]);
        return warehouseStockDto;
    }

    @Override
    public void save(DmcWarehouseStockEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcWarehouseStockEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcWarehouseStockEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseStockEntity> criteriaQuery = builder.createQuery(DmcWarehouseStockEntity.class);
        Root<DmcWarehouseStockEntity> root = criteriaQuery.from(DmcWarehouseStockEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseStockEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseStockEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public void deleteStock(Date checkingDate, String type) {
        String checkDate = DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD);
        Query query = entityManager.createNativeQuery("DELETE FROM dmc_warehouse_stock " +
                "WHERE date_check >= to_date(?1,'yyyyMMdd') " +
                "AND date_check < to_date(?2,'yyyyMMdd') + 1 " +
                "AND type = ?3")
                .setParameter("1", checkDate)
                .setParameter("2", checkDate)
                .setParameter("3", type);
        query.executeUpdate();
    }

}
