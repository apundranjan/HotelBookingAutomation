package Base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ScreenshotUtil;

public class BaseTest {
	protected static WebDriver driver;

	@BeforeSuite
	public void clearOldOutput() {
		String testOutputPath = System.getProperty("user.dir") + "/test-output/";
		deleteOldFiles(testOutputPath); // Delete old reports
		deleteOldFiles(testOutputPath + "screenshots/"); // Delete old screenshots
		//deleteOldFiles("C:\\Logs\\"); // Delete old logs
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
	@BeforeClass()
	public void setUp() throws InterruptedException {

		if (driver == null) { // Prevent duplicate WebDriver instances
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("WebDriver initialized.");
		}

		driver.get("https://www.booking.com");
		//Thread.sleep(5000);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			System.out.println("Closing the browser after all tests.");
			driver.quit();
		}
	}
	
	 @AfterMethod
	    public void takeScreenshotOnFailure(ITestResult result) throws IOException {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            String screenShotFilePath = ScreenshotUtil.captureScreenshot(driver, result.getName());  // Call ScreenshotUtils method
	            System.out.println("Screenshot captured for failed test: " + result.getName());
	        }
	    }
}
