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

public class BookingPage {
	private WebDriver driver;
	
	@FindBy(id = "hprt_nos_select_5692715_95461013_0_2_0")
	private WebElement selectRoomCount;
	
	@FindBy(xpath = "//span[@class=\"bui-button__text js-reservation-button__text\"]")
	private WebElement ContinueToBookButton;
	
	@FindBy(id = "hp_book_now_button")
	private WebElement reserveButton;
    
    public BookingPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("Title of page : " + driver.getTitle());
        PageFactory.initElements(driver, this);
    }
    
    
    public void selectHotel() throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();
		System.out.println("After Refersh" + driver.getTitle());
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("hp_book_now_button"))));
    	WebElement reserveButton = driver.findElement(By.id("hp_book_now_button"));
    	System.out.println("text : " + reserveButton.getText()); 
    	
		reserveButton.click();
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollBy(0,1500)");
		 * 
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(
		 * "hprt_nos_select_5692715_95461013_0_2_0"))));
		 */
		/*
		 * WebElement selectRoomCount =
		 * driver.findElement(By.id("hprt_nos_select_5692715_95461013_0_2_0")); Select
		 * select = new Select(selectRoomCount); select.selectByValue("1");
		 */
    	
    	Thread.sleep(3000);
    	
    
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class=\"bui-button__text js-reservation-button__text\"]"))));
    	WebElement ContinueToBookButton = driver.findElement(By.xpath("//span[@class=\"bui-button__text js-reservation-button__text\"]"));
    	System.out.println("text : " + ContinueToBookButton.getText());
    	ContinueToBookButton.click();
    	
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.titleContains("Booking.com: Your Details"));
    	
    	System.out.println(driver.getTitle());
    }

   

}
