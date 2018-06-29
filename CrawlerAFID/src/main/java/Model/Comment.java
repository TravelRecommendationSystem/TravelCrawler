package Model;

import java.util.Date;

public class Comment {
	private String userName;
	private String commentDesciption;
	private String createdDate;

	public Comment() {
	}

	public Comment(String userName, String commentDesciption, String createdDate) {
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
