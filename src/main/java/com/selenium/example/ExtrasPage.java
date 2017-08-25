package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** 
 * This class represents the extras selection page UI
 * @author Mario García
 *
 */
public class ExtrasPage extends PageObject {
	
	private static final By CHECK_OUT_BUTTON = By.cssSelector(".trips-cnt>button");
	private static final By OK_THANKS_BUTTON = By.cssSelector("button.core-btn-ghost.seat-prompt-popup-footer-btn");
	
	@FindBy(css=".trips-cnt>button")
	private WebElement checkOutButton;
	
	@FindBy(css="button.core-btn-ghost.seat-prompt-popup-footer-btn")
	private WebElement okThanksButton;

	public ExtrasPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Click on check out button
	 */
	
	public void clickCheckOutButton() {
		waitForAppear(CHECK_OUT_BUTTON);
		checkOutButton.click();
	}
	
	/**
	 * Closes seat reservation popup by clicking "Ok, thanks" button
	 * 
	 * @return a new instance of PaymentPage
	 */
	public PaymentPage clickOkThanksButton() {
		waitForAppear(OK_THANKS_BUTTON);
		okThanksButton.click();
		return new PaymentPage(driver);
	}

}
