package listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import Base.BaseTest;
import utility.ScreenshotUtil;

public class TestListener extends BaseTest implements ITestListener {
	@Override
	public void onTestFailure(ITestResult result) {
		if (driver != null) {
			String testName = result.getName();
			String screenshotPath = null;
			try {
				screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Screenshot captured for failed test: " + screenshotPath);
//Attach screenshot to TestNG Report
			Reporter.log("<br><b>Test Failed: " + testName + "</b>");
			Reporter.log("<br><img src='" + screenshotPath + "' height='300' width='500'/><br>");
		}else {
			System.out.println("Driver is null. Screenshot not taken");
		}

	}
}

