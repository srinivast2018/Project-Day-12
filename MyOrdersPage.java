package com.ibm.groceriespages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyOrdersPage {

	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[1]")
	WebElement orderIdEle;
	
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[2]")
	WebElement customerEle;
	
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[3]")
	WebElement noProductsEle;
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[4]")
	WebElement statusEle;
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[6]")
	WebElement dateaddedEle;
	
	WebDriverWait wait;
	WebDriver driver;

	public MyOrdersPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}
	
	
	
	public String orderId()
	{
		String orderid=orderIdEle.getText().toString();
		return orderid;
	}

	public String customer()
	{
		String customer=customerEle.getText().toString();
		return customer;
	}

	
	public String numberOfProducts()
	{
		String noProducts=noProductsEle.getText().toString();
		return noProducts;
	}

	public String status()
	{
		String status=statusEle.getText().toString();
		return status;
	}

	public String dateadded()
	
	{
		String date1=dateaddedEle.getText().toString();
		return date1;
	}
}
