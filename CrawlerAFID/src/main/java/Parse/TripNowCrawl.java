package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Fetch.TripNFetch;

public class TripNowCrawl {
	public static void main(String[] arg) {
		TripFetch tripNowPart = new TripAdFetch("tripnow.vn");
		tripNowPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		tripNowPart.getDocument(driver,"","");
		List<String> linkList = tripNowPart.getLinkList(driver);
		PlaceDetail.crawlLinks(driver, linkList, tripNowPart);
	}
}
