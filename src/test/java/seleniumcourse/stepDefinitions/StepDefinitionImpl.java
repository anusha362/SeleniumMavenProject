package seleniumcourse.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumcourse.TestComponents.BaseTest;
import seleniumcourse.pageobjects.CartPage;
import seleniumcourse.pageobjects.CheckOutPage;
import seleniumcourse.pageobjects.LoginPage;
import seleniumcourse.pageobjects.OrderCompletionPage;
import seleniumcourse.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
		
		public LoginPage loginPage;
		public ProductCatalogue productCatalogue;
		public CheckOutPage checkOutPage;
		public OrderCompletionPage orderCompletionPage;
		String ownerName = "Anusha";
		String countryName ="india";
		
		@Given("User landed on Ecommerce Page")
		public void User_landed_on_Ecommerce_Page() throws IOException {
			loginPage = launchApp();
		}
		
		@Given("^Logged in with username (.+) and password (.+)$")
		public void Logged_in_with_username_and_password (String username, String password)
		{
			productCatalogue = loginPage.loginApp(username, password);
		}
		
		@When("^User add product (.+) to the Cart$")
		public void User_add_product_to_the_Cart(String productName) throws InterruptedException
		{
			List<WebElement> product = productCatalogue.getProductList();
			productCatalogue.addProductTocart(productName);
		}
		
		@And("^Checkout (.+) and submit the Order$")
		public void Checkout_and_submit_the_order(String productName) {
			CartPage cartPage = productCatalogue.goToCart();
			Boolean match = cartPage.getProductsAddedInCart(productName);
			Assert.assertTrue(match);
			checkOutPage = cartPage.goToCheckoutPage();
			checkOutPage.enterUserDetails(ownerName, countryName);
			orderCompletionPage = checkOutPage.clickSubmitButton();
		}
		
		@Then("{string} message is displayed on Confirmation Page")
		public void message_displayed_on_Confirmation_Page(String string) {
			String orderConfirmationMsg = orderCompletionPage.getOrderConfirmationMsg();
			Assert.assertTrue(orderConfirmationMsg.equalsIgnoreCase(string));
			driver.close();
		}
		
		@Then("{string} message is displayed")
		public void error_message_is_displayed(String string) {
			Assert.assertEquals(string, loginPage.getErrorMsg());
			driver.close();
		}
}

