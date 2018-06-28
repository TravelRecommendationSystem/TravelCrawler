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

public class TripAdFetch {
	public static void getDocument(WebDriver driver,String userName, String passWord) {
	try {
		driver.get("https://www.tripadvisor.com.vn/Attractions-g293925-Activities-c47-Ho_Chi_Minh_City.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	} catch (WebDriverException e) {// TH disconnect, Not found element replace nav1,TimeoutException
		System.out.println("Load page failed==============================");
	}
}
	// Click NextPage button take all links
	public static List<String> expandAllPlaces(WebDriver driver) {
		List<String> linkList = new ArrayList<String>();
		try {// Check page loaded XemThem
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
			WebElement webElement = driver.findElement(By.cssSelector("a.nav.next"));
			List<WebElement> listElements = driver.findElements(By.cssSelector("div.listing_title > a"));
			while (webElement.getText().equals("Tiếp theo")) {
				try {
					listElements = driver.findElements(By.cssSelector("div.listing_title > a"));
					for (WebElement web : listElements) {
						linkList.add(web.getAttribute("href"));
					}
					System.out.println("Hello");
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
					webElement = driver.findElement(By.cssSelector("a.nav.next"));
					webElement.click();
				} catch (Exception e) {
					// System.out.println("===========ExpandAllPlaces" + e.getMessage());
				}
			}
		} catch (WebDriverException e1) {// End not found nextpage
			System.out.println("===========ExceptionNotFoundExpandAllPlaces" + e1.getMessage());
		}
		System.out.println("===========================");
		for (int i = 0; i < linkList.size(); i++) {
			System.out.println(linkList.get(i));
		}
		return linkList;
	}

	// get LinkList
	public static List<String> getLinkList(WebDriver driver) {
		List<String> linkList = expandAllPlaces(driver);
		return linkList;
	}
}
