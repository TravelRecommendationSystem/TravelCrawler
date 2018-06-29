package Fetch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripAdFetch extends TripFetch {
	public TripAdFetch(String domain) {
		this.setDomain(domain);
	}

	public void getDocument(WebDriver driver) {
		try {
			driver.get("https://www.tripadvisor.com.vn/Attractions-g293925-Activities-c47-Ho_Chi_Minh_City.html");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (WebDriverException e) {// TH disconnect, Not found element replace nav1,TimeoutException
			System.out.println("Load page failed==============================");
		}
	}

	// Click NextPage button take all links
	public HashMap<String, String> expandAllPlaces(WebDriver driver) {
		// Create HashMap
		HashMap<String, String> linkList = new HashMap<String, String>();
		List<String> url = new ArrayList<String>();
		try {// Check page loaded XemThem
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
			WebElement webElement = driver.findElement(By.cssSelector("a.nav.next"));
			// List<WebElement> listElements =
			// driver.findElements(By.cssSelector("div.listing_title > a"));
			while (webElement.getText().equals("Tiếp theo")) {
				try {
					List<WebElement> elementLinkList = driver.findElements(By.cssSelector("div.listing_title > a"));
					List<WebElement> elementImageList = driver.findElements(By.cssSelector(".photo_link > img"));
					for (int i = 0; i < elementLinkList.size(); i++) {
						linkList.put(elementLinkList.get(i).getAttribute("href"),
								elementImageList.get(i).getAttribute("src"));
						url.add(elementLinkList.get(i).getAttribute("href"));
					}
					webElement.click();
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
					webElement = driver.findElement(By.cssSelector("a.nav.next"));
				} catch (Exception e) {

				}
			}
		} catch (WebDriverException e1) {// End not found nextpage

		}
		try {
			List<WebElement> elementLinkList = driver.findElements(By.cssSelector("div.listing_title > a"));
			List<WebElement> elementImageList = driver.findElements(By.cssSelector(".photo_link > img"));
			for (int i = 0; i < elementLinkList.size(); i++) {
				linkList.put(elementLinkList.get(i).getAttribute("href"), elementImageList.get(i).getAttribute("src"));
				url.add(elementLinkList.get(i).getAttribute("href"));
			}
		} catch (WebDriverException e) {

		}
		return linkList;
	}

	// get LinkList
	public HashMap<String, String> getLinkList(WebDriver driver) {
		HashMap<String, String> linkList = expandAllPlaces(driver);
		return linkList;
	}
}
