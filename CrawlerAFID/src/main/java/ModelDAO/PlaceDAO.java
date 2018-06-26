package ModelDAO;

import Model.Comment;
import Model.Link;
import Model.Place;
import MongoDB.MyConstants;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import static MongoDB.MongoUtils.getMongoClient;

public class PlaceDAO {

    MongoClient mongoClient;
    DB db;
    DBCollection placeDB;

    public PlaceDAO() throws UnknownHostException {
        try {
            mongoClient = getMongoClient();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Department.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            placeDB = db.getCollection(MyConstants.PLACE_COLLECTION_NAME);
        } catch (Exception e) {
        }
    }

    //insert one place
    public void InsertPlace(Place placeModel) {
        try {
            BasicDBObject object = new BasicDBObject();
            List<DBObject> dbObjectList = new ArrayList<DBObject>();
            DBObject commentObject;
            //convert from model to mongo object
            object.append(MyConstants.PLACE_ID_FIELD, placeModel.getId());
            object.append(MyConstants.PLACE_NAME_FIELD, placeModel.getPlaceName());
            object.append(MyConstants.PLACE_ADDRESS_FIELD, placeModel.getPlaceAddress());
            object.append(MyConstants.PLACE_DESCRIPTION_FIELD, placeModel.getPlaceDesciption());
            object.append(MyConstants.PLACE_IMAGE_FIELD, placeModel.getPlaceImages());
            object.append(MyConstants.PLACE_OPENTIME_FIELD, placeModel.getPlaceOpenTime());
            object.append(MyConstants.PLACE_PRICE_FIELD, placeModel.getPlacePrice());
            object.append(MyConstants.PLACE_RATING_FIELD, placeModel.getPlaceRating());
            object.append(MyConstants.PLACE_TYPE_FIELD, placeModel.getPlaceType());
            object.put(MyConstants.PLACE_lINKTEXT_FIELD, placeModel.getLinkText());
            for (Comment comment : placeModel.getListComment()) {
                commentObject = new BasicDBObject();
                commentObject.put(MyConstants.COMMENT_USER_FIELD, comment.getUserName());
                commentObject.put(MyConstants.COMMENT_CREATEDATE_FIELD, comment.getCreatedDate());
                commentObject.put(MyConstants.COMMENT_CONTENT_FIELD, comment.getCommentDesciption());
                dbObjectList.add(commentObject);
            }
            object.append(MyConstants.PLACE_COMMENTS_FIELD, dbObjectList);
            placeDB.insert(object);
            System.out.println("Done!");
        } catch (Exception e) {
        }
    }

    //insert list place
    public void InserListPlace(List<Place> placeList) {
        try{
            DBObject object;
            DBObject commentObject;
            List<DBObject> commentList;
            List<DBObject> objectList = new ArrayList<DBObject>();
            for (Place placeModel : placeList) {
                //convert from model to mongo object
                commentList = new ArrayList<DBObject>();
                object = new BasicDBObject();
                object.put(MyConstants.PLACE_NAME_FIELD, placeModel.getPlaceName());
                object.put(MyConstants.PLACE_ADDRESS_FIELD, placeModel.getPlaceAddress());
                object.put(MyConstants.PLACE_DESCRIPTION_FIELD, placeModel.getPlaceDesciption());
                object.put(MyConstants.PLACE_IMAGE_FIELD, placeModel.getPlaceImages());
                object.put(MyConstants.PLACE_OPENTIME_FIELD, placeModel.getPlaceOpenTime());
                object.put(MyConstants.PLACE_PRICE_FIELD, placeModel.getPlacePrice());
                object.put(MyConstants.PLACE_RATING_FIELD, placeModel.getPlaceRating());
                object.put(MyConstants.PLACE_TYPE_FIELD, placeModel.getPlaceType());
                object.put(MyConstants.PLACE_lINKTEXT_FIELD, placeModel.getLinkText());
                object.put(MyConstants.PLACE_ID_FIELD, placeModel.getId());
                for (Comment comment : placeModel.getListComment()) {
                    commentObject = new BasicDBObject();
                    commentObject.put(MyConstants.COMMENT_USER_FIELD, comment.getUserName());
                    commentObject.put(MyConstants.COMMENT_CREATEDATE_FIELD, comment.getCreatedDate());
                    commentObject.put(MyConstants.COMMENT_CONTENT_FIELD, comment.getCommentDesciption());
                    commentList.add(commentObject);
                }
                object.put(MyConstants.PLACE_COMMENTS_FIELD, commentList);
                objectList.add(object);
            }
            placeDB.insert(objectList);
        }catch (Exception e){}

    }

    //delet by linktext of place
    public void DeletePLace(Place place) {
        try {
            placeDB.remove(new BasicDBObject().append(MyConstants.PLACE_lINKTEXT_FIELD, place.getLinkText()));
        } catch (Exception e) {
        }
    }
}
