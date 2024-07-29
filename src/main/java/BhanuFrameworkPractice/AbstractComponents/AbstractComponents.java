package BhanuFrameworkPractice.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	PageFactory.initElements(driver, this);
	this.wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	public void waitForElementToAppear(WebElement FindBy) {
		wait.until(ExpectedConditions.visibilityOf(FindBy));
			}
	public void waitForElementsToAppear(List<WebElement> FindBy) {
wait.until(ExpectedConditions.visibilityOfAllElements(FindBy));
	}
	public void elementToBeClickable(WebElement cart) {
		wait.until(ExpectedConditions.elementToBeClickable(cart));
	}
	
}
