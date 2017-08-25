package com.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


// This class implements a base Page object
// It is an abstract class that cannot be instantiated

public abstract class PageObject {
	
    private static final long TIME_LIMIT_SECONDS = 21;
    private static final long SLEEP_MILLISECONDS = 3000;
	
//    /**
//     * This method will be implemented by subclasses
//     * It will confirm that the desired web page has been loaded by waiting to a WebElement to appear
//     * 
//     * @return the WebElement is visible
//     */
//    protected abstract ExpectedCondition<WebElement> getPageLoadCondition();
    
    protected WebDriver driver;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
    /**
     * This method waits until the given element explicit appears
     *
     * @param elementLocation is the location of the element
     */
    public void waitForAppear(By elementLocation) {
        waitFor(1000);
        final WebDriverWait wait = new WebDriverWait(driver, TIME_LIMIT_SECONDS, SLEEP_MILLISECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(elementLocation));
    }
    
    /**
     * Wait method. Sometimes the execution goes too fast and some elements are not detected even using the WebDriverWait object
     * This method sleeps the execution
     *
     * @param time to sleep in milliseconds
     */
    public void waitFor(int sleepTimeMs) {
        try {
            Thread.sleep(sleepTimeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
