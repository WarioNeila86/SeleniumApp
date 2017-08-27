package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class represents the select flight page UI
 * @author Mario Garcia
 *
 */
public class SelectFlightPage extends PageObject {
	
    @FindBy(className="flight-header")
	private WebElement firstFlight;
	private By firstFlightLocation = By.className("flight-header"); 

	@FindBy(className="flight-header__min-price")
	private WebElement standardFareButton;
	
	private By standardFareButtonLocation = By.className("flight-header__min-price");
	
	@FindBy(className="standard")
	private WebElement standardFare;
	private By standardFareLocation = By.className("standard");
	
	private By selectFareButtonLocation = By.className("fare-select");

	@FindBy(id="continue")
	private WebElement continueButton;
	private By continueButtonLocation = By.id("continue");

	public SelectFlightPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Select first available flight
	 * 
	 */
	public void selectFirstFlight() {
		System.out.println("Entering selectFirstFlight");
		waitForAppear(firstFlightLocation);
		firstFlight.findElement(standardFareButtonLocation).click();
		System.out.println("Clicking firstFlight");
	}
	
    /**
     * Selects standard fare
     */
    public void selectStandardFare() {
        waitForAppear(standardFareLocation);
        waitForAppear(selectFareButtonLocation);
        standardFare.findElement(selectFareButtonLocation).click();
    }
    
    /**
     * Click on continue button
     * @return a new instance of Extras page
     */
    
	public ExtrasPage clickContinueButton() {
        waitForAppear(continueButtonLocation);
        continueButton.click();
		return new ExtrasPage(driver);
	}

}
