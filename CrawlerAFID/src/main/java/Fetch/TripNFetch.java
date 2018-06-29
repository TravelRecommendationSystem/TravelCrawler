package Fetch;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripNFetch extends TripFetch {
	private String userName;
	private String passWord;

	public TripNFetch() {
		this.setDomain("tripnow.vn");
		userName = "truongvinhtienuit@gmail.com";
		passWord = "123456";
	}

	// Login system
	public void getDocument(WebDriver driver) {
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
	public HashMap<String, String> expandAllPlaces(WebDriver driver) {
		HashMap<String, String> linkList = new HashMap<String, String>();
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
	public HashMap<String, String> getLinkList(WebDriver driver) {
		HashMap<String, String> linkList = new HashMap<String, String>();
		expandAllPlaces(driver);
		List<WebElement> elementLinkList = driver.findElements(By.className("link-absolute"));
		List<WebElement> elementImageList = driver
				.findElements(By.cssSelector(".reviewbox-img.embed.embed-foody > img"));
		for (int i = 0; i < elementLinkList.size(); i++) {

			linkList.put(elementImageList.get(i).getAttribute("href"), elementImageList.get(i).getAttribute("src"));
		}
		return linkList;
	}
}
