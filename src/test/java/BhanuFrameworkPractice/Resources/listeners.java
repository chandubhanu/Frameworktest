package BhanuFrameworkPractice.Resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BhanuFrameworkPractice.Global.extentReports;

public class listeners extends BaseTest implements ITestListener {
	ExtentReports extent = extentReports.getReportData();
	ExtentTest test;
	private WebDriver driver;
	ThreadLocal<ExtentTest> ExtentTest=new ThreadLocal<ExtentTest>();
	 
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test = extent.createTest(result.getMethod().getMethodName());
		ExtentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		ExtentTest.get().log(Status.PASS, "Test case hasbeen passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    ITestListener.super.onTestFailure(result);
	    ExtentTest.get().fail(result.getThrowable());

	    // Attempt to retrieve the driver from the test class
	    WebDriver driver = null;
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
	        		.get(result.getInstance());
	    } catch (NoSuchFieldException | IllegalAccessException e) {
	        e.printStackTrace();
	    }

	    // Take screenshot if the driver is available
	    if (driver != null) {
	        try {
	            String filePath = screenShot(result.getMethod().getMethodName(), driver);
	            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Driver not found. Cannot take screenshot.");
	    }
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
       extent.flush();
       
	}

}
