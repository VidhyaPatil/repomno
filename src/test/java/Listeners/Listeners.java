package Listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import resource.BaseClass;
import utilities.ExtentReports;

public class Listeners extends BaseClass implements ITestListener {
	public WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {

		getExtentReports(result.getName());
		eTest = extent.createTest(result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
		 eTest.log(Status.PASS, "Test case passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			takeScreenshot(result.getName());
		} catch (IOException e) {

		}
		getExtentReports(result.getName());
		eTest = extent.createTest(result.getName());
		eTest.log(Status.FAIL, "Test case failed");

		String screenshotPath = System.getProperty("user.dir") + "./screenshot/" + result.getName() + ".png";
		File file = new File(screenshotPath);
		if (file.exists()) {
			eTest.fail("Failed test screenshot : " + eTest.addScreenCaptureFromPath(screenshotPath));
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		// driver.quit();

	}

}
