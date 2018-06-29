package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripFetch;
import Fetch.TripAdFetch;

public class TripAdvisorCrawl{
	public static void main(String[] arg) {
		TripFetch tripNowPart = new TripAdFetch("tripadvisor.com");
		tripNowPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		tripNowPart.getDocument(driver,"","");
		List<String> linkList = tripNowPart.getLinkList(driver);
		PlaceDetail.crawlLinks(driver, linkList, tripNowPart);
	}
}