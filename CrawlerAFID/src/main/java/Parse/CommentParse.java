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

/**
 *
 * @author phuongnl
 */
public class CommentParse {
    public List<Comment> listComment;
    private WebDriver driver;
    private String url; // Place's description url
    
    
    public CommentParse(WebDriver driver, String url){
        listComment = new ArrayList<Comment>();
        this.driver = driver;
        this.url = url;
    }
    
    public void parse() {
        List<WebElement> commentElements = driver.findElements(By.className("prw_rup prw_common_location_topic"));
        //commentElements.forEach(element -> );
        
        WebElement element = driver.findElement(By.className("prw_rup prw_common_location_topic"));
        System.out.println(parseOneComment(element));
    }
    
    public Comment parseOneComment(WebElement element) {
        String username = element.findElement(By.className("username")).getText();
        String description = element.findElement(By.className("question_text_link")).getText();
        
        Comment comment = new Comment();
        comment.setUserName(username);
        comment.setCommentDesciption(description);
        
        return comment;
    }
}
