package Parse.Comment;

import Fetch.DocumentDriver;
import Fetch.TripAdFetch;
import Fetch.TripFetch;
import Fetch.TripNFetch;
import Model.Comment;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Umino
 * @date 7/1/2018
 */
public class CommentsDetail {
    public static List<Comment> crawlCommentsInOnePage(String link) {
        if (link.startsWith("https://www.tripnow.vn")) {
            TripFetch part = new TripNFetch();
            part.getDataFromPattern();
            return TripNowCommentParser.parseComments(DocumentDriver.getDriver(), link, part).getFirst();
        }
        if (link.startsWith("https://www.tripadvisor.com")) {
            TripFetch part = new TripAdFetch();
            part.getDataFromPattern();
            return TripAdvisorCommentParser.parseComments(DocumentDriver.getDriver(), link, part).getFirst();
        }
        return null;
    }

    public static List<Comment> crawlCommentInListLink(List<String> links) {
        List<Comment> comments = new ArrayList<Comment>();
        for (String l : links) {
            List<Comment> commentInOnePage = crawlCommentsInOnePage(l);
            if (commentInOnePage != null) {
                comments.addAll(commentInOnePage);
            }
        }
        return comments;
    }
}
