package Parse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCrawl {
	private static List<String> listLinks;

	// Login system

	public static void LoginSystem(WebDriver driver, String userName, String passWord) {
		try {
			driver.get("https://www.tripnow.vn/ho-chi-minh/travel/tim-kiem?q=&ds=dia-diem&dtids=696,694,699,1,12,14,5");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.className("nav-user-name")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("Email")).sendKeys(userName);
			driver.findElement(By.id("Password")).sendKeys(passWord);
			driver.findElement(By.id("bt_submit")).click();
		} catch (WebDriverException e) {// TH disconnect, Not found element replace nav1,TimeoutException
			System.out.println("LoginSystem================");
		}

	}

	// Click XemThem button if exist XemThem button
	public static void ExpandAllPlaces(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			try {// Check page loaded XemThem
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Xem thêm")));
				WebElement webElement = driver.findElement(By.linkText("Xem thêm"));
				while (webElement != null) {
					try {
						webElement.click();
						Thread.sleep(3000);
					} catch (Exception e) {
						System.out.println("===========ExpandAllItem" + e.getMessage());
						break;
					}
				}
			} catch (WebDriverException e1) {
				System.out.println("===========TimeoutExpandAllItem" + e1.getMessage());
			}

		} catch (WebDriverException e1) {// Not found elements TimeoutException
			System.out.println("Not found elementExpandAllItem");
		}

	}

	// crawl page content
	public static void crawlPage(WebDriver driver) {
		// use for other website don't have some column
		try {
			WebElement placeName = driver.findElement(By.className("place1-name"));
			System.out.println(placeName.getText());
		} catch (WebDriverException e) {
			System.out.println("Not found element place-name");
		}

		try {
			List<WebElement> placeDesciption = driver.findElements(By.className("porlet-content"));
			System.out.println(placeDesciption.get(1).getText());
		} catch (WebDriverException e) {

		}

		try {
			WebElement placeImages = driver.findElement(By.cssSelector(".embed-foody >img"));
			System.out.println(placeImages.getAttribute("src"));
		} catch (WebDriverException e) {

		}

		try {
			List<WebElement> placeRating = driver.findElements(By.className("place-score-number"));
			System.out.println(placeRating.get(5).getText());
		} catch (WebDriverException e) {

		}

		try {
			WebElement placeAddress = driver.findElement(By.className("place-meta-item"));
			System.out.println(placeAddress.getText());
		} catch (WebDriverException e) {

		}

		try {
			List<WebElement> placeOpenTime = driver.findElements(By.cssSelector(".place-meta-item"));
			System.out.println(placeOpenTime.get(2).getText());
		} catch (WebDriverException e) {

		}

		try {
			WebElement typePlace = driver.findElement(By.className("place-type"));
			System.out.println(typePlace.getText());
		} catch (WebDriverException e) {

		}

		try {
			WebElement placePrice = driver.findElement(By.className("icon-tag"));
			System.out.println(placePrice.getText());
		} catch (WebDriverException e) {

		}

		// Save database
	}

	// Take all links
	public static List<String> getLinks(WebDriver driver) {
		listLinks = new ArrayList<String>();
		try {
			List<WebElement> listLink = driver.findElements(By.className("link-absolute"));
			WebDriver dr = new FirefoxDriver();
			for (WebElement we : listLink) {
				try {
					String link = we.getAttribute("href");
					System.out.println(link);
					dr.get(link);
					crawlPage(dr);
					dr.close();
					dr = new FirefoxDriver();
				} catch (WebDriverException e) {// Exit browser immediate, disconnect
					System.out.println("GetLinks=============" + e.getMessage());
				} catch (NullPointerException e1) {// Not found attribute href after replace href1
					System.out.println("GetLinks not found link=============" + e1.getMessage());
				}
			}
		} catch (WebDriverException e2) {
			System.out.println("GetLinks not found link=============" + e2.getMessage());
		}
		return listLinks;
	}
}
