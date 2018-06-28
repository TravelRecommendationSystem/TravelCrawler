package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import PatternFiles.Pattern;

public class TripAdvisorCrawl{
	public static void main(String[] arg) {
		Pattern tripNowPart = new Pattern();
		tripNowPart.setDomain("tripadvisor.com");
		tripNowPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		TripAdFetch.getDocument(driver,"","");
		List<String> linkList = TripAdFetch.getLinkList(driver);
		PlaceDetail.crawlLinks(driver, linkList, tripNowPart);
	}
}