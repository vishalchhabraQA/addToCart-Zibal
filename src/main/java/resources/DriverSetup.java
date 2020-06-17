package resources;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {

	public WebDriver driver;
	
	String path = "src/main/java/resources/browser-drivers/chromedriver";
	
	public WebDriver initializeDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500,	TimeUnit.MILLISECONDS);
		return driver;
	}
	
	public void closeDriver() {
		driver.close();
	}
}
