package Parse.Comment;

import Fetch.TripFetch;
import Model.Comment;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Umino
 * @date 6/29/2018
 */
public class TripNowCommentParser extends CommentParserBase {

    public static void crawlLinks(WebDriver driver, List<String> linkList, TripFetch part){
        List<Comment> comments = new ArrayList<Comment>();
        try {
            driver.quit();
            for (String url : linkList) {
                //open new window
                driver = new FirefoxDriver();
                longWait = new WebDriverWait(driver, 20);
                shortWait = new WebDriverWait(driver, 10);

                // switch to comment tab
                driver.get(url + "/binh-luan");

                // parse one link
                expandAllComment(driver);
                List<Comment> commentsInOneLink = getListComment(driver, part);

                comments.addAll(commentsInOneLink);
                driver.quit();
            }
            System.out.println(comments);
        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
        }
    }

    // parse all comments inside a place link
    public static List<Comment> parseComments(WebDriver driver, String url, TripFetch pattern) {
        List<Comment> comments = null;
        try {
                driver.quit();
                //open new window
                driver = new FirefoxDriver();
                longWait = new WebDriverWait(driver, 20);
                shortWait = new WebDriverWait(driver, 10);

                // switch to comment tab
                driver.get(url + "/binh-luan");

                // parse one link
                expandAllComment(driver);
                comments = getListComment(driver, pattern);
                driver.quit();

        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
        }
        return comments;
    }

    private static WebElement expandAllComment(WebDriver driver) {
        String btnSeeMorePath = "a.button.expanded.primary.ng-binding.ng-scope";
        try {
            longWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSeeMorePath))).click();
        } catch (TimeoutException ex) {
            // all comments is showing
            return null;
        } catch (Exception ex) {
            //others exception
            System.out.println(ex);
            return null;
        }
        return expandAllComment(driver);
    }

    protected static List<Comment> getListComment(WebDriver driver, TripFetch pattern) {
        List<WebElement> commentContainerElements = new ArrayList<WebElement>();
        List<Comment> commentElements = new ArrayList<Comment>();
        String containerPath = "article.story.border-foody.ng-scope";

        try {
            commentContainerElements = longWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(containerPath)));
        } catch (TimeoutException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        for (WebElement element : commentContainerElements) {
            Comment comment =  parseOneCommentElement(element, pattern);
            if (comment != null) {
                commentElements.add(comment);
            }
        }
        return commentElements;
    }
}
