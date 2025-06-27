package seleniumcourse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcourse.AbstractComponents.AbstractComponents;

public class OrderCompletionPage extends AbstractComponents{
	WebDriver driver;

	public OrderCompletionPage(WebDriver driver){

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMsg;
	
	public String getOrderConfirmationMsg() {
		String orderConfirmationMsg = ConfirmationMsg.getText();
		return orderConfirmationMsg;
	}

}
