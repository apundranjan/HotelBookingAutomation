package Tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Base.BaseTest;
import Pages.BookingPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.PaymentPage;
import Pages.SearchResultsPage;
import utility.ScreenshotUtil;

public class HotelBookingTest extends BaseTest {

	@BeforeMethod
	public void setupTest() {
		clearOldOutput(); // Delete old files before test execution
		ScreenshotUtil.clearOldScreenshots(); // Clear old screenshots
		test = extent.createTest("Book Hotel Test");
	}

	@Test
	public void testHotelBookingWithCoupon() throws InterruptedException {

		HomePage homePage = new HomePage(driver);
		homePage.searchHotels("New York", "2025-04-10", "2025-04-15");
		test.log(Status.INFO, " Navigated to HomePage and searched hotels in New York from 10th April to 15th April 2025 ");

		SearchResultsPage resultsPage = new SearchResultsPage(driver);
		resultsPage.selectFirstHotel();
		test.log(Status.INFO, " Clicked on the first hotel found ");

		BookingPage bookingPage = new BookingPage(driver);
		bookingPage.selectHotel();
		test.log(Status.INFO, " Reserved one room ");

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.proceedToCheckout("Apoorva", "Ranjan", "appyranjan11@gmail.com", "India", "9876543210");
		test.log(Status.INFO, " Entered checkout details ");

		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.applyCoupon("SUMMER25");
		test.log(Status.INFO, " Applied discount SUMMER25 ");

		Assert.assertTrue(paymentPage.isDiscountApplied(), "Discount was not applied!");
		test.log(Status.PASS, " Applied discount SUMMER25 is invalid ");

		Assert.assertTrue(paymentPage.reviewBookingDetails("Thu, Apr 10, 2025", "Tue, Apr 15, 2025"), "Incorrect date");
		test.log(Status.PASS, " Verified the booking dates and booked ");

	}

}
