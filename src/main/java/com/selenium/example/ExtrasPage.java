package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** 
 * This class represents the extras selection page UI
 * @author Mario Garcia
 *
 */
public class ExtrasPage extends PageObject {
	
	@FindBy(css=".trips-cnt>button")
	private WebElement checkOutButton;
	private By checkOutButtonLocation = By.cssSelector(".trips-cnt>button");
	
	@FindBy(css="button.core-btn-ghost.seat-prompt-popup-footer-btn")
	private WebElement okThanksButton;
	private By okThanksButtonLocation = By.cssSelector("button.core-btn-ghost.seat-prompt-popup-footer-btn");

	public ExtrasPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Click on check out button
	 */
	
	public void clickCheckOutButton() {
		waitForAppear(checkOutButtonLocation);
		checkOutButton.click();
	}
	
	/**
	 * Closes seat reservation popup by clicking "Ok, thanks" button
	 * 
	 * @return a new instance of PaymentPage
	 */
	public PaymentPage clickOkThanksButton() {
		waitForAppear(okThanksButtonLocation);
		okThanksButton.click();
		return new PaymentPage(driver);
	}

}
