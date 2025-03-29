package Pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	@SuppressWarnings("unused")
	private WebDriver driver;

	/*
	 * @FindBy(xpath = "//span[@class='e4adce92df' and text()='See availability']")
	 * private WebElement firstHotelSelect;
	 */

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Title of page : " + driver.getTitle());
	}

	public void selectFirstHotel() {
		System.out.println("Search result page : " + driver.getTitle());

		/*
		 * if (firstHotelSelect.isEmpty()) { throw new
		 * RuntimeException("No hotels found! Check the locator or ensure search results are loaded."
		 * ); }
		 */
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[@class='e4adce92df' and text()='See availability']"))));
		WebElement firstHotelSelect = driver
				.findElement(By.xpath("//span[@class='e4adce92df' and text()='See availability']"));
		// System.out.println("See availability button text : " +
		// firstHotelSelect.getText());
		try {
			firstHotelSelect.click();

			String originalWindow = driver.getWindowHandle();

			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows) {
				if (!window.equals(originalWindow)) {
					driver.switchTo().window(window);
					System.out.println("new handle : " + driver.getTitle());
				}
			}

		} catch (Exception e) {
			System.out.println("No hotels found");
			System.out.println(e.getMessage());
		}
	}
}
