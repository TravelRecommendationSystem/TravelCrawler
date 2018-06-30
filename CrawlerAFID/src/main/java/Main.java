import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Model.Comment;
import Parse.Comment.CommentsDetail;
import Parse.Comment.TripAdvisorCommentParser;
import Parse.Comment.TripNowCommentParser;
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
        //TripFetch tripNowPart = new TripAdFetch("tripnow.vn");
//        TripFetch tripNowPart = new TripAdFetch("tripadvisor.com");
//        tripNowPart.getDataFromPattern();
//        WebDriver driver = DocumentDriver.getDriver();
//        tripNowPart.getDocument(driver, "", "");
        List<String> urls = new ArrayList<String>();
        urls.add("https://www.tripnow.vn/vung-tau/chua-quan-am-nam-hai-22676");
        urls.add("https://www.tripadvisor.com.vn/Attraction_Review-g293925-d311089-Reviews-Central_Post_Office-Ho_Chi_Minh_City.html");
        urls.add("https://www.tripnow.vn/ho-chi-minh/nha-tho-duc-ba-saigon-notre-dame-basilica-21851");
        urls.add("https://www.tripnow.vn/ho-chi-minh/pho-di-bo-nguyen-hue-19306");
        urls.add("https://www.tripadvisor.com.vn/Attraction_Review-g293925-d454974-Reviews-Saigon_Opera_House_Ho_Chi_Minh_Municipal_Theater-Ho_Chi_Minh_City.html");
        urls.add("https://www.tripadvisor.com.vn/Attraction_Review-g2145104-d13837243-Reviews-Binh_Chau_Phuoc_Buu_Nature_Reserve-Ba_Ria_Vung_Tau_Province.html");
        List<Comment> comments = CommentsDetail.crawlCommentInListLink(urls);
        System.out.println(comments);
    }
}
