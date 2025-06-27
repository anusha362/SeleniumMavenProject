package seleniumcourse;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.AddHasLaunchApp;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumcourse.TestComponents.BaseTest;
import seleniumcourse.TestComponents.Retry;
import seleniumcourse.pageobjects.CartPage;
import seleniumcourse.pageobjects.CheckOutPage;
import seleniumcourse.pageobjects.LoginPage;
import seleniumcourse.pageobjects.OrderCompletionPage;
import seleniumcourse.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void validateErrorMsg() throws IOException, InterruptedException {

		loginPage.loginApp("ekanyes15@gmail.com", "ekanyes15");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMsg());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = loginPage.loginApp("yesekagvi14@gmail.com", "yesekagvi14#AEY");
		List<WebElement> product = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.getProductsAddedInCart("ZARA COAT 33");
		Assert.assertFalse(match);

	}
}
