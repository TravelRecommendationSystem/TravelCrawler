package Model;

import java.util.List;

public class Place {
	private String id;
	private String placeName;
	private String placeDesciption;
	private List<String> placeImages;
	private String placeRating;
	private String placeAddress;
	private String placeOpenTime;
	private String placeType;
	private String placePrice;
	private String linkText;
	private List<Comment> listComment;

	public Place() {
	}

	public Place(String id, String placeName, String placeDesciption, List<String> placeImages, String placeRating,
			String placeAddress, String placeOpenTime, String placeType, String placePrice, String linkText,
			List<Comment> listComment) {
		this.id = id;
		this.placeName = placeName;
		this.placeDesciption = placeDesciption;
		this.placeImages = placeImages;
		this.placeRating = placeRating;
		this.placeAddress = placeAddress;
		this.placeOpenTime = placeOpenTime;
		this.placeType = placeType;
		this.placePrice = placePrice;
		this.linkText = linkText;
		this.listComment = listComment;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceDesciption() {
		return placeDesciption;
	}

	public void setPlaceDesciption(String placeDesciption) {
		this.placeDesciption = placeDesciption;
	}

	public List<String> getPlaceImages() {
		return placeImages;
	}

	public void setPlaceImages(List<String> placeImages) {
		this.placeImages = placeImages;
	}

	public String getPlaceRating() {
		return placeRating;
	}

	public void setPlaceRating(String placeRating) {
		this.placeRating = placeRating;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public String getPlaceOpenTime() {
		return placeOpenTime;
	}

	public void setPlaceOpenTime(String placeOpenTime) {
		this.placeOpenTime = placeOpenTime;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getPlacePrice() {
		return placePrice;
	}

	public void setPlacePrice(String placePrice) {
		this.placePrice = placePrice;
	}

	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}
}
