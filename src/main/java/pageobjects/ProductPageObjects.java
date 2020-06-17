package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPageObjects {

	public WebDriver driver;
	
	public ProductPageObjects(WebDriver driver){
		this.driver=driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public WebElement getAddToCartBtn(String inputProduct) {
		
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-thumb']//div[@class='caption']/h4/a"));
		for(int i=0; i<=productList.size();i++) {
			String name = productList.get(i).getText();
			WebElement product = productList.get(i);
			if(name.contains(inputProduct)) {	
				List<WebElement> addToCartBtns = driver.findElements(By.xpath("//div[@class='button-group']/button[1]"));
				return addToCartBtns.get(i);
			}
		}
		return null;
	}
	

	public By cartTotal = By.id("cart-total");
	public WebElement getCartTotal() {
		return driver.findElement(cartTotal);
	}
	
	public By getLoadingText() {
		return By.xpath("//*[text()='Loading...']");
	}
	
	public By PhonesPDA = By.linkText("Phones & PDAs");
	public WebElement getPhonesPDA() {
		return driver.findElement(PhonesPDA);
	}
	
	public int getProductCount() {
		
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-thumb']"));
		return productList.size();
	}
	
	public By cartItemsPopup = By.xpath("//ul[@class='dropdown-menu pull-right']");
	public WebElement getcartItemsPopup() {
		return driver.findElement(cartItemsPopup);
	}
	
	public By cartItemsCount = By.xpath("//ul[@class='dropdown-menu pull-right']/li/table/tbody/tr/td[3]");
	public WebElement getcartItemsCount() {
		return driver.findElement(cartItemsCount);
	}
	
	public By cartItemsPrice = By.xpath("//ul[@class='dropdown-menu pull-right']/li/table/tbody/tr/td[4]");
	public WebElement getcartItemsPrice() {
		return driver.findElement(cartItemsPrice);
	}
	
	public ArrayList<String> getCartItemsPriceAndCount() {
		
		List<WebElement> cartItemRows = driver.findElements(By.xpath("//ul[@class='dropdown-menu pull-right']/li/table/tbody/tr"));
		ArrayList<String> items = new ArrayList<String>();
		for(int i=0; i<cartItemRows.size();i++) {
			String itemCount = cartItemRows.get(i).findElement(By.xpath("//td[3]")).getText();
			items.add(itemCount);
			String itemPrice = cartItemRows.get(i).findElement(By.xpath("//td[4]")).getText();
		}
		return items;
	}
	
	
	
	public By viewCartLink = By.cssSelector("a[href*='checkout/cart']");
	public WebElement getViewCartLink() {
		return driver.findElement(viewCartLink);
	}
	
	public WebElement updateCartQuantity(String inputProduct, String quantity) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size();i++) {
			String name = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+(i+1)+"]/td[2]/a")).getText();
			if(name.contains(inputProduct)) {
				cartRows.get(i).findElement(By.xpath("//td[4]/div/input")).clear();
				cartRows.get(i).findElement(By.xpath("//td[4]/div/input")).sendKeys(quantity);
				cartRows.get(i).findElement(By.xpath("//td[4]/div/span/button")).click();
			}
		}
		return null;
	}

	public By successMessage = By.cssSelector("div[class*='alert-success']");
	public WebElement getSuccessMessage() {
		return driver.findElement(successMessage);
	}

	
	public WebElement removeItem(String inputProduct) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size();i++) {
			String name = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+(i+1)+"]/td[2]/a")).getText();
			if(name.contains(inputProduct)) {
				cartRows.get(i).findElement(By.xpath("//td[4]/div/span/button[2]")).click();
			}
		}
		return null;
	}

	public String verfifyRemoval(String inputProduct) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size(); i++) {
			String name = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+(i+1)+"]/td[2]/a")).getText();
			
			if(name.contains(inputProduct)) {
				return inputProduct + " is still present in the list";
			}
			else {
				return inputProduct + " is not present in the list";			
			}
		}
		return null;
	}
	
}
