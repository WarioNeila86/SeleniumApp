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
 * @author Mario Garcia
 *
 */
public class RyanairHomePage extends PageObject {
	
    @FindBy(id="flight-search-type-option-one-way")
	private WebElement oneWayButton;
	private By oneWayButtonLocation = By.id("flight-search-type-option-one-way");

	@FindBy(css="input[placeholder=\"Destination airport\"]")
	private WebElement destinationInput;
	
	@FindBy(css=".pane.right .core-list-item-rounded>span")
	private WebElement destinationAirport;
	private By destinationAirportLocation = By.cssSelector(".pane.right .core-list-item-rounded>span");
	
	@FindBy(css="input[placeholder=\"Departure airport\"]")
	private WebElement departureInput;
	
	@FindBy(css=".initial>span")
	private WebElement departureAirport;
	private By departureAirportLocation = By.cssSelector(".initial>span");
	
	@FindBy(css="[translate=\"common.buttons.lets_go\"]")
	private WebElement letsGoButton;

	private By datePickerLocation = By.className("content-box");
	
	public RyanairHomePage(WebDriver driver) {
		super(driver);
	}
	
    /**
     * Selects "One way" flights by clicking on the radio button
     */
	public void selectOneWayFlights() {
		waitForAppear(oneWayButtonLocation);
		oneWayButton.click();
	}
	
	/**
	 * Enter flight destination airport
	 * @param destination airport name
	 */
	public void enterDestination(String destination) {
		destinationInput.clear();
		destinationInput.sendKeys(destination);
		waitForAppear(destinationAirportLocation);
		destinationAirport.click();
	}
	
	/**
	 * Enter flight departure airport
	 * @param departure airport name
	 */
	public void enterDeparture(String departure) {
		departureInput.clear();
		departureInput.sendKeys(departure);
		waitForAppear(departureAirportLocation);
		departureAirport.click();
	}
	
    /**
     * Selects departure date
     * @param daysOffset number of days from today to establish flight date
     */
    public void selectFlightDate(int daysOffset) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        calendar.add(Calendar.DAY_OF_MONTH, daysOffset);
        Date departureDate = calendar.getTime();
        String departureDateString = dateFormat.format(departureDate);
        waitForAppear(datePickerLocation);
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
