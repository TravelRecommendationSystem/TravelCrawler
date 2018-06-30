package Parse.Comment;

import Fetch.TripFetch;
import Model.Comment;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Umino
 * @date 6/30/2018
 */
public class TripAdvisorCommentParser extends CommentParserBase {
    public static void crawlLinks(WebDriver driver, List<String> linkList, TripFetch part){
        List<Comment> comments = new ArrayList<Comment>();
        try {
            driver.quit();
            for (String url : linkList) {
                List<Comment> commentsInOneLink = parseComments(driver, url, part);
                comments.addAll(commentsInOneLink);
            }
            System.out.println(comments);
        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
        }
    }

    public static List<Comment> parseComments(WebDriver driver, String url, TripFetch pattern) {
        List<Comment> comments = null;
        try {
            comments = new ArrayList<Comment>();
            driver.quit();
            //open new window
            driver = new FirefoxDriver();
            driver.get(url);
            longWait = new WebDriverWait(driver, 30);
            shortWait = new WebDriverWait(driver, 10);

            // khi nút next còn hiện thì còn thực hiện
            do {
                List<WebElement> commentElements = getCommentElements();
                for (WebElement element : commentElements) {
                    seeMoreCommentDescription(element);
                    Comment comment = parseOneCommentElement(element, pattern);
                    if (comment != null) {
                        comments.add(comment);
                    }
                }
            }
            while (navigateNextCommentPage());
            driver.quit();

        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
        }
        return comments;
    }

    public static boolean navigateNextCommentPage() {
        try {
            String reviewContainerPath = "div[id='REVIEWS']";
            String btnNexPagePath = "a.nav.next.taLnk.ui_button.primary";

            WebElement commentContainer = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(reviewContainerPath)));
            commentContainer.findElement(By.cssSelector(btnNexPagePath)).click();
            return true;

        } catch (TimeoutException ex){
            // This is the last page, so next button is gone
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public static List<WebElement> getCommentElements() {
        String commentContainerPath = "div#taplc_location_reviews_list_responsive_detail_0 > div > div.review-container";
        List<WebElement> commentElements = new ArrayList<WebElement>();
        try {
            commentElements = longWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(commentContainerPath)));
        } catch (TimeoutException ex){
            return commentElements;
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return commentElements;
    }

    public static void seeMoreCommentDescription(WebElement element) {
        try {
            String btnSeeMorePath = "span.taLnk.ulBlueLinks";
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSeeMorePath))).click();

        } catch (TimeoutException ex){
            // This comment is short, so no need for having button more
            return;
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
    }

    protected static Comment parseOneCommentElement(WebElement element, TripFetch pattern) {
        Comment comment;
        try {
            String username = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentUsername()))).getAttribute("innerHTML");
            String createdDate = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentCreatedDate()))).getText();
            String description = shortWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.cssSelector(pattern.getCommentDescription()))).getAttribute("innerHTML");

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
