package com.ibm.groceriespages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.GetScreenshot;

public class OrderSuccessPage {

	/*
	 * @FindBy(xpath ="//a[contains(text(),'My Account')]") WebElement myaccountEle;
	 */

	@FindBy(xpath = "//a[contains(text(),'My Orders')]")
	WebElement myOrdersEle;

	WebDriverWait wait;
	WebDriver driver;

	public OrderSuccessPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	// Method to click on MyAccount
	public void clickOMyAccount() throws IOException, InterruptedException {

		driver.findElement(By.partialLinkText("My Account")).click();
		// myaccountEle.click();
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);
		myOrdersEle.click();
	}

}
