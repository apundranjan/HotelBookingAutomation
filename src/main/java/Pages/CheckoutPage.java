package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPage {
	private WebDriver driver;

	@FindBy(id = ":rn:")
	private WebElement firstNameText;

	@FindBy(id = ":ro:")
	private WebElement lastNameText;

	@FindBy(id = ":rp:")
	private WebElement emailAddressText;

	@FindBy(id = ":rv:")
	private WebElement countrydrpdwn;

	@FindBy(id = ":r11:")
	private WebElement phoneNumberText;

	@FindBy(xpath = "//button[@name=\'book\']")
	private WebElement FinalCheckout;

	@FindBy(id = ":r3l:")
	private WebElement bookingName;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Title of page : " + driver.getTitle());
	}

	public void selectDropdown(WebElement webElement, String value) {
		Select select = new Select(webElement);
		select.selectByVisibleText(value);
	}

	public void proceedToCheckout(String firstName, String lastname, String emailAddress, String country,
			String phoneNumber) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(5000);
		//System.out.println("text in firstname : " + firstNameText.getText());
		firstNameText.sendKeys(firstName);
		lastNameText.sendKeys(lastname);
		emailAddressText.sendKeys(emailAddress);
		selectDropdown(countrydrpdwn, country);
		phoneNumberText.sendKeys(phoneNumber);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		FinalCheckout.click();
		

	}

}
