package seleniumcourse;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.AddHasLaunchApp;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumcourse.AbstractComponents.AbstractComponents;
import seleniumcourse.AbstractComponents.OrderPage;
import seleniumcourse.TestComponents.BaseTest;
import seleniumcourse.pageobjects.CartPage;
import seleniumcourse.pageobjects.CheckOutPage;
import seleniumcourse.pageobjects.LoginPage;
import seleniumcourse.pageobjects.OrderCompletionPage;
import seleniumcourse.pageobjects.ProductCatalogue;

public class OrderSubmission extends BaseTest{
	String productName = "ZARA COAT 3";
	String ownerName = "Anusha";
	String countryName ="india";
	
	@Test(dataProvider ="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = loginPage.loginApp(input.get("email"), input.get("password"));
//		************************ Get the LIST of all the PRODUCTS  **************************	
		List<WebElement> product = productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
//		************************ Get the products added in the CART  **************************		
		Boolean match = cartPage.getProductsAddedInCart(input.get("product"));
		Assert.assertTrue(match);
//		************************ Fill the user details and select payments in the checkout page **************************
		CheckOutPage checkOutPage = cartPage.goToCheckoutPage();
		checkOutPage.enterUserDetails(ownerName, countryName);
//		************************ Hit the submit Button  **************************
		OrderCompletionPage orderCompletionPage = checkOutPage.clickSubmitButton();
		String orderConfirmationMsg = orderCompletionPage.getOrderConfirmationMsg();
//		************************ Order Confirmation Message  **************************
		Assert.assertTrue(orderConfirmationMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		ProductCatalogue productCatalogue = loginPage.loginApp("ekanyes15@gmail.com", "ekanyes15AY#2025");
		OrderPage orderPage = productCatalogue.goToOrdersHistory();
		Assert.assertTrue(orderPage.getOrdersPage(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "ekanyes15@gmail.com");
//		map.put("password", "ekanyes15AY#2025");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "yesekagvi14@gmail.com");
//		map1.put("password", "yesekagvi14#AEY");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\seleniumcourse\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	******* when there are few Data then this way of dataprovider we can use ***********
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"ekanyes15@gmail.com","ekanyes15AY#2025","ZARA COAT 3"},{"yesekagvi14@gmail.com","yesekagvi14#AEY","ADIDAS ORIGINAL"}};
//	}
	
	
}
