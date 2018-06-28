package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripNFetch;
import PatternFiles.Pattern;

public class TripNowCrawl {
	public static void main(String[] arg) {
		Pattern tripNowPart = new Pattern();
		tripNowPart.setDomain("tripnow.vn");
		tripNowPart.getDataFromPattern();
		WebDriver driver = DocumentDriver.getDriver();
		TripNFetch.getDocumen(driver, "truongvinhtienuit@gmail.com", "123456");
		List<String> linkList = TripNFetch.getLinkList(driver);
		PlaceDetail.crawlLinks(driver, linkList, tripNowPart);
	}
}
