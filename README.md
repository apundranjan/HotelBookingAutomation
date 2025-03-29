# Selenium POM Framework with Extent Reports
This repository contains a **Page Object Model (POM)** framework integrated with ExtentReports for reporting for detailed test reporting.

## Features:
✅ Page Object Model (POM) – Improves test maintainability.
✅ Selenium WebDriver – Automates web applications.
✅ TestNG – Manages test execution and assertions.
✅ Extent Reports – Provides interactive HTML reports.
✅ Screenshot Capture on Failure – Helps in debugging.
✅ Maven Integration – Handles dependencies and test execution. 


## Setup Instructions:
1. Clone the repo:
   git clone https://github.com/your-username/SeleniumPOM_ExtentReports.git

**Project structure**
HotelAutomationAutomation
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BaseTest.java                # Initializes WebDriver & configurations
│   │   │   ├── pages/
│   │   │   │   ├── HotelBookingPage.java        # Page class for hotel booking scenarios
│   │   │   │   ├── HomePage.java 
│   │   │   │   ├── SeacrhResultsPage.java 
│   │   │   │   ├── BookingPage.java 
│   │   │   │   ├── CheckoutPage.java 
│   │   │   │   ├── PaymentPage.java 
│   │   │   ├── utilities/
│   │   │   │   ├── ScreenshotUtils.java         # Captures screenshots on failure
│   │   │   ├── listeners/
│   │   │   │   ├── TestListener.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/
│   │   │   │   ├── HotelBookingTest.java        # Test class for hotel booking
│-- test-output/                                 # Stores generated test reports
│   ├── screenshots/                             # Stores failure screenshots
│-- pom.xml                                      # Maven dependencies & plugins
│-- testng.xml                                   # Test suite configuration
│-- README.md                                    # Project details
│-- .gitignore                                   # Files to be ignored in Git


Solved problem: An automated test script using Selenium with Java for the following scenario:
  Open the hotel booking application.
  Search for hotels in “New York” for the dates April 10 - April 15.
  Select the first hotel from the search results.
  Apply the coupon code "SUMMER25".
  Verify that the discount is applied correctly.
  Proceed to checkout but do not complete payment.


Done by Apoorva Ranjan


