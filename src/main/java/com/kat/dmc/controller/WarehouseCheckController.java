package com.kat.dmc.controller;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.req.WarehouseSearchReq;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import com.kat.dmc.service.interfaces.SupplierService;
import com.kat.dmc.service.interfaces.WarehouseService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("warehouseCheck")
@ConversationScoped
public class WarehouseCheckController implements Serializable {

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    SupplierService supplierService;

    @Autowired
    MaterialService materialService;

    @Autowired
    SessionController sessionController;

    @Autowired
    WarehouseService warehouseService;

    //Tổng hợp
    private WarehouseSearchReq sumOnStockReq;//Hàng trong kho
    private WarehouseSearchReq sumImportReq;//Hàng nhập
    private WarehouseSearchReq sumExportReq;//Hàng xuất
    private WarehouseSearchReq sumTempImpExpReq;//Hàng tạm nhập tá xuất

    private List<MaterialIETDDto> sumOnStockRes;
    private List<MaterialIETDDto> sumImportRes;
    private List<MaterialIETDDto> sumExportRes;
    private List<MaterialIETDDto> sumTempImpExpRes;


    @PostConstruct
    public void init(){
        sumOnStockReq = new WarehouseSearchReq();
        sumImportReq = new WarehouseSearchReq();
        sumExportReq = new WarehouseSearchReq();
        sumTempImpExpReq = new WarehouseSearchReq();
    }

    public void searchSumOnStock(){
        sumOnStockRes = warehouseService.findAllBySearchReq(sumOnStockReq);
        PrimeFaces.current().ajax().update("main:checkTab:tblDetail1Sum");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                , "Info", "Tìm thấy xxx bản ghi"));
    }

    public List<MaterialIETDDto> getSumOnStockRes() {
        return sumOnStockRes;
    }

    public void setSumOnStockRes(List<MaterialIETDDto> sumOnStockRes) {
        this.sumOnStockRes = sumOnStockRes;
    }

    public List<MaterialIETDDto> getSumImportRes() {
        return sumImportRes;
    }

    public void setSumImportRes(List<MaterialIETDDto> sumImportRes) {
        this.sumImportRes = sumImportRes;
    }

    public List<MaterialIETDDto> getSumExportRes() {
        return sumExportRes;
    }

    public void setSumExportRes(List<MaterialIETDDto> sumExportRes) {
        this.sumExportRes = sumExportRes;
    }

    public List<MaterialIETDDto> getSumTempImpExpRes() {
        return sumTempImpExpRes;
    }

    public void setSumTempImpExpRes(List<MaterialIETDDto> sumTempImpExpRes) {
        this.sumTempImpExpRes = sumTempImpExpRes;
    }

    public WarehouseSearchReq getSumOnStockReq() {
        return sumOnStockReq;
    }

    public void setSumOnStockReq(WarehouseSearchReq sumOnStockReq) {
        this.sumOnStockReq = sumOnStockReq;
    }

    public WarehouseSearchReq getSumImportReq() {
        return sumImportReq;
    }

    public void setSumImportReq(WarehouseSearchReq sumImportReq) {
        this.sumImportReq = sumImportReq;
    }

    public WarehouseSearchReq getSumExportReq() {
        return sumExportReq;
    }

    public void setSumExportReq(WarehouseSearchReq sumExportReq) {
        this.sumExportReq = sumExportReq;
    }

    public WarehouseSearchReq getSumTempImpExpReq() {
        return sumTempImpExpReq;
    }

    public void setSumTempImpExpReq(WarehouseSearchReq sumTempImpExpReq) {
        this.sumTempImpExpReq = sumTempImpExpReq;
    }
}
