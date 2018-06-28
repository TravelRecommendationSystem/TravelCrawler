package Model;

public class Link {
	private String linkText;
	private Boolean flag;
	private int numberFailedRequest;
	private int numberRequest;

	public Link() {
	}

	public Link(String linkText, Boolean flag, int numberFailedRequest, int numberRequest) {
		this.linkText = linkText;
		this.flag = flag;
		this.numberFailedRequest = numberFailedRequest;
		this.numberRequest = numberRequest;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public int getNumberFailedRequest() {
		return numberFailedRequest;
	}

	public void setNumberFailedRequest(int numberFailedRequest) {
		this.numberFailedRequest = numberFailedRequest;
	}

	public int getNumberRequest() {
		return numberRequest;
	}

	public void setNumberRequest(int numberRequest) {
		this.numberRequest = numberRequest;
	}
}
