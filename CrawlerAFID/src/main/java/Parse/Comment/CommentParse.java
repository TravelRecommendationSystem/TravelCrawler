/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parse.Comment;

import Model.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author phuongnl
 */
public class CommentParse {
    public List<Comment> listComment;
    private WebDriver driver;
    private List<WebElement> listPage;
    
    
    public CommentParse(WebDriver driver){
        this.listComment = new ArrayList<Comment>();
        this.listPage = new ArrayList<WebElement>();
        this.driver = driver;
    }
    
    public void parse() {
        WebDriverWait longWait = new WebDriverWait(driver, 30);
        WebDriverWait shortWait = new WebDriverWait(driver, 10);

        // khi nút next còn hiện thì còn thực hiện
        do {
            List<WebElement> commentElements = getCommentElements(longWait);
            for (WebElement element : commentElements) {
                seeMoreCommentDescription(element);
                Comment comment = parseOneComment(element);
                if (comment != null) {
                    listComment.add(comment);
                }
            }
        }
        while (navigateNextCommentPage(longWait));
        System.out.println(listComment);
    }

    public void seeMoreCommentDescription(WebElement element) {
        try {
            element.findElement(By.cssSelector("span[class='taLnk ulBlueLinks']")).click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } catch (Exception ex) {
            // This comment is short, so no need for having button more
        }
    }

    public boolean navigateNextCommentPage(WebDriverWait wait) {
        try {
            String reviewContainerPath = "div[id='REVIEWS']";
            WebElement commentContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(reviewContainerPath)));
            commentContainer.findElement(By.cssSelector("a[class='nav next taLnk ui_button primary']")).click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public List<WebElement> getCommentElements(WebDriverWait wait) {
        String commentContainerPath = "div[class='listContainer responsive'] > div[class='review-container']";
        List<WebElement> commentElements = new ArrayList<WebElement>();
        try {
            commentElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(commentContainerPath)));
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return commentElements;
    }
    
    public Comment parseOneComment(WebElement element) {
        Comment comment;
        try {
            String username = element.findElement(By.cssSelector("span[class='expand_inline scrname']")).getText();
            String createdDate = element.findElement(By.cssSelector("span[class='ratingDate relativeDate']")).getText();
            String description = element.findElement(By.cssSelector("p[class='partial_entry']")).getText();
            
            comment = new Comment();
            comment.setUserName(username);
            comment.setCommentDesciption(description);
            comment.setCreatedDate(createdDate);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return comment;
    }
}
