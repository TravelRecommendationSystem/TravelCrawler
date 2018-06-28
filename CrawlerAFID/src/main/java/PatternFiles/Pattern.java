package PatternFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Pattern {
	String rootUrl;
	String domain;
	String name;
	String description;
	String image;
	String rating;
	String address;
	String openTime;
	String placeType;
	String placePrice;
	String commentUsername;
	String commentDescription;
	String commentCreatedDate;

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlacePrice() {
		return placePrice;
	}

	public void setPlacePrice(String placePrice) {
		this.placePrice = placePrice;
	}
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCommentUsername() {
		return commentUsername;
	}

	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public String getCommentCreatedDate() {
		return commentCreatedDate;
	}

	public void setCommentCreatedDate(String commentCreatedDate) {
		this.commentCreatedDate = commentCreatedDate;
	}

	public Pattern() {
	}
	
	public Pattern(String domain) {
		super();
		this.domain = domain;
	}

	public void getDataFromPattern() {
		InputStream inputFile = null;
		try {
			inputFile = new FileInputStream("src/main/java/PatternFiles/detail_pattern.json");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot read this file");
		}
		JsonReader jsonReader = Json.createReader(inputFile);
		JsonObject rootObject = jsonReader.readObject();

		// Get data for tripadvisor
		JsonObject tripadvisorObject = rootObject.getJsonObject(domain);
		JsonObject tripadvisorCommentObject = tripadvisorObject.getJsonObject("comments");
		// Get fields
		this.setRootUrl(tripadvisorObject.getString("rootUrl"));
		this.setName(tripadvisorObject.getString("name"));
		this.setDescription(tripadvisorObject.getString("description"));
		this.setImage(tripadvisorObject.getString("image"));
		this.setRating(tripadvisorObject.getString("rating"));
		this.setAddress(tripadvisorObject.getString("address"));
		this.setOpenTime(tripadvisorObject.getString("openTime"));
		this.setPlaceType(tripadvisorObject.getString("placeType"));
		this.setPlacePrice(tripadvisorObject.getString("placePrice"));
		this.setCommentUsername(tripadvisorCommentObject.getJsonObject("userName").getString("target"));
		this.setCommentDescription(tripadvisorCommentObject.getJsonObject("description").getString("target"));
		this.setCommentCreatedDate(tripadvisorCommentObject.getJsonObject("createdDate").getString("target"));
	}
	//IF don't exist column catch NullPointerException
	// Test
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\n rootUrl: " + this.getRootUrl());
		result.append("\n name: " + this.getName());
		result.append("\n address: " + this.getAddress());
		result.append("\n rating: " + this.getRating());
		result.append("\n desc: " + this.getDescription());
		result.append("\n type: " + this.getPlaceType());
		result.append("\n time: " + this.getOpenTime());
		result.append("\n username: " + this.getCommentUsername());
		result.append("\n comment: " + this.getCommentDescription());
		result.append("\n date: " + this.getCommentCreatedDate());
		return result.toString();
	}

	public static void main(String[] arg) {
		Pattern trip = new Pattern();
		trip.setDomain("tripadvisor.com");
		trip.getDataFromPattern();
		System.out.println(trip.getRootUrl());
	}
}
