package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects {

	public WebDriver driver;
	
	public HomePageObjects(WebDriver driver){
		this.driver=driver;
	}
	
	By desktopLink = By.linkText("Desktops");
	public WebElement getDesktopLink() {
		return driver.findElement(desktopLink);
	}
	
	By desktopList = By.xpath("//div[@class='dropdown-menu']");
	public By getDesktopList() {
		return desktopList;
	}
	
	By macOption = By.linkText("Mac (1)");
	public WebElement getMacOption() {
		return driver.findElement(macOption);
	}	
}
