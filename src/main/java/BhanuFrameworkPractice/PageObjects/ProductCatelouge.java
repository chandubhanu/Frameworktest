package BhanuFrameworkPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BhanuFrameworkPractice.AbstractComponents.AbstractComponents;

public class ProductCatelouge extends AbstractComponents{
	WebDriver driver;
	String itemtobeselected;
	public ProductCatelouge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		this.itemtobeselected = itemtobeselected;
	PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//div[@class='inventory_item_name ']")
	List<WebElement> products;
	By cart=By.xpath(".//ancestor::div[@class='inventory_item']//button[@class='btn btn_primary btn_small btn_inventory ']");
	@FindBy(css=".shopping_cart_link")
	WebElement clickoncart;
	public List<WebElement> addToCart() {
		waitForElementsToAppear(products);
		return products;
		
	}
	
	public void getitems() {
	 
			WebElement prod=addToCart().stream().filter(product -> product.getText().equalsIgnoreCase("Sauce Labs Backpack")).findFirst()
				.orElse(null);
			WebElement addtocart=prod.findElement(cart);
			elementToBeClickable(addtocart);
			addtocart.click();
			
		
	}
	
	public ProductValidation clickoncart() {
		clickoncart.click();
		ProductValidation productvalidation=new ProductValidation(driver);
		return productvalidation;
	}
	
}
