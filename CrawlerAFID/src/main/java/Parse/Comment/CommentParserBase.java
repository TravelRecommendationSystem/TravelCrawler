package Parse.Comment;

import Fetch.TripFetch;
import Model.Comment;
import Model.Link;
import Utils.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Umino
 * @date 6/29/2018
 */

public class CommentParserBase {
    protected static WebDriverWait longWait;
    protected static WebDriverWait shortWait;

    protected static Pair<List<Comment>, Boolean> getListComment(WebDriver driver, TripFetch pattern) {
        return null;
    }
    public static Pair<List<Comment>, Link> parseComments(WebDriver driver, String url, TripFetch pattern) { return null;}
    protected static Comment parseOneCommentElement(WebElement element, TripFetch pattern) { return null; }
}
