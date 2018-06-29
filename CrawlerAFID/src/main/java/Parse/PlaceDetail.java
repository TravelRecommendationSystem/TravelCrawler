package Parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Fetch.DocumentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Fetch.TripFetch;

public class PlaceDetail {
	public static void crawlPage(WebDriver driver, TripFetch part, String image) {
		// use for other website don't have some column
		
		try {
			// Name
			WebElement placeName = driver.findElement(By.cssSelector(part.getName()));
			System.out.println(placeName.getText());
//			//Image
//			WebElement placeImages = driver.findElement(By.cssSelector(part.getImage()));
			System.out.println("Image==================="+image);
			//Rating
			WebElement placeRating = driver.findElement(By.cssSelector(part.getRating()));
			System.out.println(placeRating.getText());
			//placeAddress
			WebElement placeAddress = driver.findElement(By.cssSelector(part.getAddress()));
			System.out.println(placeAddress.getText());
			
		} catch (WebDriverException e) {
			System.out.println("Not found element place-name");
		}
		try {
			// Description
			WebElement placeDesciption = driver.findElement(By.cssSelector(part.getDescription()));
			System.out.println(placeDesciption.getText());
			
		}catch(WebDriverException e) {
			
		}
		// openTime
		try {
			List<WebElement> placeOpenTime = driver.findElements(By.cssSelector(part.getOpenTime()));
			System.out.println(placeOpenTime.get(part.getIndexTime()).getText());
		} catch (WebDriverException e) {

		} catch (IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list placeOpenTime because not found element");
		}
		// typePlace
		try {
			List<WebElement> typePlace = driver.findElements(By.cssSelector(part.getPlaceType()));
			System.out.println(typePlace.get(part.getIndexType()).getText());

		} catch (WebDriverException e) {

		} catch (IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list  typePlace because not found element");
		}
		// placePrice
		try {
			WebElement placePrice = driver.findElement(By.cssSelector(part.getPlacePrice()));
			System.out.println(placePrice.getText());
		} catch (WebDriverException e) {

		}

		// Save database
	}

	public static void crawlLinks(TripFetch part) {
		part.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		part.getDocument(driver);
		HashMap<String,String> linkList = part.getLinkList(driver);
		try {
			WebDriver dr = new FirefoxDriver();
			for (Map.Entry<String, String> link : linkList.entrySet()) {
				try {
					dr.get(link.getKey());
					crawlPage(dr, part,link.getValue());
					dr.close();
					dr = new FirefoxDriver();
				} catch (WebDriverException e) {// Exit browser immediate, disconnect
					System.out.println("GetLinks=============" + e.getMessage());
				} catch (NullPointerException e1) {// Not found attribute href after replace href1

				}
			}
		} catch (WebDriverException e2) {

		}
	}
}
