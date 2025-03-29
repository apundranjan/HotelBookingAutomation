package Base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExtentManager;
import utility.ScreenshotUtil;

public class BaseTest {
	protected static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void clearOldOutput() {
		String testOutputPath = System.getProperty("user.dir") + "/test-output/";
		deleteOldFiles(testOutputPath); // Delete old reports
		String screenshotOutputPath = System.getProperty("user.dir") + "/screenshots/";
		deleteOldFiles(screenshotOutputPath); // Delete old screenshots
	}

	public void deleteOldFiles(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.exists() && directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				}
			}
			System.out.println(" Deleted old files from: " + directoryPath);
		} else {
			System.out.println(" Directory not found or already empty: " + directoryPath);
		}
	}

	@SuppressWarnings("deprecation")
	@BeforeMethod()
	public void setUp() throws InterruptedException {

		if (driver == null) { // Prevent duplicate WebDriver instances
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("WebDriver initialized.");
		}

		driver.get("https://www.booking.com");
		// Thread.sleep(5000);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			System.out.println("Closing the browser after all tests.");
			driver.quit();
		}
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotFilePath = ScreenshotUtil.captureScreenshot(driver, result.getName()); // Call
																									// ScreenshotUtils
																									// method
			System.out.println("Screenshot captured for failed test: " + result.getName());
		}
	}

	@BeforeSuite
	public void setupExtent() {
		extent = ExtentManager.getInstance();
	}

	@AfterSuite
	public void flushReports() {
		extent.flush();
	}
}
