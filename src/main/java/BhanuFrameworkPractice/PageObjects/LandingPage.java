package BhanuFrameworkPractice.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BhanuFrameworkPractice.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	String itemtobeselected;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.itemtobeselected = itemtobeselected;
	PageFactory.initElements(driver, this);
	}
	@FindBy(id="user-name")
	WebElement username;
	@FindBy(id="password")
	WebElement password;
	@FindBy(className="submit-button")
	WebElement Submit;
	//button[@class='error-button']
	@FindBy(css=".error-message-container.error-message-container.error")
	WebElement errorMessage;
	public ProductCatelouge login(String userid,String Secretkey) {
		username.sendKeys(userid);
		password.sendKeys(Secretkey);
		Submit.click();
		ProductCatelouge productcatelouge=new ProductCatelouge(driver);
		return productcatelouge;
	}
//driver.findElement(By.id("user-name")).sendKeys("standard_user");
//driver.findElement(By.id("password")).sendKeys("secret_sauce");
//driver.findElement(By.className("submit-button")).click();
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
	
	public String errorMessagevalidation() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
		
				}
	
	
	
	
	
}