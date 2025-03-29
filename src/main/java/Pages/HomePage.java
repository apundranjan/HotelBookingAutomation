package Pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(id = "accommodations")
	private WebElement accomodationButton;

	@FindBy(id = ":rh:")
	private WebElement destinationField;

	@FindBy(xpath = "//button[@data-testid='date-display-field-start']")
	private WebElement datePicker;

	@FindBy(id = "checkin_date")
	private WebElement checkinDate;

	@FindBy(id = "checkout_date")
	private WebElement checkoutDate;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButton;

	private void selectDate(String date) throws InterruptedException {
		String dateXpath = "//span[@role='checkbox' and @data-date='" + date + "' and span[text()='"
				+ date.substring(date.length() - 2) + "']]";
		System.out.println(dateXpath);
		WebElement dateElement = driver.findElement(By.xpath(dateXpath));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dateXpath)));

		dateElement.click();
	}

	public void searchHotels(String destination, String checkin, String checkout) throws InterruptedException {
		accomodationButton.click();
		destinationField.sendKeys(destination);
		datePicker.click();
		
		selectDate(checkin);
		selectDate(checkout);

		searchButton.click();

	}

}
