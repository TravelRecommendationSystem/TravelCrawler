package Fetch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DocumentDriver {
	public static WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", "/home/tienbui/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
}
