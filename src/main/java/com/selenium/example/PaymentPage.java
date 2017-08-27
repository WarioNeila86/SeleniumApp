package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represents the payment page UI
 * @author Mario Garcia
 *
 */
public class PaymentPage extends PageObject {

	
	@FindBy(className="core-btn-secondary")
	private WebElement loginButton;
	private By loginButtonLocation = By.className("login-register");
	
	@FindBy(css="input[placeholder=\"Email address\"]")
	private WebElement emailInput;
	private By emailInputLocation = By.cssSelector("input[placeholder=\"Email address\"]");
	
	@FindBy(css="input[placeholder=\"Password\"]")
	private WebElement passwordInput;
	
	@FindBy(css="[name=\"remember\"]")
	private WebElement rememberLoginCheckbox;
	
	@FindBy(css=".modal-form-container [type=\"submit\"]")
	private WebElement submitLoginButton;
	
	@FindBy(css="[id*=title]")
	private WebElement titleSelect;
	private By titleSelectLocation = By.cssSelector("[id*=title]");
	
    @FindBy(css="[id*=firstName]")
    private WebElement firstNameInput;
    
    @FindBy(css="[id*=lastName]")
    private WebElement lastNameInput;
    
    @FindBy(css="[id*=save-to-companions]")
    private WebElement savePassengerCheckbox;
    private By savePassengerCheckboxLocation = By.cssSelector("[id*=save-to-companions]");
    
    @FindBy(css="[name=\"phoneNumberCountry\"]")
    private WebElement phoneNumberCountrySelect;
    
    @FindBy(css="input[name=\"phoneNumber\"]")
    private WebElement phoneNumberInput;
	
    @FindBy(css="[name=\"cardNumber\"]")
	private WebElement cardNumberInput;
    
    @FindBy(css="[name=\"cardType\"]")
    private WebElement cardTypeSelect;
    
    @FindBy(css="[name=\"expiryMonth\"]")
    private WebElement expiryMonthSelect;
    
    @FindBy(css="[name=\"expiryYear\"]")
    private WebElement expiryYearSelect;
    
    @FindBy(css="[name=\"securityCode\"]")
    private WebElement securityCodeInput;
    
    @FindBy(css="[name=\"cardHolderName\"]")
    private WebElement cardHolderNameInput;
    
    @FindBy(css="[name=\"billingAddressAddressLine1\"]")
    private WebElement billingAddressAddressLine1Input;
    @FindBy(css="[name=\"billingAddressAddressLine2\"]")
    private WebElement billingAddressAddressLine2Input;
    @FindBy(css="[name=\"billingAddressCity\"]")
    private WebElement billingAddressCityInput;
    @FindBy(css="[name=\"billingAddressPostcode\"]")
    private WebElement billingAddressPostcodeInput;
    @FindBy(css="[name=\"billingAddressCountry\"]")
    private WebElement billingAddressCountrySelect;
    
    @FindBy(css="[name=\"acceptPolicy\"]")
    private WebElement acceptPolicyCheckbox;
    
    @FindBy(css=".cta>button")
    private WebElement payNowButton;
    
    @FindBy(css="[ng-switch-when=\"PaymentDeclined\"]")
    private WebElement paymentDeclinedAlert;
    private By paymentDeclinedAlertLocation = By.cssSelector("[ng-switch-when=\"PaymentDeclined\"]");
    
    
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Selects login option to complete the payment 
	 */
	
	public void selectLoginOption() {
		waitForAppear(loginButtonLocation);
		loginButton.click();
	}
	
	/**
	 * Fills the input fields with login details
	 * @param username
	 * @param password
	 */
	
	public void enterLoginDetails(String username, String password) {
		waitForAppear(emailInputLocation);
		emailInput.clear();
		emailInput.sendKeys(username);
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}
	
	/**
	 * Disables "Keep me logged in" checkbox
	 */
	
	public void disableRememberCheckbox() {
		if (rememberLoginCheckbox.isSelected()) {
			rememberLoginCheckbox.click();
		}
	}
	
	/**
	 * Click on login button to complete login process
	 */
	
	public void completeLogin() {
		submitLoginButton.click();
	}
	
	/**
	 * Enter passenger details
	 * @param title
	 * @param firstName
	 * @param lastName
	 */
	
	public void enterPassengerDetails(String title, String firstName, String lastName) {
		waitForAppear(titleSelectLocation);
		Select select = new Select(titleSelect);
        select.selectByVisibleText(title);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        // Disable remember passenger checkbox
        waitForAppear(savePassengerCheckboxLocation);
        if (savePassengerCheckbox.isSelected()) {
        	savePassengerCheckbox.click();
        }
	}
	
	/**
	 * Enter phone country and phone number information
	 * @param country
	 * @param phoneNumber
	 */
	public void enterContactDetails(String country, String phoneNumber) {
		Select select = new Select(phoneNumberCountrySelect);
		select.selectByVisibleText(country);
		phoneNumberInput.sendKeys(phoneNumber);
	}
	
	public void enterCardNumber(String cardNumber) {
		cardNumberInput.sendKeys(cardNumber);
	}
	
	public void selectCardType(String cardType) {
		Select select = new Select(cardTypeSelect);
		select.selectByVisibleText(cardType);
	}
	
	public void selectExpiryMonth(String expiryMonth) {
		Select select = new Select(expiryMonthSelect);
		select.selectByVisibleText(expiryMonth);
	}
	
	public void selectExpiryYear(String expiryYear) {
		Select select = new Select(expiryYearSelect);
		select.selectByVisibleText(expiryYear);
	}
	
	public void enterSecurityCode(String securityCode) {
		securityCodeInput.sendKeys(securityCode);
	}
	
	public void enterCardHolderName(String cardHolderName) {
		cardHolderNameInput.sendKeys(cardHolderName);
	}
	
	public void enterBillingAddressDetails(String address1, String address2, String city, String postCode, String country) {
		billingAddressAddressLine1Input.sendKeys(address1);
		billingAddressAddressLine2Input.sendKeys(address2);
		billingAddressCityInput.sendKeys(city);
		billingAddressPostcodeInput.sendKeys(postCode);
		Select select = new Select(billingAddressCountrySelect);
		select.selectByVisibleText(country);
	}
	
	public void acceptTermsAndConiditions() {
		if (acceptPolicyCheckbox.isEnabled()) {
			acceptPolicyCheckbox.click();
		}
	}
	
	public void clickPayNowButton() {
		payNowButton.click();
	}
	
	public boolean isPaymentDeclined() {
		waitForAppear(paymentDeclinedAlertLocation);
		return paymentDeclinedAlert.isDisplayed();
	}

}
