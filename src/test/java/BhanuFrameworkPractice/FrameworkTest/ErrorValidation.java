package BhanuFrameworkPractice.FrameworkTest;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BhanuFrameworkPractice.PageObjects.ProductCatelouge;
import BhanuFrameworkPractice.PageObjects.ProductValidation;
import BhanuFrameworkPractice.Resources.BaseTest;
import BhanuFrameworkPractice.Resources.RetryAnalyser;

public class ErrorValidation extends BaseTest{
	String itemtobeselected="Sauce Labs Backpack";
	@Test(groups= {"errorHandling"},retryAnalyzer=RetryAnalyser.class)
	public void secondTest() throws IOException {
	    //String[] itemtobeselected = { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket" };
	    //LandingPage landingpage = launchapplication();
		landingPage.login("standard_user", "secret_sauc");
	   Assert.assertEquals(landingPage.errorMessagevalidation(), "Epic sadface: Username and password do not match any user in this service");
	   
	}
	
	@Test(groups= {"errorHandling"})
	public void fourthTest() throws IOException {
	   // String[] itemtobeselected = { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket" };
	    //LandingPage landingpage = launchapplication();
	    ProductCatelouge productcatelouge = landingPage.login("standard_user", "secret_sauce");
	    productcatelouge.getitems();
	    ProductValidation productvalidation = productcatelouge.clickoncart();
	    List<String> itemNamesInCart = productvalidation.getProductNamesInCart();
	    Boolean match=itemNamesInCart.contains("Sauce Labs Backpack");
	    Assert.assertTrue(match);
	}
}
