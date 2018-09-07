package com.kat.dmc.scheduler;

import com.kat.dmc.common.constant.ConfigConst;
import com.kat.dmc.common.constant.DateConst;
import com.kat.dmc.common.model.DmcConfigEntity;
import com.kat.dmc.common.model.DmcWarehouseStockEntity;
import com.kat.dmc.common.dto.WarehouseStockDto;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.common.util.StringUtil;
import com.kat.dmc.repository.interfaces.ConfigRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.repository.interfaces.WarehouseStockRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


//@EnableScheduling
@Component
@Transactional
public class WarehouseDayCheck {

    @Autowired
    ConfigRepo configRepo;
    @Autowired
    UtilRepo utilRepo;
    @Autowired
    WarehouseStockRepo warehouseStockRepo;
    @Autowired
    ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(WarehouseDayCheck.class);

    @Scheduled(fixedDelay = 86400000, initialDelay = 10000)
    public void checkWarehouseImport(){
        logger.info("Start check warehouse import ...");
        try{
            //Get warehouse config
            DmcConfigEntity configEntity;
            try {
                configEntity = configRepo.findByKey(ConfigConst.CHECKED_IMPORT_DATE);
            }catch (NoResultException ex){
                configEntity = new DmcConfigEntity();
                configEntity.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
                configEntity.setKey(ConfigConst.CHECKED_IMPORT_DATE);
                configEntity.setValue(DateUtil.toString(DateUtil.getPreviousDay(DateUtil.getCurrentDayTS()), DateConst.FORMAT_YYYYMMDD));
                configRepo.save(configEntity);
            }
            Date checkingDate = StringUtil.toDate(configEntity.getValue(), DateConst.FORMAT_YYYYMMDD);
            //Get warehouse import yesterday
            List<WarehouseStockDto> importStock = warehouseStockRepo.findImportStock(checkingDate);
            //Remove all data if exits in warehouse check
            warehouseStockRepo.deleteStock(checkingDate, "import");
            //Add to warehouse check table
            for(WarehouseStockDto stockDto : importStock){
                DmcWarehouseStockEntity stockEntity = modelMapper.map(stockDto, DmcWarehouseStockEntity.class);
                stockEntity.setId(utilRepo.findSequenceNextval("dmc_warehouse_stock_id_seq"));
                stockEntity.setType("import");
                stockEntity.setDateCheck(new Timestamp(checkingDate.getTime()));
                stockEntity.setImportId(stockDto.getImportId());
                warehouseStockRepo.save(stockEntity);
            }
            checkingDate = DateUtil.getNextDay(checkingDate);
            //Update dmc_config
            configRepo.saveConfig(ConfigConst.CHECKED_IMPORT_DATE, DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD));
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        logger.info("... warehouse import done.");
    }

    @Scheduled(fixedDelay = 86400000, initialDelay = 20000)
    public void checkWarehouseExport(){
        logger.info("Start check warehouse export ...");
        try{
            //Get warehouse config
            DmcConfigEntity configEntity;
            try {
                configEntity = configRepo.findByKey(ConfigConst.CHECKED_EXPORT_DATE);
            }catch (NoResultException ex){
                configEntity = new DmcConfigEntity();
                configEntity.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
                configEntity.setKey(ConfigConst.CHECKED_EXPORT_DATE);
                configEntity.setValue(DateUtil.toString(DateUtil.getPreviousDay(DateUtil.getCurrentDayTS()), DateConst.FORMAT_YYYYMMDD));
                configRepo.save(configEntity);
            }
            Date checkingDate = StringUtil.toDate(configEntity.getValue(), DateConst.FORMAT_YYYYMMDD);
            //Get warehouse export yesterday
            List<WarehouseStockDto> exportStock = warehouseStockRepo.findExportStock(checkingDate);
            //Remove all data if exits in warehouse check
            warehouseStockRepo.deleteStock(checkingDate, "export");
            //Add to warehouse check table
            for(WarehouseStockDto stockDto : exportStock){
                DmcWarehouseStockEntity stockEntity = modelMapper.map(stockDto, DmcWarehouseStockEntity.class);
                stockEntity.setId(utilRepo.findSequenceNextval("dmc_warehouse_stock_id_seq"));
                stockEntity.setType("export");
                stockEntity.setDateCheck(new Timestamp(checkingDate.getTime()));

                warehouseStockRepo.save(stockEntity);
            }
            checkingDate = DateUtil.getNextDay(checkingDate);
            //Update dmc_config
            configRepo.saveConfig(ConfigConst.CHECKED_EXPORT_DATE, DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD));
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        logger.info("... warehouse export done.");
    }

