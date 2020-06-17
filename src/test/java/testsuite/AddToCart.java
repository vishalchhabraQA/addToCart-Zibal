package testsuite;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPageObjects;
import pageobjects.HomePageObjects;
import pageobjects.ProductPageObjects;
import resources.DriverSetup;

public class AddToCart extends DriverSetup {

	String macCount = "";
	int phonesPDACount = 0;
	String product = "iPhone";
	
	@Test()
	public void addToCartScenario() throws IOException, InterruptedException {
		
		/* Step 1: Open Google Chrome browser */
		
		driver = initializeDriver(); 
		
		/* Step 2: Go to Url: https://demo.opencart.com/ */
		
		driver.get("https://demo.opencart.com/");
		HomePageObjects h = new HomePageObjects(driver);

		/* Step 3: Mouse hover ‘Desktops’ link on navigation bar */
		
		Actions a = new Actions(driver);
		a.moveToElement(h.getDesktopLink()).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10); //Explicit wait till the list is available
		wait.until(ExpectedConditions.visibilityOfElementLocated((h.getDesktopList())));

		/* Step 4: Click on item ‘Mac’ from list and store the count of available item for further use */
		
		String txt = h.getMacOption().getText();
		macCount = txt.substring(txt.indexOf("(")+1,txt.indexOf(")"));
		
		h.getMacOption().click();
		
		/*Step 5. Verify if desired product page is open */
		
		ProductPageObjects p = new ProductPageObjects(driver);
		Assert.assertEquals(p.getTitle(), "Mac");
		
		/* Step 6: Click on ‘Add to Cart’ button for the product ‘iMac’ (Create dynamic  
		 	xpath in which youpass the product name, and also can reuse for another item) */
		
		p.getAddToCartBtn("iMac").click(); //Generalize Function 
		
		/* Step 7: Verify if in Cart button, Number of Item and price is getting reflected */
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(p.getLoadingText()));

		String cartBtnText = p.getCartTotal().getText();
		
		Assert.assertEquals(cartBtnText, "1 item(s) - $122.00");
		
		/* Step 8: Verify if link ‘Phones & PDAs’ is displayed on page */
		
		Assert.assertTrue(p.getPhonesPDA().isDisplayed());
		
	    /* Step 9: Click on link ‘Phones & PDAs’ and store the count of available item for further use */
		
		p.getPhonesPDA().click();
		phonesPDACount = p.getProductCount();
		
		/* Step 10: Verify the number of products getting displayed on page against the stored count in Step 9*/
		Assert.assertEquals(p.getProductCount(), phonesPDACount);
		
		/* Step 11: Click on ‘Add to Cart’ button for the product ‘iPhone’ (using dynamic xpath created in
				Step 5, don’t create new xpath) */
		
		p.getAddToCartBtn("iPhone").click();
		
		/* Step 12: Verify if updated item count and price on Cart button, is getting reflected */
				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(p.getLoadingText()));
		String cartBtnTextUpdated = p.getCartTotal().getText();
		
		Assert.assertEquals(cartBtnTextUpdated, "2 item(s) - $245.20");
		
		/* Step 13: Click on Cart button and verify if Added items are listed with count and price */
		
		p.getCartTotal().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(p.cartItemsPopup));
		Thread.sleep(3000);
		Assert.assertEquals(p.getCartItemsPriceAndCount().size(), 2);
		
		/* Step 14: Click on ‘View Cart’ link */
		
		p.getViewCartLink().click();
		
		/* Step 15: On Shopping cart page, Enter quantity ‘2’ for product ‘iMac’ and click on update button */
		CartPageObjects c = new CartPageObjects(driver);
		c.updateCartQuantity("iMac", "2"); //Generalize Function
		
		/* Step 16: Verify Shopping cart modification success message “Success: You have modified your
			shopping cart!” */
		
		Assert.assertEquals(c.getSuccessMessage().getText(),"Success: You have modified your shopping cart!\n" + "×");
		
		/*  Step 17: Verify if new quantity is getting displayed in quantity column for ‘iMac’   */
		
		/* Step 17: is not applicable due to, Error: Products marked with *** are not available in the desired quantity or not in stock!*/
		
		
		/* Step 18: Click on Remove button for Product Name ‘iPhone’ */
		
		c.removeItem(product);
		
		/* Step 19: Verify if cart is updated and Removed product is not listed in cart */
		
		Thread.sleep(4000);
		String result = c.verfifyRemoval(product);		
		Assert.assertEquals(result, product + " is not present in the list");
		
		/* Step 20: Verify if Cart button is updated with new item count and price */

		Assert.assertEquals(p.getCartItemsPriceAndCount().size(), 1);
		closeDriver();		
	}

}
