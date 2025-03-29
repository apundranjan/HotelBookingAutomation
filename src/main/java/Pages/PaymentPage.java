package Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {

	private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Title of page : " + driver.getTitle());
	}

	@FindBy(xpath = "//input[@name='coupon_code_input']")
	private WebElement couponField;

	@FindBy(xpath = "//button//span[normalize-space(text())='Apply']")
	private WebElement applyCouponButton;

	@FindBy(xpath = "//button[contains(@class, 'bui-button--secondary') and contains(@class, 'js-booking-overview__trigger') and @name='review']")
	private WebElement reviewBookingBtn;

	@FindBy(xpath = "//p[@id='bp-checkin-date__label']/following-sibling::time//p[@class='bui-date__title']")
	private WebElement checkinDate;

	@FindBy(xpath = "//p[@id='bp-checkout-date__label']/following-sibling::time//p[@class='bui-date__title']")
	private WebElement checkoutDate;

	@FindBy(xpath = "//button[span[contains(text(), 'Looks good, complete my booking!')]]")
	private WebElement bookFinal;

	public void applyCoupon(String couponCode) throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		couponField.sendKeys(couponCode);
		applyCouponButton.click();
	}

	@FindBy(id = "coupon_code-error")
	private WebElement couponCodeError;

	public boolean isDiscountApplied() throws InterruptedException {
		Thread.sleep(5000);
		return couponCodeError.isDisplayed();
	}

	public boolean reviewBookingDetails(String inDate, String outDate) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.titleContains("Don't forget your booking")); - debugging

		js.executeScript("window.scrollBy(0, -1000);");

		boolean res = false;
		reviewBookingBtn.click();
		Thread.sleep(2000);
		String checkindate = checkinDate.getText();
		String checkoutdate = checkoutDate.getText();
		/* Debugging 
		 * System.out.println("Check in date : " + checkindate + " || Checkout Date : "
		 * + checkoutdate); System.out.println("Check in date : " + inDate +
		 * " || Checkout Date : " + outDate);
		 */
		
		if (inDate.equals(checkindate) && outDate.equals(checkoutdate)) {
			res = true;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(1000);
			bookFinal.click();
		}

		return res;
	}

}
