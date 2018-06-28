package Parse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripNowCrawl {
	private static List<String> listLinks;

	// Login system

	public static void LoginSystem(WebDriver driver, String userName, String passWord) {
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
	public static void ExpandAllPlaces(WebDriver driver) {
		try {// Check page loaded XemThem
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Xem thêm")));
			WebElement webElement = driver.findElement(By.linkText("Xem thêm"));
			while (webElement.getText().equals("Xem thêm")) {
				try {
					webElement.click();
					System.out.println("****************************");
					//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Xem thêm")));
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					webElement = driver.findElement(By.linkText("Xem thêm"));
				} catch (Exception e) {
					System.out.println("===========ExpandAllPlaces" + e.getMessage());
				}
			}
		} catch (WebDriverException e1) {
			System.out.println("===========TimeoutExpandAllPlaces" + e1.getMessage());
		}

	}

	// crawl page content
	public static void crawlPage(WebDriver driver) {
		// use for other website don't have some column
		//Name
		try {
			WebElement placeName = driver.findElement(By.className("place-name"));
			System.out.println(placeName.getText());
		} catch (WebDriverException e) {
			System.out.println("Not found element place-name");
		}
		//Description
		try {
			WebElement placeDesciption = driver.findElement(By.cssSelector("div.porlet-content"));
			System.out.println(placeDesciption.getText());
		} catch (WebDriverException e) {
			System.out.println("Not found description");
		}
		//Image
		try {
			WebElement placeImages = driver.findElement(By.cssSelector(".embed-foody >img"));
			System.out.println(placeImages.getAttribute("src"));
		} catch (WebDriverException e) {

		}
		//Rating
		try {
			WebElement placeRating = driver.findElement(By.className("place-total-score"));
			System.out.println(placeRating.getText());
		} catch (WebDriverException e) {

		}
		//placeAddress
		try {
			WebElement placeAddress = driver.findElement(By.className("place-meta-item"));
			System.out.println(placeAddress.getText());
		} catch (WebDriverException e) {

		}
		//openTime
		try {
			List<WebElement> placeOpenTime = driver.findElements(By.cssSelector(".place-meta-item"));
			System.out.println(placeOpenTime.get(2).getText());
		} catch (WebDriverException e) {

		}catch(IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list because not found element");
		}
		//typePlace
		try {
			List<WebElement> typePlace = driver.findElements(By.className("place-type"));
			System.out.println(typePlace.get(0).getText());
		} catch (WebDriverException e) {

		}catch(IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list because not found element");
		}
		//placePrice
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

	public static void main(String[] arg) {
		System.setProperty("webdriver.gecko.driver", "/home/tienbui/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();
		LoginSystem(driver, "truongvinhtienuit@gmail.com", "123456");
		ExpandAllPlaces(driver);
		getLinks(driver);
		// driver.quit();
	}
}
