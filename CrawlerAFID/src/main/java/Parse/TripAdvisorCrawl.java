package Parse;
import Fetch.TripAdFetch;

public class TripAdvisorCrawl {
	public static void main(String[] arg) {
		PlaceDetail.crawlLinks(new TripAdFetch());
	}
}