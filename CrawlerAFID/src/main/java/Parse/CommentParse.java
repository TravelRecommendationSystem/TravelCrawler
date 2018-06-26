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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        listPage = getCommentPages(wait);
        int currentpage = 1;
        List<WebElement> commentElements = new ArrayList<WebElement>();
        
        while (currentpage <= listPage.size()){
            commentElements.addAll(getCommentElements(wait));
            navigateNextCommentPage(currentpage);
        }
        if (commentElements.size() == 0)
            return;
        
        for (WebElement element : commentElements) {
            Comment comment = parseOneComment(element);
            if (comment != null) {
                listComment.add(comment);
            }
        }  
        System.out.println(listComment);
    }
    
    public List<WebElement> getCommentPages(WebDriverWait wait) {
        List<WebElement> listPage = new ArrayList<WebElement>();
        try { 
            By numBox = By.cssSelector("div[class='pageNumbers']");
            By number = By.cssSelector("a[class='pageNum taLnk']");
            listPage = wait.until(ExpectedConditions
                    .presenceOfNestedElementsLocatedBy(numBox, number));
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return listPage;
    }
    
    public void navigateNextCommentPage(int currentpage) {
        listPage.get(++currentpage).click();
    }
    
    public List<WebElement> getCommentElements(WebDriverWait wait) {
        List<WebElement> commentElements = new ArrayList<WebElement>();
        try {
            commentElements = wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(By.cssSelector("div[class='review-container']")));
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
