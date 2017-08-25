package com.selenium.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *  This class represents the Ryanair homepage UI
 * @author Mario García
 *
 */
public class RyanairHomePage extends PageObject {
	
    private static final By ONE_WAY_RADIO_BUTTON = By.id("flight-search-type-option-one-way");
    private static final By DEPARTURE_AIRPORT = By.cssSelector(".initial>span");
    private static final By DESTINATION_AIRPORT = By.cssSelector(".pane.right .core-list-item-rounded>span");
    private static final By DATE_PICKER = By.className("content-box");

	// This element represents the one way flight radio button
	@FindBy(id="flight-search-type-option-one-way")
	private WebElement oneWay;

	@FindBy(css="input[placeholder=\"Destination airport\"]")
	private WebElement destinationInput;
	
	@FindBy(css=".pane.right .core-list-item-rounded>span")
	private WebElement destinationAirport;
	
	@FindBy(css="input[placeholder=\"Departure airport\"]")
	private WebElement departureInput;
	
	@FindBy(css=".initial>span")
	private WebElement departureAirport;
	
	// These properties represent the fields used to compose the departure date
	@FindBy(xpath="//*[@id=\"row-dates-pax\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/input[1]")
	private WebElement flyOutDay;
	
	@FindBy(xpath="//*[@id=\"row-dates-pax\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/input[2]")
	private WebElement flyOutMonth;
	
	@FindBy(xpath="//*[@id=\"row-dates-pax\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/input[3]")
	private WebElement flyOutYear;
	
	// This property represents the "Let's go" button
	
	@FindBy(css="[translate=\"common.buttons.lets_go\"]")
	private WebElement letsGoButton;

	public RyanairHomePage(WebDriver driver) {
		super(driver);
	}
	
//    protected ExpectedCondition<WebElement> getPageLoadCondition() {
//        return ExpectedConditions.visibilityOfElementLocated(MAIN_VIEW);
//    }

    /**
     * Selects "One way" flights by clicking on the radio button
     */
	public void selectOneWayFlights() {
		waitForAppear(ONE_WAY_RADIO_BUTTON);
		driver.findElement(ONE_WAY_RADIO_BUTTON).click();
	}
	
	/**
	 * Enter flight destination airport
	 * @param destination airport name
	 */
	public void enterDestination(String destination) {
		destinationInput.clear();
		destinationInput.sendKeys(destination);
		waitForAppear(DESTINATION_AIRPORT);
		destinationAirport.click();
	}
	
	/**
	 * Enter flight departure airport
	 * @param departure airport name
	 */
	public void enterDeparture(String departure) {
		departureInput.clear();
		departureInput.sendKeys(departure);
		waitForAppear(DEPARTURE_AIRPORT);
		departureAirport.click();
	}
	
    /**
     * Selects departure date (10 days from today).
     */
    public void selectFlightDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // Add 10 days to current date
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        Date departureDate = calendar.getTime();
        String departureDateString = dateFormat.format(departureDate);
        waitForAppear(DATE_PICKER);
        driver.findElement(By.cssSelector("[data-id=\"" + departureDateString + "\"]")).click();
    }
	
    /**
     * Clicks on Let's go button
     * @return a new instance of SelectFlightPage
     */
	public SelectFlightPage clickLetsGoButton() {
		letsGoButton.click();
		return new SelectFlightPage(driver);
	}
}
