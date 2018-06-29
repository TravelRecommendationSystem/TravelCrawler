package Parse;

import java.util.List;

import org.openqa.selenium.WebDriver;

import Fetch.DocumentDriver;
import Fetch.TripFetch;
import Fetch.TripAdFetch;

public class TripAdvisorCrawl{
	public static void main(String[] arg) {
		PlaceDetail.crawlLinks(new TripAdFetch());
	}
}