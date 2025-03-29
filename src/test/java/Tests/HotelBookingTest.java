package Tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	}

	@Test
	public void testHotelBookingWithCoupon() throws InterruptedException {

		HomePage homePage = new HomePage(driver);
		homePage.searchHotels("New York", "2025-04-10", "2025-04-15");

		SearchResultsPage resultsPage = new SearchResultsPage(driver);
		resultsPage.selectFirstHotel();

		BookingPage bookingPage = new BookingPage(driver);
		bookingPage.selectHotel();

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.proceedToCheckout("Apoorva", "Ranjan", "appyranjan11@gmail.com", "India", "9876543210");

		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.applyCoupon("SUMMER25");

		Assert.assertTrue(paymentPage.isDiscountApplied(), "Discount was not applied!");

		Assert.assertTrue(paymentPage.reviewBookingDetails("Thu, Apr 10, 2025", "Tue, Apr 15, 2025"), "Incorrect date");

	}

}
