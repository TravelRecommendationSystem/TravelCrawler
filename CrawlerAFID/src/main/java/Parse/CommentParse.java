/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parse;

import Model.Comment;
import java.util.ArrayList;
import java.util.List;
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
        listComment = new ArrayList<Comment>();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //List<WebElement> commentElements = new ArrayList<WebElement>();

        // khi nút next còn hiện thì còn thực hiện
        do {
            List<WebElement> commentElements = getCommentElements(wait);
            for (WebElement element : commentElements) {
                Comment comment = parseOneComment(element);
                if (comment != null) {
                    listComment.add(comment);
                }
            }
        }
        while (navigateNextCommentPage(wait));
        System.out.println(listComment);
    }

    public boolean navigateNextCommentPage(WebDriverWait wait) {
        try {
            By nextButtonPath = By.cssSelector("div[class='prw_rup prw_common_north_star_pagination responsive'] > div > a[class='nav next taLnk ui_button primary']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonPath)).click();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public List<WebElement> getCommentElements(WebDriverWait wait) {
        List<WebElement> commentElements = new ArrayList<WebElement>();
        try {
            commentElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='review-container']")));
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return commentElements;
    }
    
    public Comment parseOneComment(WebElement element) {
        Comment comment;
        try {
            String username = element.findElement(By.cssSelector("span[class='expand_inline scrname']")).getText();
            String description = element.findElement(By.cssSelector("p[class='partial_entry']")).getText();
            String createdDate = element.findElement(By.cssSelector("span[class='ratingDate relativeDate']")).getText();
            
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
