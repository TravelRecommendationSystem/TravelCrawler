package Parse.Comment;

import Fetch.TripFetch;
import Model.Comment;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Umino
 * @date 6/29/2018
 */

public class CommentParserBase {
    public class CommentParseResult {
        private List<Comment> listComment;
        private boolean isSuccess;
        private String refUrl;

        public List<Comment> getListComment() {
            return listComment;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getRefUrl() {
            return refUrl;
        }

        public CommentParseResult(List<Comment> listComment, boolean isSuccess, String refUrl) {
            this.listComment = listComment;
            this.isSuccess = isSuccess;
            this.refUrl = refUrl;
        }
    }


///////////////////////////////////
    protected static WebDriverWait longWait;
    protected static WebDriverWait shortWait;

    protected static List<Comment> getListComment(WebDriver driver, TripFetch pattern) {
        return null;
    }
    public static List<Comment> parseComments(WebDriver driver, String url, TripFetch pattern) { return null;}
    protected static Comment parseOneCommentElement(WebElement element, TripFetch pattern) {
        Comment comment;
        try {
            String username = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentUsername()))).getText();
            String createdDate = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentCreatedDate()))).getText();
            String description = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentDescription()))).getText();

            comment = new Comment();
            comment.setUserName(username);
            comment.setCommentDesciption(description);
            comment.setCreatedDate(createdDate);

        } catch (TimeoutException ex){
            System.out.println(ex);
            return null;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return comment;
    }
}
