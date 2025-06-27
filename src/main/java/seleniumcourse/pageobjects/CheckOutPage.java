package seleniumcourse.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcourse.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;

	public CheckOutPage(WebDriver driver){

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Name on Card ')]/following-sibling::input")
	WebElement enterName;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement enterCountry;
	
	@FindBy(css=".list-group-item")
	List<WebElement> listOfcountries;
	
	@FindBy(css=".btnn.action__submit")
	WebElement submitButton;
	
	By listCountries = By.cssSelector(".list-group-item");
	
	public void enterUserDetails(String ownerName, String countryName) {
		enterName.sendKeys(ownerName);
		enterCountry.sendKeys(countryName);
		waitForElementToAppear(listCountries);
		listOfcountries.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).
		findFirst().ifPresentOrElse(WebElement::click, () -> System.out.println("No matching element found"));
	}
	
	public OrderCompletionPage clickSubmitButton() 
	{
		submitButton.click();
		return new OrderCompletionPage(driver);
	}
	

	

}

