package BhanuFrameworkPractice.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import BhanuFrameworkPractice.AbstractComponents.AbstractComponents;

public class ProductValidation extends AbstractComponents{
	WebDriver driver;
	String itemtobeselected;
	public ProductValidation(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.itemtobeselected=itemtobeselected;
	PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css=".inventory_item_name")
	List<WebElement> itemsincart;
	@FindBy(css="#checkout")
	WebElement checkput;
	public List<String> getProductNamesInCart() {
		
		waitForElementsToAppear(itemsincart);
		
		return itemsincart.stream().map(WebElement::getText).collect(Collectors.toList());
		
		
	}
	
	public Paymentpage checkout() {
		checkput.click();
		Paymentpage paymentpage=new Paymentpage(driver);
		return paymentpage;
	}
}
