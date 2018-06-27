package PatternFiles;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*Class lay du lieu tu file pattern */
public class TRIPNOW {
    String rootUrl;
    String detailLink;
    String name;
    String address;
    String rating;
    String description;
    String openTime;
    String placeprice;
    String placeType;
    String commentUsername;
    String commentDescription;
    String commentCreatedDate;

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
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

    public String getPlaceprice() {
        return placeprice;
    }

    public void setPlaceprice(String placeprice) {
        this.placeprice = placeprice;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
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

    public TRIPNOW(){
        getDataFromPattern();
    }

    public void getDataFromPattern(){
        InputStream inputFile = null;
        try {
            inputFile = new FileInputStream("src/main/java/PatternFiles/detail_pattern.json");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read this file");
        }


        JsonReader jsonReader = Json.createReader(inputFile);
        JsonObject rootObject = jsonReader.readObject();

        //Get data for tripnow
        JsonObject tripnowObject = rootObject.getJsonObject("tripnow.vn");
        JsonObject tripnowCommentObject = tripnowObject.getJsonObject("comments");

        this.setRootUrl(tripnowObject.getString("rootUrl"));
        this.setDetailLink(tripnowObject.getJsonObject("detailLink").getString("target"));
        this.setName(tripnowObject.getJsonObject("name").getString("target"));
        this.setAddress(tripnowObject.getJsonObject("address").getString("target"));
        this.setRating(tripnowObject.getJsonObject("rating").getString("target"));
        this.setDescription(tripnowObject.getJsonObject("description").getString("target"));
        this.setOpenTime(tripnowObject.getJsonObject("openTime").getString("target"));
        this.setPlaceprice(tripnowObject.getJsonObject("placePrice").getString("target"));
        this.setPlaceType(tripnowObject.getJsonObject("placeType").getString("target"));
        this.setCommentUsername(tripnowCommentObject.getJsonObject("userName").getString("target"));
        this.setCommentDescription(tripnowCommentObject.getJsonObject("description").getString("target"));
        this.setCommentCreatedDate(tripnowCommentObject.getJsonObject("createdDate").getString("target"));
    }

    //Test
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\n rootUrl: " + this.getRootUrl());
        result.append("\n detailink: " + this.getDetailLink());
        result.append("\n name: " + this.getName());
        result.append("\n address: " + this.getAddress());
        result.append("\n rating: " + this.getRating());
        result.append("\n desc: " + this.getDescription());
        result.append("\n type: " + this.getPlaceType());
        result.append("\n time: " + this.getOpenTime());
        result.append("\n price: " + this.getPlaceprice());
        result.append("\n username: " + this.getCommentUsername());
        result.append("\n comment: " + this.getCommentDescription());
        result.append("\n date: " + this.getCommentCreatedDate());
        return result.toString();
    }
}
