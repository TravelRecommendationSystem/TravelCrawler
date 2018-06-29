import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Parse.TripNowCommentParser;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phuongnl
 */
public class Main {
    //private static String baseUrl = "https://www.tripadvisor.com.vn/Attraction_Review-g293925-d454974-Reviews-Saigon_Opera_House_Ho_Chi_Minh_Municipal_Theater-Ho_Chi_Minh_City.html";

    public static void main(String [ ] args) {
        TripFetch tripNowPart = new TripAdFetch("tripnow.vn");
        tripNowPart.getDataFromPattern();
        WebDriver driver = DocumentDriver.getDriver();
        tripNowPart.getDocument(driver, "", "");
//        List<String> linkList = tripNowPart.getLinkList(driver);
        List<String> ulrs = new ArrayList<String>();
        ulrs.add("https://www.tripnow.vn/vung-tau/chua-quan-am-nam-hai-22676");
        ulrs.add("https://www.tripnow.vn/ho-chi-minh/nha-tho-duc-ba-saigon-notre-dame-basilica-21851");
        ulrs.add("https://www.tripnow.vn/ho-chi-minh/pho-di-bo-nguyen-hue-19306");
        TripNowCommentParser.crawlLinks(driver, ulrs, tripNowPart);
    }
}
