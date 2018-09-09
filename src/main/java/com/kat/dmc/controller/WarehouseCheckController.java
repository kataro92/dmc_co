package com.kat.dmc.controller;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.WarehouseDailyStatusDto;
import com.kat.dmc.common.dto.WarehouseDto;
import com.kat.dmc.common.dto.WarehouseStatusDto;
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

    private List<WarehouseStatusDto> sumOnStockRes;
    private List<WarehouseStatusDto> sumImportRes;
    private List<WarehouseStatusDto> sumExportRes;
    private List<WarehouseStatusDto> sumTempImpExpRes;
    private List<WarehouseDto> warehouseDtos;


    @PostConstruct
    public void init(){
        sumOnStockReq = new WarehouseSearchReq();
        sumImportReq = new WarehouseSearchReq();
        sumExportReq = new WarehouseSearchReq();
        sumTempImpExpReq = new WarehouseSearchReq();
        warehouseDtos = warehouseService.findAllActive();
        for(WarehouseDto warehouseDto : warehouseDtos){
            List<WarehouseStatusDto> warehouseStatusDtos = warehouseService.findStatusByWarehouseId(warehouseDto.getId());
            warehouseDto.setWarehouseStatusDto(makeDailyFromListStatus(warehouseStatusDtos));
        }
    }

    private WarehouseDailyStatusDto makeDailyFromListStatus(List<WarehouseStatusDto> warehouseStatusDtos){
        WarehouseDailyStatusDto warehouseDailyStatusDto = new WarehouseDailyStatusDto();
        for(WarehouseStatusDto warehouseStatusDto : warehouseStatusDtos){
            if(warehouseStatusDto.getType().equals("A")){
                warehouseDailyStatusDto.setQuantityOnStock(warehouseStatusDto.getQuantity());
                warehouseDailyStatusDto.setPriceOnStock(warehouseStatusDto.getPrice());
            }else if(warehouseStatusDto.getType().equals("B")){
                warehouseDailyStatusDto.setQuantityImportDay(warehouseStatusDto.getQuantity());
                warehouseDailyStatusDto.setPriceImportDay(warehouseStatusDto.getPrice());
            }else if(warehouseStatusDto.getType().equals("C")){
                warehouseDailyStatusDto.setQuantityExportDay(warehouseStatusDto.getQuantity());
                warehouseDailyStatusDto.setPriceExportDay(warehouseStatusDto.getPrice());
            }
        }
        return warehouseDailyStatusDto;
    }

    public void selectSumWarehouse(Integer warehouseId){
        sumOnStockReq.setWarehouseId(warehouseId);
        searchSumOnStock();
        PrimeFaces.current().executeScript("detailShow('1');");
    }

    public void searchSumOnStock(){
        sumOnStockRes = warehouseService.findAllBySearchReq(sumOnStockReq);
        PrimeFaces.current().ajax().update("main:checkTab:tblDetail1Sum");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                , "Info", "Tìm thấy " + sumOnStockRes.size() + " bản ghi"));
    }

    public List<WarehouseStatusDto> getSumOnStockRes() {
        return sumOnStockRes;
    }

    public void setSumOnStockRes(List<WarehouseStatusDto> sumOnStockRes) {
        this.sumOnStockRes = sumOnStockRes;
    }

    public List<WarehouseStatusDto> getSumImportRes() {
        return sumImportRes;
    }

    public void setSumImportRes(List<WarehouseStatusDto> sumImportRes) {
        this.sumImportRes = sumImportRes;
    }

    public List<WarehouseStatusDto> getSumExportRes() {
        return sumExportRes;
    }

    public void setSumExportRes(List<WarehouseStatusDto> sumExportRes) {
        this.sumExportRes = sumExportRes;
    }

    public List<WarehouseStatusDto> getSumTempImpExpRes() {
        return sumTempImpExpRes;
    }

    public void setSumTempImpExpRes(List<WarehouseStatusDto> sumTempImpExpRes) {
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

    public List<WarehouseDto> getWarehouseDtos() {
        return warehouseDtos;
    }

    public void setWarehouseDtos(List<WarehouseDto> warehouseDtos) {
        this.warehouseDtos = warehouseDtos;
    }
}
