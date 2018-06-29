package Parse;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripFetch;
import Fetch.TripAdFetch;

public class TripAdvisorCrawl {
	public static void main(String[] arg) {
		TripFetch tripPart = new TripAdFetch("tripadvisor.com");
		tripPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		tripPart.getDocument(driver);
		HashMap<String, String> linkList = tripPart.getLinkList(driver);
		PlaceDetail.crawlLinks(linkList, tripPart);
	}
}