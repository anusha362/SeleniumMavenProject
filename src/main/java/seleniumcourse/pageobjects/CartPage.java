package seleniumcourse.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcourse.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;

	public CartPage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".infoWrap h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".subtotal button")
	WebElement checkOutButton;
	
	public Boolean getProductsAddedInCart(String productName) {
		Boolean match = productsInCart.stream().anyMatch(cartProd-> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckoutPage() {
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
	
}
