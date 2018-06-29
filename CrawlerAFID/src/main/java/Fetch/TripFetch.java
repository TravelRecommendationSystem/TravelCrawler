package Fetch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.openqa.selenium.WebDriver;

public class TripFetch {
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
	int indexTime;
	int indexType;

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

	public int getIndexTime() {
		return indexTime;
	}

	public void setIndexTime(int indexTime) {
		this.indexTime = indexTime;
	}

	public int getIndexType() {
		return indexType;
	}

	public void setIndexType(int indexType) {
		this.indexType = indexType;
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

	public TripFetch() {
	}

	public TripFetch(String domain) {
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
		JsonObject tripObject = rootObject.getJsonObject(domain);
		JsonObject tripCommentObject = tripObject.getJsonObject("comments");
		// Get fields
		try {
			this.setRootUrl(tripObject.getString("rootUrl"));
			this.setName(tripObject.getString("name"));
			this.setDescription(tripObject.getString("description"));
			this.setImage(tripObject.getString("image"));
			this.setRating(tripObject.getString("rating"));
			this.setAddress(tripObject.getString("address"));
			this.setPlaceType(tripObject.getJsonObject("placeType").getString("target"));
			this.setIndexType(tripObject.getJsonObject("placeType").getInt("index"));
			this.setCommentUsername(tripCommentObject.getJsonObject("userName").getString("target"));
			this.setCommentDescription(tripCommentObject.getJsonObject("description").getString("target"));
			this.setCommentCreatedDate(tripCommentObject.getJsonObject("createdDate").getString("target"));
		} catch (NullPointerException e) {
			System.out.println("Default fields not found");
		}

		try {
			this.setPlacePrice(tripObject.getString("placePrice"));
		} catch (NullPointerException e) {
			System.out.println("PlacePrice not found");
		}
		try {
			this.setOpenTime(tripObject.getJsonObject("openTime").getString("target"));
			this.setIndexTime(tripObject.getJsonObject("openTime").getInt("index"));
		} catch (NullPointerException e) {
			System.out.println("OpenTime not found");
		}
	}

	// IF don't exist column catch NullPointerException
	public void getDocument(WebDriver driver, String userName, String passWord) {

	}

	public List<String> expandAllPlaces(WebDriver driver) {
		List<String> linkList = new ArrayList<String>();
		return linkList;
	}

	public List<String> getLinkList(WebDriver driver) {
		List<String> linkList = new ArrayList<String>();
		return linkList;
	}

}
