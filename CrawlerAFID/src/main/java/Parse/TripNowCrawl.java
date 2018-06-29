package Parse;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Fetch.TripNFetch;

public class TripNowCrawl {
	public static void main(String[] arg) {
		TripFetch tripPart = new TripNFetch("tripnow.vn");
		tripPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		tripPart.getDocument(driver);
		HashMap<String,String> linkList = tripPart.getLinkList(driver);
		PlaceDetail.crawlLinks(linkList, tripPart);
	}
}
