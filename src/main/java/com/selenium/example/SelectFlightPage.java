package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class represents the select flight page UI
 * @author Mario García
 *
 */
public class SelectFlightPage extends PageObject {
	
    private static final By FLIGHT_HEADER = By.className("flight-header");
    private static final By STANDART_FARE_SELECT_BUTTON = By.className("flight-header__min-price");
    private static final By STANDART_FARE = By.className("standard");
    private static final By SELECT_BUTTON = By.className("fare-select");
    private static final By CONTINUE_BUTTON = By.id("continue");

	private WebDriverWait wait;

	@FindBy(className="flight-header")
	private WebElement firstFlight;

	@FindBy(className="flight-header__min-price")
	private WebElement standardFareButton;
	
	@FindBy(className="standard")
	private WebElement standardFare;
	
	@FindBy(className="fare-select")
	private WebElement selectFareButton;

	@FindBy(id="continue")
	private WebElement continueButton;

	public SelectFlightPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 30);
	}

	public void waitForPricesToBeLoaded() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//flights-table")));
	}


	/**
	 * Select first available flight
	 * 
	 */
	public void selectFirstFlight() {
		System.out.println("Entering selectFirstFlight");
		waitForAppear(FLIGHT_HEADER);
		firstFlight.findElement(STANDART_FARE_SELECT_BUTTON).click();
		System.out.println("Clicking firstFlight");
	}
	
	// This method selects the standard fare of the selected flight
//	public void selectStandardFare() {
//		System.out.println("Entering selectStandardFare");
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id=\"continue\"])[1]")));
//		System.out.println("Clicking on standard fare");
//		standardFare.click();
//	}
	
    /**
     * Selects standard fare
     */
    public void selectStandardFare() {
        waitForAppear(STANDART_FARE);
        waitForAppear(SELECT_BUTTON);
        standardFare.findElement(SELECT_BUTTON).click();
    }
    
    /**
     * Click on continue button
     * @return a new instance of Extras page
     */
    
	public ExtrasPage clickContinueButton() {
        waitForAppear(CONTINUE_BUTTON);
        continueButton.click();
		return new ExtrasPage(driver);
	}

}
