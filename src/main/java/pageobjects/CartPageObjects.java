package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPageObjects {

	public WebDriver driver;
		
	public CartPageObjects(WebDriver driver){
		this.driver=driver;
	}
	
	public void updateCartQuantity(String inputProduct, String quantity) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size();i++) {
			String name = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+(i+1)+"]/td[2]/a")).getText();
			if(name.contains(inputProduct)) {
				cartRows.get(i).findElement(By.xpath("//td[4]/div/input")).clear();
				cartRows.get(i).findElement(By.xpath("//td[4]/div/input")).sendKeys(quantity);
				cartRows.get(i).findElement(By.xpath("//td[4]/div/span/button")).click();
			}
		}
	}

	public By successMessage = By.cssSelector("div[class*='alert-success']");
	public WebElement getSuccessMessage() {
		return driver.findElement(successMessage);
	}

	
	public void removeItem(String inputProduct) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size();i++) {
			String name = driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+(i+1)+"]/td[2]/a")).getText();
			if(name.contains(inputProduct)) {
				cartRows.get(i).findElement(By.xpath("//td[4]/div/span/button[2]")).click();
			}
		}
	}

	public String verfifyRemoval(String inputProduct) {
		
		List<WebElement> cartRows = driver.findElements(By.xpath("//*[@id='content']/form/div/table/tbody/tr"));
		for(int i=0; i<cartRows.size();i++) {
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
