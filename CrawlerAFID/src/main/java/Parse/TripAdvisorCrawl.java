package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripNFetch;
import PatternFiles.Pattern;

//package Parse;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class TripAdvisorCrawl {
//	//private static List<String> listLinks = new ArrayList<String>();
//
//	public static void getDocument(WebDriver driver) {
//		try {
//			driver.get("https://www.tripadvisor.com.vn/Attractions-g293925-Activities-c47-Ho_Chi_Minh_City.html");
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		} catch (WebDriverException e) {// TH disconnect, Not found element replace nav1,TimeoutException
//			System.out.println("Load page failed==============================");
//		}
//	}
//
//	// Parse place
//
////	// Click NextPage button take all links
////	public static List<String> ExpandAllPlaces(WebDriver driver) {
////		try {// Check page loaded XemThem
////			WebDriverWait wait = new WebDriverWait(driver, 20);
////			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
////			WebElement webElement = driver.findElement(By.cssSelector("a.nav.next"));
////			List<WebElement> listElements = driver
////					.findElements(By.cssSelector("div.listing_title > a"));
////			while (webElement.getText().equals("Tiếp theo")) {
////				try {
////					listElements = driver.findElements(By.cssSelector("div.listing_title > a"));
////					for (WebElement web : listElements) {
////						listLinks.add(web.getAttribute("href"));
////					}
////					System.out.println("Hello");
////					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.nav.next")));
////					webElement = driver.findElement(By.cssSelector("a.nav.next"));
////					webElement.click();
////				} catch (Exception e) {
////					// System.out.println("===========ExpandAllPlaces" + e.getMessage());
////				}
////			}
////		} catch (WebDriverException e1) {// End not found nextpage
////			System.out.println("===========ExceptionNotFoundExpandAllPlaces" + e1.getMessage());
////		}
////		System.out.println("===========================");
////		for (int i = 0; i < listLinks.size(); i++) {
////			System.out.println(listLinks.get(i));
////		}
////		return listLinks;
////	}
//
//	// Parse place
//	// crawl page content
//		public static void crawlPage(WebDriver driver) {
//			// use for other website don't have some column
//			//Name
//			try {
//				WebElement placeName = driver.findElement(By.id("HEADING"));
//				System.out.println(placeName.getText());
//			} catch (WebDriverException e) {
//				System.out.println("Not found element place-name");
//			}
//			//Description
//			try {
//				WebElement placeDesciption = driver.findElement(By.cssSelector("div.modal-card-body-no-exist"));
//				System.out.println(placeDesciption.getText());
//			} catch (WebDriverException e) {
//				System.out.println("Not found element description");
//			}
//			//Images
////			try {
////				WebElement webElement = driver.findElement(By.cssSelector(".ui_icon.expand"));
////				webElement.click();
////				WebDriverWait wait = new WebDriverWait(driver, 60);
////				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui_close_x")));
////				//WebElement placeImages = driver.findElement(By.cssSelector("div#PANO_HOLDER >img"));
//////				System.out.println(placeImages.getAttribute("src"));
//////				//ui_close_x
////				webElement = driver.findElement(By.cssSelector(".ui_close_x"));
////				webElement.click();
////				
////			} catch (WebDriverException e) {
////				System.out.println("++++++++++++Not found element images"+ e.getMessage());
////			}
//			//Rating
//			try {
//				WebElement placeRating = driver.findElement(By.cssSelector("span.overallRating"));
//				System.out.println(placeRating.getText());
//			} catch (WebDriverException e) {
//
//			}
//			//placeAddress
//			try {
//				WebElement placeAddress = driver.findElement(By.cssSelector("div.blEntry.address"));
//				System.out.println(placeAddress.getText());
//			} catch (WebDriverException e) {
//
//			}
//			//openTime
//			try {
//				List<WebElement> placeOpenTime = driver.findElements(By.cssSelector("div.detail_section.hours > div"));
//				System.out.println(placeOpenTime.get(1).getText());
//			} catch (WebDriverException e) {
//				System.out.println("===================Not found placeOpenTime");
//			}catch(IndexOutOfBoundsException e1) {
//				System.out.println("===================Outside of list because not found element");
//			}
//			//typePlace
//			try {
//				List<WebElement> typePlace = driver.findElements(By.cssSelector("span.header_detail > div > a"));
//				System.out.println(typePlace.get(0).getText());
//			} catch (WebDriverException e) {
//				System.out.println("===================Not found placeOpenTime");
//			}catch(IndexOutOfBoundsException e1) {
//				System.out.println("===================Outside of list because not found element");
//			}
//			//placePrice
//			try {
//				WebElement placePrice = driver.findElement(By.className("icon-tag-no-exist"));
//				System.out.println(placePrice.getText());
//			} catch (WebDriverException e) {
//
//			}
//		}
//
//	// Take all links
//	public static List<String> getLinks(WebDriver driver) {
//		List<String> listUrls = ExpandAllPlaces(driver);
//		WebDriver dr = new FirefoxDriver();
//		for (String link : listUrls) {
//			try {
//				System.out.println(link);
//				dr.get(link);
//				crawlPage(dr);
//				//dr.close();
//				//dr = new FirefoxDriver();
//				break;
//			} catch (WebDriverException e) {// Exit browser immediate, disconnect
//				System.out.println("GetLinks=============" + e.getMessage());
//			}
//		}
//		return listUrls;
//	}
//
//	public static void main(String[] arg) {
//		System.setProperty("webdriver.gecko.driver", "/home/tienbui/Downloads/geckodriver");
//		WebDriver driver = new FirefoxDriver();
//		getDocument(driver);
//		ExpandAllPlaces(driver);
//		getLinks(driver);
//		// driver.quit();
//	}
//}
public class TripAdvisorCrawl{
	public static void main(String[] arg) {
		Pattern tripNowPart = new Pattern();
		tripNowPart.setDomain("tripadvisor.com");
		tripNowPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		TripAdFetch.getDocument(driver,"","");
//		TripAdFetch.expandAllPlaces(driver);
//		// Click button XemThem
		List<String> linkList = TripAdFetch.getLinkList(driver);
		PlaceDetail.crawlLinks(driver, linkList, tripNowPart);
	}
}