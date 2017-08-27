package com.selenium.stepDefinition;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.selenium.example.ExtrasPage;
import com.selenium.example.PaymentPage;
import com.selenium.example.RyanairHomePage;
import com.selenium.example.SelectFlightPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {

	private WebDriver driver;
	private RyanairHomePage homePage;
	private SelectFlightPage flightPage;
	private ExtrasPage extrasPage;
	private PaymentPage paymentPage;
	
	public TestSteps() {
		
	}
	
	@Before
	public void initializeChromeDriver( ) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
	}
	
	@Given("^I make a flight search from \"([^\"]*)\" to \"([^\"]*)\" for one adult (\\d+) days after today$")
	public void i_make_a_flight_search_from_to_for_one_adult_days_after_today(String departureAirport, String destinationAirport, int daysOffset) {
		String url = "https://www.ryanair.com/ie/en/";
		driver.get(url);
        
        homePage = PageFactory.initElements(driver, RyanairHomePage.class);
        homePage.selectOneWayFlights();
        homePage.enterDeparture(departureAirport);
        homePage.enterDestination(destinationAirport);
        homePage.selectFlightDate(daysOffset);
        flightPage = homePage.clickLetsGoButton();
	}

	@Given("^I select first available flight with standard fare$")
	public void i_select_first_available_flight_with_standard_fare() {
		flightPage.selectFirstFlight();
        flightPage.selectStandardFare();
        extrasPage = flightPage.clickContinueButton();
        extrasPage.clickCheckOutButton();
        paymentPage = extrasPage.clickOkThanksButton();
	}

	@Given("^I login to Ryanair site using username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_to_Ryanair_site_using_username_and_password(String username, String password) {
		paymentPage.selectLoginOption();
        paymentPage.enterLoginDetails(username, password);
        paymentPage.disableRememberCheckbox();
        paymentPage.completeLogin();
	}

	@Given("^I enter payment details with card number \"([^\"]*)\", expiration month \"([^\"]*)\", expiration year \"([^\"]*)\" and security code \"([^\"]*)\"$")
	public void i_enter_payment_details_with_card_number_expiration_month_expiration_year_and_security_code(String cardNumber, String expiryMonth, String expiryYear, String securityCode) {
		String title = "Mr";
		String firstName = "Jon";
        String lastName = "Snow";
        String phoneCountry = "Spain";
        String phoneNumber = "666666666";
        String cardType = "MasterCard";
        String cardHolderName = "Jon Snow";
        String address1 = "The Wall";
        String address2 = "King in the North";
        String city = "Winterfell";
        String country = "Spain";
        String postCode = "28000";
		paymentPage.enterPassengerDetails(title,firstName,lastName);
        paymentPage.enterContactDetails(phoneCountry, phoneNumber);
        paymentPage.enterCardNumber(cardNumber);
        paymentPage.selectCardType(cardType);
        paymentPage.selectExpiryMonth(expiryMonth);
        paymentPage.selectExpiryYear(expiryYear);
        paymentPage.enterSecurityCode(securityCode);
        paymentPage.enterCardHolderName(cardHolderName);
        paymentPage.enterBillingAddressDetails(address1, address2, city, postCode, country);
	}

	@When("^I complete payment$")
	public void i_complete_payment() {
		paymentPage.acceptTermsAndConiditions();
        paymentPage.clickPayNowButton();
	}

	@Then("^I get payment declined message$")
	public void i_get_payment_declined_message() {
		assertTrue(paymentPage.isPaymentDeclined());
	}

	@After
	public void closeBrowser() {
		driver.close();
	}

}
