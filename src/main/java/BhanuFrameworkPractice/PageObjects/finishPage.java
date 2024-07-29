package BhanuFrameworkPractice.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BhanuFrameworkPractice.AbstractComponents.AbstractComponents;

public class finishPage extends AbstractComponents{
	WebDriver driver;
	String[] itemtobeselected;
	public finishPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	@FindBy(css=".complete-header")
	WebElement message;
	public String lastPage() {
		return message.getText();
	}
}
