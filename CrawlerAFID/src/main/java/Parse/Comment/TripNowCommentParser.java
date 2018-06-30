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
                List<Comment> commentsInOneLink = getListComment(driver, part).getFirst();

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
    public static Pair<List<Comment>, Link> parseComments(WebDriver driver, String link, TripFetch pattern) {
        List<Comment> comments = new ArrayList<Comment>();
        boolean isSuccess = false;
        try {
                driver.quit();
                //open new window
                driver = new FirefoxDriver();
                longWait = new WebDriverWait(driver, 20);
                shortWait = new WebDriverWait(driver, 10);

                // switch to comment tab
                driver.get(link + "/binh-luan");

                // parse one link
                expandAllComment(driver);
                Pair<List<Comment>, Boolean> result = getListComment(driver, pattern);
                comments.addAll(result.getFirst());
                isSuccess &= result.getSecond();

                driver.quit();

        } catch (Exception ex) {
            // Network interrupt
            driver.quit();
            System.out.println(ex);
            isSuccess = false;
        }
        return new Pair(comments, new Link(link, isSuccess));
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

    protected static Pair<List<Comment>, Boolean> getListComment(WebDriver driver, TripFetch pattern) {
        List<WebElement> commentContainerElements = new ArrayList<WebElement>();
        List<Comment> commentElements = new ArrayList<Comment>();
        String containerPath = "article.story.border-foody.ng-scope";
        Boolean isSuccess = true;

        try {
            commentContainerElements = longWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(containerPath)));
        } catch (TimeoutException ex) {
            System.out.println(ex);
            isSuccess = false;
        } catch (Exception ex) {
            System.out.println(ex);
            isSuccess = false;
        }
        for (WebElement element : commentContainerElements) {
            Comment comment =  parseOneCommentElement(element, pattern);
            if (comment != null) {
                commentElements.add(comment);
            }
        }
        return new Pair(commentElements, isSuccess);
    }

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
