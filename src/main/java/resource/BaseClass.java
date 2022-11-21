package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public Properties prop;
	public Logger logger;
	public ExtentReports extent;
	public ExtentTest eTest;

	public WebDriver initilization() throws IOException {

		prop = new Properties();
		String propPath = System.getProperty("user.dir") + "\\src\\main\\java\\resource\\data.properties";
		FileInputStream fis = new FileInputStream(propPath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}
		// driver.get("https://www.saucedemo.com/");
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger = LogManager.getLogger("SauceLiveProject");

		return driver;

	}

	public void takeScreenshot(String testName) throws IOException {
		//TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\screenshot\\" + testName + ".png";

		FileUtils.copyFile(src, new File(screenshotPath));
		//return screenshotPath;
	}

	public void getExtentReports(String testName) {
		// String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new
		// Date());
		String reportame = "SauceLiveProject" + testName + ".html";

		String reportpath = System.getProperty("user.dir") + "/report/" + reportame;
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
		reporter.config().setReportName("Test Report");
		reporter.config().setDocumentTitle("Extent Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

}
