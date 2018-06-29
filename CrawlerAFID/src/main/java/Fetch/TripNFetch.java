package Fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripNFetch extends TripFetch {
	public TripNFetch(String domain) {
		this.setDomain(domain);
	}

	// Login system
	public void getDocument(WebDriver driver, String userName, String passWord) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			driver.get("https://www.tripnow.vn/ho-chi-minh/travel/tim-kiem?q=&ds=dia-diem&dtids=696,694,699,1,12,14,5");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-user-name")));
			driver.findElement(By.className("nav-user-name")).click();
			driver.findElement(By.id("Email")).sendKeys(userName);
			driver.findElement(By.id("Password")).sendKeys(passWord);
			driver.findElement(By.id("bt_submit")).click();
		} catch (WebDriverException e) {// TH disconnect, Not found element replace nav1,TimeoutException
			System.out.println("LoginSystem================");
		}
	}

	// Click XemThem button if exist XemThem button
	public List<String> expandAllPlaces(WebDriver driver) {
		List<String> linkList = new ArrayList<String>();
		try {// Check page loaded XemThem
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Xem thêm")));
			WebElement webElement = driver.findElement(By.linkText("Xem thêm"));
			while (webElement.getText().equals("Xem thêm")) {
				try {
					webElement.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					webElement = driver.findElement(By.linkText("Xem thêm"));
				} catch (Exception e) {
					System.out.println("===========ExpandAllPlaces" + e.getMessage());
				}
			}
		} catch (WebDriverException e1) {
			System.out.println("===========TimeoutExpandAllPlaces" + e1.getMessage());
		}
		return linkList;
	}

	// get LinkList
	public List<String> getLinkList(WebDriver driver) {
		List<String> linkList = new ArrayList<String>();
		expandAllPlaces(driver);
		List<WebElement> listWebElement = driver.findElements(By.className("link-absolute"));
		for (WebElement web : listWebElement) {
			linkList.add(web.getAttribute("href"));
		}
		return linkList;
	}
}
