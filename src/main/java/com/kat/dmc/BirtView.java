package com.kat.dmc;

/**
 * Created by DucPH
 * on 10/5/2017.
 */

import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.common.util.FileUtil;
import com.kat.dmc.common.util.ResourceUtil;
import com.kat.dmc.service.impl.BIRTReportRunner;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.OdaDataSetHandle;
import org.eclipse.birt.report.model.api.ParameterHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.mock;

/**
 * BirtView is used to run and render BIRT reports.
 * This class expects the request to contain a reportName and reportFormat
 * parameter. In addition Report parameters are automatically searched for in the
 * the request object.
 */
public class BirtView extends AbstractView {

    private static IReportEngine birtEngine = BIRTReportRunner.getBirtReportEngine();
    private String reportNameRequestParameter = "reportName";
    private String reportFormatRequestParameter = "reportFormat";
    private String reportFilename = "filename";
    private String reportStartup = "startup";
    private static Logger birtViewLogger = LoggerFactory.getLogger(BirtView.class);
    @PostConstruct
    public void startUpTemplateEngine(){
        try {
            HttpServletRequest mockRequest = mock(HttpServletRequest.class);
            HttpServletResponse mockResponse = mock(HttpServletResponse.class);
            DateTime now = DateTime.now();
            mockRequest.setAttribute("filename",reportStartup);
            mockRequest.setAttribute("reportFormat",IRenderOption.OUTPUT_FORMAT_PDF);
            mockRequest.setAttribute("reportName",reportStartup);
            renderMergedOutputModel(mockRequest, mockResponse
                    , IRenderOption.OUTPUT_FORMAT_PDF,reportStartup,reportStartup);
            DateTime dateTime = DateTime.now();
            Seconds seconds = Seconds.secondsBetween(now, dateTime);
            birtViewLogger.info("[BIRT][FIRST-RUN-DURATION] : {}", seconds.getSeconds());
        } catch (Exception e) {
            birtViewLogger.error("[BIRT][ERROR][FIRST-RUN] {}", e);
        }
    }

    protected void renderMergedOutputModel(
            Map map, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String format = request.getParameter(this.reportFormatRequestParameter);
        String reportName = request.getParameter(this.reportNameRequestParameter);
        String filename = request.getParameter(this.reportFilename);
        renderMergedOutputModel(request, response, format, reportName, filename);
    }

    protected void renderMergedOutputModel(HttpServletRequest request,
                                           HttpServletResponse response, String format,
                                           String reportName, String filename) throws EngineException, IOException {
        DateTime now = DateTime.now();
        IReportRunnable runnable;
        ClassLoader classLoader = getClass().getClassLoader();
        String templateRoot = ResourceUtil.getProperty("system.properties", "birt_report_input_dir");
        URL resource = classLoader.getResource(templateRoot + File.separator + reportName + ".rptdesign");
        String decoded = URLDecoder.decode(FileUtil.writeReport2Temp(URLDecoder.decode(resource.getPath(), "UTF-8")), "UTF-8");
        runnable = birtEngine.openReportDesign(decoded);
        IRunAndRenderTask runAndRenderTask = birtEngine.createRunAndRenderTask(runnable);
        runAndRenderTask.validateParameters();
        response.setContentType(birtEngine.getMIMEType(format));

        ReportDesignHandle reportDesignHandle = (ReportDesignHandle) runnable.getDesignHandle( );
        List<OdaDataSetHandle> dataSets;
        dataSets = reportDesignHandle.getAllDataSets();
        List<ParameterHandle> allParameters = reportDesignHandle.getAllParameters();
        for(OdaDataSetHandle ds : dataSets){
            PropertyHandle propertyHandle = reportDesignHandle.findDataSet(ds.getName()).getPropertyHandle("queryText");
            String query = (String) propertyHandle.getValue();
            logger.info("[BIRT][SQL]: " + query);
        }
        IRenderOption options = new RenderOption();
        //Set report parameter
        HashMap<String, Object> args = new HashMap<>();
        args.putAll(request.getParameterMap());
        Iterator<Map.Entry<String, Object>> iterator = args.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> arg = iterator.next();
            String[] lstValue = (String[]) arg.getValue();
            runAndRenderTask.setParameterValue(arg.getKey(), lstValue[0]);
        }
        for(ParameterHandle parameter : allParameters){
            logger.info("[BIRT][PARAM]: " + parameter.getName() + " : " + runAndRenderTask.getParameterValue(parameter.getName()));
        }
        try {
            if (format.equalsIgnoreCase(IRenderOption.OUTPUT_FORMAT_PDF)) {
                PDFRenderOption pdfOptions = new PDFRenderOption(options);
                pdfOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
                pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW
                        , IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);
                pdfOptions.setOption(IPDFRenderOption.PDF_HYPHENATION , true);
                pdfOptions.setOption(IPDFRenderOption.PDF_TEXT_WRAPPING , true);
                pdfOptions.setEmbededFont(true);
                filename = filename.replaceAll("[\\\\/:*?\"<>|]", "_");
                String newFilename = CommonUtil.makeJapanEncodeOnly(filename).replaceAll("\\+", "%20");
                String userAgent = request.getHeader("User-Agent");
                if(userAgent != null && (userAgent.contains("Firefox") || userAgent.contains("Safari"))){
                    response.setHeader("Content-Disposition","attachment; filename*=utf-8''" + CommonUtil.rfc5987_encode(filename) + ".pdf");
                }else {
                    response.setHeader("Content-Disposition", "attachment; filename=" + newFilename + ".pdf");
                }
                pdfOptions.setOutputStream(response.getOutputStream());
                runAndRenderTask.setRenderOption(pdfOptions);
            } else {
                HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
                htmlOptions.setOutputFormat(IRenderOption.OUTPUT_FORMAT_HTML);
                htmlOptions.setOutputStream(response.getOutputStream());
                htmlOptions.setImageHandler(new HTMLServerImageHandler());
                htmlOptions.setBaseImageURL(request.getContextPath() + "/images");
                runAndRenderTask.setRenderOption(htmlOptions);
            }
            runAndRenderTask.getAppContext().put(EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, request);
            runAndRenderTask.run();
            runAndRenderTask.close();
        } catch (Exception ex) {
            response.setContentType(birtEngine.getMIMEType(IRenderOption.OUTPUT_FORMAT_HTML));
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.println("<h4>"+reportName+"</h4>");
            outputStream.println("<p style='padding-left: 41px'>");
            outputStream.println("<b style='color: red'>"+ex.getMessage()+"</b><br/>");
            outputStream.println("<a style='color: red'>"+ex.getLocalizedMessage()+"</a>");
            outputStream.println("</p>");
        }
        DateTime dateTime = DateTime.now();
        Seconds seconds = Seconds.secondsBetween(now, dateTime);
        birtViewLogger.info("[BIRT] {} :> duration :> {}s", reportName, seconds.getSeconds());
    }
}