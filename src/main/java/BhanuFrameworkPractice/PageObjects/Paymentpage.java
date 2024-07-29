package BhanuFrameworkPractice.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BhanuFrameworkPractice.AbstractComponents.AbstractComponents;


	public class Paymentpage extends AbstractComponents{
		WebDriver driver;
		
		public Paymentpage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			
		PageFactory.initElements(driver, this);
		}
		
		
//		driver.findElement(By.cssSelector("#checkout")).click();
//		driver.findElement(By.name("firstName")).sendKeys("Bhanu");
//		driver.findElement(By.id("last-name")).sendKeys("Prakash");
//		driver.findElement(By.cssSelector("#postal-code")).sendKeys("560010");
//		driver.findElement(By.cssSelector(".submit-button")).click();
//		driver.findElement(By.id("finish")).click();
		@FindBy(id="last-name")
		WebElement lastName;
		@FindBy(name="firstName")
		WebElement firstName;
		@FindBy(css="#postal-code")
		WebElement pinCode;
		@FindBy(css=".submit-button")
		WebElement submitbutton;
		@FindBy(id="finish")
		WebElement finish;
		public finishPage paymentGateway(String enterfirstname,String lastname,String pinCode) {
			
			firstName.sendKeys(enterfirstname);
			lastName.sendKeys(lastname);
			this.pinCode.sendKeys(pinCode);
			submitbutton.click();
			finish.click();
			finishPage finishpage=new finishPage(driver);
			return finishpage;
		
			
			
			
			
		}
		
		
}
