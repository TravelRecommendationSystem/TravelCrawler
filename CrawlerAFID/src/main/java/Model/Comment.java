package Model;

import java.util.Date;

public class Comment {
    private String userName;
    private String commentDesciption;
    private Date createdDate;

    public Comment() {
    }

    public Comment(String userName, String commentDesciption, Date createdDate) {
        this.userName = userName;
        this.commentDesciption = commentDesciption;
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentDesciption() {
        return commentDesciption;
    }

    public void setCommentDesciption(String commentDesciption) {
        this.commentDesciption = commentDesciption;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
