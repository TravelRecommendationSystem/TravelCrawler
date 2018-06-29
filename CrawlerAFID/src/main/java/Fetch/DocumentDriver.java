package Fetch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import MongoDB.MyConstants;

public class DocumentDriver {
	public static WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", MyConstants.LINK_CONNECT_GECKODRIVER);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
}
