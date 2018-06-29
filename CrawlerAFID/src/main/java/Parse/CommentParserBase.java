package Parse;

import Model.Comment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Umino
 * @date 6/29/2018
 */

public abstract class CommentParserBase {
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

    private WebDriver driver;
    private Wait longWait;
    private Wait shortWait;

    public CommentParserBase(WebDriver driver) {
        this.driver = driver;
        longWait = new WebDriverWait(driver, 30);
        shortWait = new WebDriverWait(driver, 10);
    }

    public abstract CommentParseResult parse(String url);
    protected abstract List<WebDriver> getListCommentWebElement();
    protected abstract Comment parseOneCommentElement(WebDriver element);
}
