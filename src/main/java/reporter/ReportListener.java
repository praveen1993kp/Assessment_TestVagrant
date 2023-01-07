package reporter;

import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        ExtentReport.log(Status.FAIL, "Test Execution Failed");
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReport.log(Status.SKIP, "Test Execution Skipped");
    }
}
