package BhanuFrameworkPractice.FrameworkTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BhanuFrameworkPractice.PageObjects.Paymentpage;
import BhanuFrameworkPractice.PageObjects.ProductCatelouge;
import BhanuFrameworkPractice.PageObjects.ProductValidation;
import BhanuFrameworkPractice.PageObjects.finishPage;
import BhanuFrameworkPractice.Resources.BaseTest;

public class Standalonetset extends BaseTest{
	public  String itemtobeselected =  "Sauce Labs Backpack";
	@Test(dataProvider="getData",groups= {"PurchaseOrder"})
	public void FirstTest(HashMap<String,String> input) throws IOException {
	    
	    ProductCatelouge productcatelouge = landingPage.login(input.get("email"), input.get("password"));
	    productcatelouge.getitems();
	    ProductValidation productvalidation = productcatelouge.clickoncart();
	    List<String> itemNamesInCart = productvalidation.getProductNamesInCart();
	    List<String> expectedItemNames = new ArrayList<>(List.of(itemtobeselected));
	    Assert.assertEquals(itemNamesInCart, expectedItemNames);
	    Paymentpage paymentpage = productvalidation.checkout();
	    String pincodeAsString = String.valueOf(560010);
	    finishPage finishpage = paymentpage.paymentGateway("Bhanu", "Prakash", pincodeAsString);
	    Assert.assertEquals(finishpage.lastPage(), "Thank you for your order!");
	}

	//To check if Sauce Labs Backpack is in orders page
	@Test(dependsOnMethods= {"FirstTest"})
	public void thirdTest() throws IOException {
	    ProductCatelouge productcatelouge = landingPage.login("standard_user", "secret_sauce");
	    productcatelouge.getitems();
	    ProductValidation productvalidation = productcatelouge.clickoncart();
	    List<String> itemNamesInCart = productvalidation.getProductNamesInCart();
	    Boolean match=itemNamesInCart.contains("Sauce Labs Backpack");
	    Assert.assertTrue(match);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "standard_user");
//		map.put("password", "secret_sauce");
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "locked_out_user");
//		map1.put("password", "secret_sauce");
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\BhanuFrameworkPractice\\Data\\purchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
		
	}
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"standard_user", "secret_sauce"},{"locked_out_use
//	}
	
}
