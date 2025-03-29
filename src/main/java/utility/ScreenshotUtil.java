package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static void clearOldScreenshots() {
        String screenshotPath = "test-output/screenshots/";
        File directory = new File(screenshotPath);

        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("Cleared old screenshots.");
        } else {
            System.out.println("Screenshot directory is empty or doesn't exist.");
        }
    }
	
	public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String filePath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
	        
	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(srcFile, new File(filePath));

	        System.out.println("Screenshot saved: " + filePath);
		return filePath;
    }
}

