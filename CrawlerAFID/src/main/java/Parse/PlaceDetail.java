package Parse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import PatternFiles.Pattern;

public class PlaceDetail {
	public static void crawlPage(WebDriver driver, Pattern part) {
		// use for other website don't have some column
		// Name
		try {
			WebElement placeName = driver.findElement(By.cssSelector(part.getName()));
			System.out.println(placeName.getText());
		} catch (WebDriverException e) {
			System.out.println("Not found element place-name");
		}
		// Description
		try {
			WebElement placeDesciption = driver.findElement(By.cssSelector(part.getDescription()));
			System.out.println(placeDesciption.getText());
		} catch (WebDriverException e) {
			System.out.println("Not found description");
		}
		// Image
		try {
			WebElement placeImages = driver.findElement(By.cssSelector(part.getImage()));
			System.out.println(placeImages.getAttribute("src"));
		} catch (WebDriverException e) {

		}
		// Rating
		try {
			WebElement placeRating = driver.findElement(By.cssSelector(part.getRating()));
			System.out.println(placeRating.getText());
		} catch (WebDriverException e) {

		}
		// placeAddress
		try {
			WebElement placeAddress = driver.findElement(By.cssSelector(part.getAddress()));
			System.out.println(placeAddress.getText());
		} catch (WebDriverException e) {

		}
		// openTime
		try {
			List<WebElement> placeOpenTime = driver.findElements(By.cssSelector(part.getOpenTime()));
			System.out.println(placeOpenTime.get(2).getText());
		} catch (WebDriverException e) {

		} catch (IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list because not found element");
		}
		// typePlace
		try {
			List<WebElement> typePlace = driver.findElements(By.cssSelector(part.getPlaceType()));
		} catch (WebDriverException e) {

		} catch (IndexOutOfBoundsException e1) {
			System.out.println("===================Outside of list because not found element");
		}
		// placePrice
		try {
			WebElement placePrice = driver.findElement(By.cssSelector(part.getPlacePrice()));
			System.out.println(placePrice.getText());
		} catch (WebDriverException e) {

		}

		// Save database
	}

	public static void crawlLinks(WebDriver driver, List<String>linkList, Pattern part) {
		try {
			WebDriver dr = new FirefoxDriver();
			for (String link : linkList) {
				try {
					dr.get(link);
					crawlPage(dr, part);
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
	}
}
