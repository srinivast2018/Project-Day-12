package com.ibm.groceries;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.groceriespages.CheckOutPage;
import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.MyOrdersPage;
import com.ibm.groceriespages.OrderSuccessPage;
import com.ibm.initialization.WebDriverLaunch;
import com.ibm.utilities.DatabaseConnection;
import com.ibm.utilities.GetScreenshot;

public class PlaceOrder extends WebDriverLaunch {

	@Test(priority = 1, testName = "PlaceOrder", groups = "low")
	public void myOrders() throws IOException, InterruptedException, SQLException {
		String userPage = data.get("userPageUrl");
		String checkoutHeader = data.get("checkoutHeader");
		String text = data.get("text");
		String phoneNum = data.get("phoneNum");
		String password = data.get("pwd");
		String invalidPhoneMsg = data.get("invalidPhoneMsg");
		String fullname = data.get("fullnamevalue");
		String mailid = data.get("mailidvalue");
		String address = data.get("addressvalue");
		String pincode = data.get("pincodevalue");
		String orderMessage = data.get("orderMessage");

		// To launch Groceries user page
		driver.get(userPage);
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);

		GroceriesUserPage userpage = new GroceriesUserPage(driver, wait);
		String pageSource = userpage.gotoCheckOut();
		screen.takeScreenshot(driver);

		// Verifying checkout header is displayed
		Thread.sleep(2000);
		Assert.assertTrue(pageSource.contains(checkoutHeader));
		CheckOutPage checkObj = new CheckOutPage(driver, wait);

		// To verify the validation invalid phone number
		pageSource = checkObj.verifyinvalidPhonenumber(text, password);
		Thread.sleep(2000);
		screen.takeScreenshot(driver);
		Assert.assertTrue(pageSource.contains(invalidPhoneMsg));

		checkObj.fillMandatory(phoneNum, password);
		// To verify full name text box is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='name']")).isDisplayed());

		checkObj.enterDeliveryAddress(fullname, mailid, address, pincode);

		Thread.sleep(2000);
		// Verifying agree check box is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='tc']")).isDisplayed());

		pageSource = checkObj.confirmOrder();
		Thread.sleep(2000);

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'My
		// Account')]")));
		// Verifying for message of order success
		// Assert.assertTrue(pageSource.contains(orderMessage));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My Account")));
		screen.takeScreenshot(driver);

		// To click on MyOrders
		OrderSuccessPage orderObj = new OrderSuccessPage(driver, wait);
		orderObj.clickOMyAccount();
		screen.takeScreenshot(driver);

		MyOrdersPage myorderObj = new MyOrdersPage(driver, wait);
		String orderid = myorderObj.orderId();
		orderid = orderid.replace("#", "");
		int id = Integer.parseInt(orderid);
		String customer = myorderObj.customer();
		String noProducts = myorderObj.numberOfProducts();
		String status = myorderObj.status();
		String date_added = myorderObj.dateadded();
		System.out.println("Order Details: ");
		System.out.println(orderid);
		System.out.println(customer);
		System.out.println(noProducts);
		System.out.println(status);
		System.out.println(date_added);

		DatabaseConnection conn = new DatabaseConnection();
		Statement st = conn.connectDatabase();

		ResultSet rs = st.executeQuery("select *from as_order" + " where order_id=" + id);
		if (rs.next()) {
			Assert.assertEquals(rs.getInt("order_id"), id);
		}

	}
}
