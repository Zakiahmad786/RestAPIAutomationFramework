package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.logging.Handler;

public class ExtentReportManager {

    public static ExtentReports extReports;
    public static ExtentReports createInstance(String fileName,String reportName,String documentTitle){

        ExtentSparkReporter extentReport=new ExtentSparkReporter(fileName);
        extentReport.config().setReportName(reportName);
        extentReport.config().setDocumentTitle(documentTitle);
        extentReport.config().setTheme(Theme.DARK);
        extentReport.config().setEncoding("utf-8");

       extReports=new ExtentReports();
       extReports.attachReporter(extentReport);
       return extReports;
    }

public static String getReportNameWithTimeStamp(){
    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy_mm_dd_hh_mm_ss");
    LocalDateTime localDateTime=LocalDateTime.now();
    String formatedTime=dateTimeFormatter.format(localDateTime);
    String reportName="Test Report"+formatedTime+".html";
    return reportName;
}

public static void logPassDetails(String log){
        Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
}
    public static void logFailureDetails(String log){
        Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logInfoDetails(String log){
        Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log){
        Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }
    public static void logJson(String json){
        Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static void logHeaders(List<Header> headersList){
        String[][] arrayHeaders=headersList.stream().map(header -> new String[] {header.getName(),header.getValue()})
                .toArray(String[][] :: new);
        Setup.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
    }
    public static void logExceptionDetails(String log){
        Setup.extentTest.get().fail(log);
    }
}
