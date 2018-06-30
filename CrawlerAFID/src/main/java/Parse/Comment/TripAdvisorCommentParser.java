package Parse.Comment;

import Fetch.TripFetch;
import Model.Comment;
import Model.Link;
import Utils.Pair;
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
                Pair<List<Comment>, Link> result = parseComments(driver, url, part);
                comments.addAll(result.getFirst());
            }
            System.out.println(comments);
        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
        }
    }

    public static Pair<List<Comment>, Link> parseComments(WebDriver driver, String url, TripFetch pattern) {
        List<Comment> comments = null;
        Boolean isSuccess = true;

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
                Pair<List<WebElement>, Boolean> result = getCommentElements();
                List<WebElement> commentElements = result.getFirst();
                isSuccess &= result.getSecond();

                for (WebElement element : commentElements) {
                    seeMoreCommentDescription(element);
                    Comment comment = parseOneCommentElement(element, pattern);
                    if (comment != null) {
                        comments.add(comment);
                    } else {
                        isSuccess = false;
                    }
                }
            }
            while (navigateNextCommentPage());
            driver.quit();

        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
            isSuccess = false;
        }
        return new Pair(comments,  new Link(url, isSuccess));
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

    public static Pair<List<WebElement>, Boolean> getCommentElements() {
        String commentContainerPath = "div#taplc_location_reviews_list_responsive_detail_0 > div > div.review-container";
        List<WebElement> commentElements = new ArrayList<WebElement>();
        try {
            commentElements = longWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(commentContainerPath)));
            return new Pair(commentElements, true);
        } catch (TimeoutException ex){
            System.out.println(ex);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return new Pair(commentElements, false);
    }

    public static Boolean seeMoreCommentDescription(WebElement element) {
        try {
            String btnSeeMorePath = "span.taLnk.ulBlueLinks";
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSeeMorePath))).click();
            return true;
        } catch (TimeoutException ex){
            // This comment is short, so no need for having button more
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    protected static Comment parseOneCommentElement(WebElement element, TripFetch pattern) {
        Comment comment = null;
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
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            return comment;
        }
    }
}
