package Parse;
import Fetch.TripNFetch;

public class TripNowCrawl {
	public static void main(String[] arg) {
		PlaceDetail.crawlLinks(new TripNFetch());
	}
}
