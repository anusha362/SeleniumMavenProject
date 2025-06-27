package seleniumcourse;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumcourse.pageobjects.LoginPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// new comments are added
		String productName = "ZARA COAT 3";
		String ownerName = "Anusha";
		String countryName ="india";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("ekanyes15@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("ekanyes15AY#2025");
		driver.findElement(By.id("login")).click();
		List<WebElement> product =driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod =product.stream().filter(products->products.findElement(By.cssSelector("b")).
				getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> productsAddedInCart =driver.findElements(By.cssSelector(".infoWrap h3"));
		Boolean match = productsAddedInCart.stream().anyMatch(cartProd-> cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card ')]/following-sibling::input")).sendKeys(ownerName);
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(countryName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group-item")));
		List<WebElement> listOfcountries = driver.findElements(By.cssSelector(".list-group-item"));
		listOfcountries.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().ifPresentOrElse(WebElement::click, () -> System.out.println("No matching element found"));
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		String orderConfirmationMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirmationMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

}
