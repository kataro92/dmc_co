package com.kat.dmc.repository.impl;

import com.kat.dmc.common.constant.DateConst;
import com.kat.dmc.common.model.DmcWarehouseEntity;
import com.kat.dmc.common.model.DmcWarehouseEntity_;
import com.kat.dmc.common.model.DmcWarehouseStatus;
import com.kat.dmc.common.req.WarehouseSearchReq;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.repository.interfaces.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class WarehouseRepoImpl implements WarehouseRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcWarehouseEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcWarehouseEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcWarehouseEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcWarehouseEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcWarehouseEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcWarehouseEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcWarehouseEntity> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.status), 1));
        if(canImport != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canImport), canImport));
        if(canExport != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canExport), canExport));
        if(canTransfer != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canTransfer), canTransfer));
        if(canDismiss != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canDismiss), canDismiss));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcWarehouseStatus> findAllBySearchReq(WarehouseSearchReq sumOnStockReq) {
        TypedQuery<DmcWarehouseStatus> statusTypedQuery = entityManager.createNamedQuery("DmcWarehouseStatus.findAllBySearchReq", DmcWarehouseStatus.class)
                .setParameter("warehouse_id", sumOnStockReq.getWarehouseId());
        String[] dateParams = makeFromToDate(sumOnStockReq, "0", "0");
        statusTypedQuery.setParameter("from_date", dateParams[0]);
        statusTypedQuery.setParameter("to_date", dateParams[1]);
        return statusTypedQuery.getResultList();
    }
    @Override
    public List<DmcWarehouseStatus> findImportBySearchReq(WarehouseSearchReq sumOnStockReq) {
        TypedQuery<DmcWarehouseStatus> statusTypedQuery = entityManager.createNamedQuery("DmcWarehouseStatus.findImportBySearchReq", DmcWarehouseStatus.class)
                .setParameter("warehouse_id", sumOnStockReq.getWarehouseId());
        String[] dateParams = makeFromToDate(sumOnStockReq, "0", "0");
        statusTypedQuery.setParameter("from_date", dateParams[0]);
        statusTypedQuery.setParameter("to_date", dateParams[1]);
        return statusTypedQuery.getResultList();
    }
    @Override
    public List<DmcWarehouseStatus> findExportBySearchReq(WarehouseSearchReq sumOnStockReq) {
        TypedQuery<DmcWarehouseStatus> statusTypedQuery = entityManager.createNamedQuery("DmcWarehouseStatus.findExportBySearchReq", DmcWarehouseStatus.class)
                .setParameter("warehouse_id", sumOnStockReq.getWarehouseId());
        String[] dateParams = makeFromToDate(sumOnStockReq, "0", "0");
        statusTypedQuery.setParameter("from_date", dateParams[0]);
        statusTypedQuery.setParameter("to_date", dateParams[1]);
        return statusTypedQuery.getResultList();
    }
    @Override
    public List<DmcWarehouseStatus> findTempImportBySearchReq(WarehouseSearchReq sumOnStockReq) {
        TypedQuery<DmcWarehouseStatus> statusTypedQuery = entityManager.createNamedQuery("DmcWarehouseStatus.findTempImportBySearchReq", DmcWarehouseStatus.class)
                .setParameter("warehouse_id", sumOnStockReq.getWarehouseId());
        String[] dateParams = makeFromToDate(sumOnStockReq, "0", "0");
        statusTypedQuery.setParameter("from_date", dateParams[0]);
        statusTypedQuery.setParameter("to_date", dateParams[1]);
        return statusTypedQuery.getResultList();
    }

    private String[] makeFromToDate(WarehouseSearchReq sumOnStockReq, String fromDate, String toDate){
        if(sumOnStockReq.getType() == 0){//Ngày
            if(sumOnStockReq.getSearchType() == 0){
                fromDate = getValueFromDate(sumOnStockReq.getDay());
                toDate = getValueFromDate(sumOnStockReq.getDay());
            }else {
                fromDate = getValueFromDate(sumOnStockReq.getFromDay());
                toDate = getValueFromDate(sumOnStockReq.getToDay());
            }
        }else if(sumOnStockReq.getType() == 1){//Tháng
            if(sumOnStockReq.getSearchType() == 0){
                fromDate = getValueFromDate(DateUtil.getStartDay4MonthYear(sumOnStockReq.getMonth(), sumOnStockReq.getYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4MonthYear(sumOnStockReq.getMonth(), sumOnStockReq.getYear()));
            }else {
                fromDate = getValueFromDate(DateUtil.getStartDay4MonthYear(sumOnStockReq.getFromMonth(), sumOnStockReq.getFromYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4MonthYear(sumOnStockReq.getToMonth(), sumOnStockReq.getToYear()));
            }
        }else if(sumOnStockReq.getType() == 2){//Quý
            if(sumOnStockReq.getSearchType() == 0){
                fromDate = getValueFromDate(DateUtil.getStartDay4QuaterYear(sumOnStockReq.getQuater(), sumOnStockReq.getYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4QuaterYear(sumOnStockReq.getQuater(), sumOnStockReq.getYear()));
            }else {
                fromDate = getValueFromDate(DateUtil.getStartDay4QuaterYear(sumOnStockReq.getFromQuater(), sumOnStockReq.getFromYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4QuaterYear(sumOnStockReq.getToQuater(), sumOnStockReq.getToYear()));
            }
        }else if(sumOnStockReq.getType() == 3){//Năm
            if(sumOnStockReq.getSearchType() == 0){
                fromDate = getValueFromDate(DateUtil.getStartDay4Year(sumOnStockReq.getYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4Year(sumOnStockReq.getYear()));
            }else {
                fromDate = getValueFromDate(DateUtil.getStartDay4Year(sumOnStockReq.getFromYear()));
                toDate = getValueFromDate(DateUtil.getEndDay4Year(sumOnStockReq.getToYear()));
            }
        }
        return new String[] {fromDate, toDate};
    }

    private String getValueFromDate(Date day){
        if(day == null){
            return "0";
        }else{
            return DateUtil.toString(day, DateConst.FORMAT_YYYYMMDD);
        }
    }
    @Override
    public List<DmcWarehouseStatus> findDailyStatus(Integer warehouseId) {
        return entityManager.createNamedQuery("DmcWarehouseStatus.findDailyStatus", DmcWarehouseStatus.class)
                .setParameter("warehouse_id", warehouseId)
                .setParameter("process_date", DateUtil.toString(DateUtil.getCurrentDayTS(), DateConst.FORMAT_YYYYMMDD))
                .getResultList();
    }
}
