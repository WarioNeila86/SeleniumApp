package com.selenium.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class DeclinedPaymentTest extends FunctionalTest {

	@Test
	public void test() {
		String url = "https://www.ryanair.com/ie/en/";
		String departureAirport = "DUB";
		String destinationAirport = "SXF";
        String username = "l598328@mvrht.net";
        String password = "Ryanairtest1";
        String title = "Mr";
        String firstName = "Jon";
        String lastName = "Snow";
        String phoneCountry = "Spain";
        String phoneNumber = "666666666";
        String cardNumber = "5555555555555557";
        String cardType = "MasterCard";
        String securityCode = "123";
        String expiryMonth = "12";
        String expiryYear = "2020";
        String cardHolderName = "Jon Snow";
        String address1 = "The Wall";
        String address2 = "King in the North";
        String city = "Winterfell";
        String country = "Spain";
        String postCode = "28000";
        
        driver.get(url);
        
        RyanairHomePage homePage = PageFactory.initElements(driver, RyanairHomePage.class);
        homePage.selectOneWayFlights();
        homePage.enterDeparture(departureAirport);
        homePage.enterDestination(destinationAirport);
        homePage.selectFlightDate(5);
        
        SelectFlightPage flightPage = homePage.clickLetsGoButton();
        flightPage.selectFirstFlight();
        flightPage.selectStandardFare();

        ExtrasPage extrasPage = flightPage.clickContinueButton();
        extrasPage.clickCheckOutButton();
        
        PaymentPage paymentPage = extrasPage.clickOkThanksButton();
        
        paymentPage.selectLoginOption();
        paymentPage.enterLoginDetails(username, password);
        paymentPage.disableRememberCheckbox();
        paymentPage.completeLogin();
        paymentPage.enterPassengerDetails(title,firstName,lastName);
        paymentPage.enterContactDetails(phoneCountry, phoneNumber);
        paymentPage.enterCardNumber(cardNumber);
        paymentPage.selectCardType(cardType);
        paymentPage.selectExpiryMonth(expiryMonth);
        paymentPage.selectExpiryYear(expiryYear);
        paymentPage.enterSecurityCode(securityCode);
        paymentPage.enterCardHolderName(cardHolderName);
        paymentPage.enterBillingAddressDetails(address1, address2, city, postCode, country);
        paymentPage.acceptTermsAndConiditions();
        paymentPage.clickPayNowButton();
        
		assertTrue(paymentPage.isPaymentDeclined());
	}

}
