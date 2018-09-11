package com.kat.dmc.service.impl;
/**
 * Created by DucPH
 * on 10/5/2017.
 */

import com.kat.dmc.common.util.ResourceUtil;
import com.kat.dmc.service.interfaces.ReportRunner;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.logging.Level;

@Service
@Qualifier("birt")
public class BIRTReportRunner implements ReportRunner {
    private static final String DEFAULT_LOGGING_DIRECTORY = "defaultBirtLoggingDirectory/";
    private static final String SYSTEM_PROPERTIES = "system.properties";
    private static Logger logger = LoggerFactory.getLogger(BIRTReportRunner.class);
    private static IReportEngine birtReportEngine = null;


    public static IReportEngine getBirtReportEngine(){
        return birtReportEngine;
    }
    public void setBirtReportEngine(IReportEngine birtReportEngine){
        BIRTReportRunner.birtReportEngine = birtReportEngine;
    }
    /**
     * Starts up and configures the BIRT Report Engine
     */
    @PostConstruct
    public void startUp() {
        try {
            String birtLoggingDirectory = ResourceUtil.getProperty(SYSTEM_PROPERTIES, "birt_logging_directory") == null
                    ? DEFAULT_LOGGING_DIRECTORY : ResourceUtil.getProperty(SYSTEM_PROPERTIES, "birt_logging_directory");
            Level birtLoggingLevel = ResourceUtil.getProperty(SYSTEM_PROPERTIES, "birt_logging_level") == null
                    ? Level.SEVERE : Level.parse(ResourceUtil.getProperty(SYSTEM_PROPERTIES, "birt_logging_level"));
            EngineConfig engineConfig = new EngineConfig();
            logger.info("[BIRT] log directory : {}", birtLoggingDirectory);
            logger.info("[BIRT] log level : {}", birtLoggingLevel);
            engineConfig.setLogConfig(birtLoggingDirectory, birtLoggingLevel);
            // Required due to a bug in BIRT that occurs in calling Startup after the Platform has already been started up
            RegistryProviderFactory.releaseDefault();
            Platform.startup(engineConfig);
            IReportEngineFactory reportEngineFactory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            setBirtReportEngine(reportEngineFactory.createReportEngine(engineConfig));
        } catch (BirtException e) {
            // Possibly rethrow the exception here and catch it in the aspect.
            logger.error("[BIRT][ERROR] {}", e);
        }
    }

    /**
     * Shuts down the BIRT Report Engine
     */
    @PreDestroy
    public void shutdown() {
        birtReportEngine.destroy();
        RegistryProviderFactory.releaseDefault();
        Platform.shutdown();
    }
}
