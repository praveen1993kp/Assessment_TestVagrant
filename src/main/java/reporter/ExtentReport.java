package reporter;



import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ExtentReport {

    public static ExtentReports report ;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Method to create Extent Report
     */
    public void createReport(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String path = System.getProperty("user.dir") +"/Reports"+ File.separator
                + "TestReport"+
                localDateTime.getYear()+"_"+
                localDateTime.getMonth()+"_"+
                localDateTime.getDayOfMonth()+"_"+
                localDateTime.getHour()+"_"+
                localDateTime.getMinute()+"_"+
                localDateTime.getSecond()+
                ".html";
        report =  reportManager(path);
    }

    /**
     * Method to create html file for the report
     * @param fileName - report name
     * @return - ExtentReport instance
     */
    public ExtentReports reportManager(String fileName) {
        ExtentSparkReporter obj = new ExtentSparkReporter(fileName);
        obj.config().setReportName("<b>"+"<font size=5>"+"Test Automation Report"+"</font>"+"</b>");
        obj.config().setDocumentTitle(fileName);
        obj.config().setTheme(Theme.DARK);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(obj);
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
        return extent;
    }

    /**
     * Create node for each Test
     * @param testCaseName - Test case name which will be same as node name
     */
    public void createNode(String testCaseName){
        extentTest.set(report.createTest(testCaseName));
    }

    /**
     * Method to log the status
     * @param status - Status enum
     * @param log - log to be inserted
     */
    public static void log(Status status, String log){
        switch (status){
            case INFO:
                extentTest.get().log(Status.INFO, log);
                break;
            case PASS:
                extentTest.get().log(Status.PASS, log);
                break;
            case FAIL:
                extentTest.get().log(Status.FAIL, log);
                break;
        }
    }
}
