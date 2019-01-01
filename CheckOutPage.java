package com.ibm.groceriespages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ibm.utilities.GetScreenshot;

public class CheckOutPage {

	@FindBy(xpath = "//input[@id='pnum']")
	WebElement phonenumEle;

	// To locate password
	@FindBy(xpath = "//input[@id='pword']")
	WebElement passwordEle;

	// To locate Next button
	@FindBy(xpath = "//button[@id='next-id']")
	WebElement nextEle;

	// To locate full name
	@FindBy(xpath = "//input[@id='name']")
	WebElement fullnameEle;

	// To locate email
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailEle;

	// To locate address
	@FindBy(xpath = "//textarea[@id='address']")
	WebElement addressEle;

	// To locate city drop down
	@FindBy(xpath = "//select[@id='city']")
	WebElement cityEle;

	// To locate pin code
	@FindBy(xpath = "//input[@id='pincode']")
	WebElement pincodeEle;

	// To locate home or office drop down
	@FindBy(xpath = "//select[@id='type']")
	WebElement placeEle;

	// To locate Continue Payment button
	@FindBy(xpath = "//a[text()='Continue to payment']")
	WebElement continuepaymnetEle;

	// To locate agree check box
	@FindBy(xpath = "//input[@id='tc']")
	WebElement agreeEle;

	// To locate Confirm order button
	@FindBy(xpath = "//a[@id='confirm-order-id']")
	WebElement confirmorderEle;

	WebDriverWait wait;
	WebDriver driver;

	public CheckOutPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	// Verify the validation message for invalid phone number field
	public String verifyinvalidPhonenumber(String text, String password) {
		phonenumEle.sendKeys(text);
		passwordEle.sendKeys(password);
		nextEle.click();
		return driver.getPageSource();

	}

	public void fillMandatory(String phoneNum, String password) {
		phonenumEle.clear();
		passwordEle.clear();
		phonenumEle.sendKeys(phoneNum);
		passwordEle.sendKeys(password);
		nextEle.click();
	}

	public void enterDeliveryAddress(String fullname, String mailid, String address, String pincode)
			throws IOException, InterruptedException {
		fullnameEle.clear();
		emailEle.clear();
		addressEle.clear();

		fullnameEle.sendKeys(fullname);
		emailEle.sendKeys(mailid);
		addressEle.sendKeys(address);

		// To select city
		Select citySelect = new Select(cityEle);
		citySelect.selectByIndex(10);

		// To enter pin code
		pincodeEle.clear();
		pincodeEle.sendKeys(pincode);

		// To select place
		Select placeSelect = new Select(placeEle);
		placeSelect.selectByIndex(0);

		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);

		// To click on continue to payment button
		continuepaymnetEle.click();
	}

	public String confirmOrder() {
		agreeEle.click();
		confirmorderEle.click();
		return driver.getPageSource();
	}

}
