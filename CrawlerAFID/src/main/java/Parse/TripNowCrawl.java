package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Fetch.TripNFetch;

public class TripNowCrawl {
	public static void main(String[] arg) {
		PlaceDetail.crawlLinks(new TripNFetch());
	}
}