    @Scheduled(fixedDelay = 86400000, initialDelay = 30000)
    public void checkWarehouseTransfer(){
        logger.info("Start check transfer export ...");
        try{
            //Get warehouse config
            DmcConfigEntity configEntity;
            try {
                configEntity = configRepo.findByKey(ConfigConst.CHECKED_TRANSFER_DATE);
            }catch (NoResultException ex){
                configEntity = new DmcConfigEntity();
                configEntity.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
                configEntity.setKey(ConfigConst.CHECKED_TRANSFER_DATE);
                configEntity.setValue(DateUtil.toString(DateUtil.getPreviousDay(DateUtil.getCurrentDayTS()), DateConst.FORMAT_YYYYMMDD));
                configRepo.save(configEntity);
            }
            Date checkingDate = StringUtil.toDate(configEntity.getValue(), DateConst.FORMAT_YYYYMMDD);
            //Get warehouse transfer yesterday
            List<WarehouseStockDto> transferStock = warehouseStockRepo.findTransferStock(checkingDate);
            //Remove all data if exits in warehouse check
            warehouseStockRepo.deleteStock(checkingDate, "transfer");
            //Add to warehouse check table
            for(WarehouseStockDto stockDto : transferStock){
                DmcWarehouseStockEntity stockEntity = modelMapper.map(stockDto, DmcWarehouseStockEntity.class);
                stockEntity.setId(utilRepo.findSequenceNextval("dmc_warehouse_stock_id_seq"));
                stockEntity.setType("transfer");
                stockEntity.setDateCheck(new Timestamp(checkingDate.getTime()));
                warehouseStockRepo.save(stockEntity);
            }
            checkingDate = DateUtil.getNextDay(checkingDate);
            //Update dmc_config
            configRepo.saveConfig(ConfigConst.CHECKED_TRANSFER_DATE, DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD));
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        logger.info("... warehouse transfer done.");
    }

    @Scheduled(fixedDelay = 86400000, initialDelay = 40000)
    public void checkWarehouseDismiss(){
        logger.info("Start check warehouse dismiss ...");
        try{
            //Get warehouse config
            DmcConfigEntity configEntity;
            try {
                configEntity = configRepo.findByKey(ConfigConst.CHECKED_DISMISS_DATE);
            }catch (NoResultException ex){
                configEntity = new DmcConfigEntity();
                configEntity.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
                configEntity.setKey(ConfigConst.CHECKED_DISMISS_DATE);
                configEntity.setValue(DateUtil.toString(DateUtil.getPreviousDay(DateUtil.getCurrentDayTS()), DateConst.FORMAT_YYYYMMDD));
                configRepo.save(configEntity);
            }
            Date checkingDate = StringUtil.toDate(configEntity.getValue(), DateConst.FORMAT_YYYYMMDD);
            //Get warehouse export yesterday
            List<WarehouseStockDto> exportStock = warehouseStockRepo.findDismissStock(checkingDate);
            //Remove all data if exits in warehouse check
            warehouseStockRepo.deleteStock(checkingDate, "dismiss");
            //Add to warehouse check table
            for(WarehouseStockDto stockDto : exportStock){
                DmcWarehouseStockEntity stockEntity = modelMapper.map(stockDto, DmcWarehouseStockEntity.class);
                stockEntity.setId(utilRepo.findSequenceNextval("dmc_warehouse_stock_id_seq"));
                stockEntity.setType("dismiss");
                stockEntity.setDateCheck(new Timestamp(checkingDate.getTime()));
                warehouseStockRepo.save(stockEntity);
            }
            checkingDate = DateUtil.getNextDay(checkingDate);
            //Update dmc_config
            configRepo.saveConfig(ConfigConst.CHECKED_DISMISS_DATE, DateUtil.toString(checkingDate, DateConst.FORMAT_YYYYMMDD));
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        logger.info("... warehouse dismiss done.");
    }
}
