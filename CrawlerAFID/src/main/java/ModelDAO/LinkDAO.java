package ModelDAO;

import Model.Link;
import MongoDB.MyConstants;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import static MongoDB.MongoUtils.getMongoClient_1;

public class LinkDAO {

    public LinkDAO() {
    }
    //Insert Link
    public Boolean InsertLink(Link linkModel) {
        MongoClient mongoClient = null;
        DB db;
        DBCollection linkDB;
        try {
            mongoClient = getMongoClient_1();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Link.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
            BasicDBObject object = new BasicDBObject();
            object.append(MyConstants.URL_FIELD, linkModel.getLinkText());
            //number Fail Request
            object.append(MyConstants.NUMBER_FAIL_REQUEST_FIELD, linkModel.getNumberFailedRequest());
            //number request
            object.append(MyConstants.NUMBER_REQUEST_FIELD, linkModel.getNumberRequest());
            //Flag to mark fail or success request
            object.append(MyConstants.FLAG_FIELD, linkModel.getFlag());
            linkDB.insert(object);
            mongoClient.close();
            return true;
        } catch (Exception e) {
            mongoClient.close();
            return false;
        }

    }

    //Inset List Link
    public boolean InsertListLink(List<Link> linkList) {
        MongoClient mongoClient = null;
        DB db;
        DBCollection linkDB;
        try {
            mongoClient = getMongoClient_1();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Link.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
            DBObject object;
            List<DBObject> dbObjectList = new ArrayList<DBObject>();
            for (Link linkModel : linkList) {
                object = new BasicDBObject();
                object.put(MyConstants.URL_FIELD, linkModel.getLinkText());
                //number Fail Request
                object.put(MyConstants.NUMBER_FAIL_REQUEST_FIELD, linkModel.getNumberFailedRequest());
                //number request
                object.put(MyConstants.NUMBER_REQUEST_FIELD, linkModel.getNumberRequest());
                //Flag to mark fail or success request
                object.put(MyConstants.FLAG_FIELD, linkModel.getFlag());
                dbObjectList.add(object);
            }
            linkDB.insert(dbObjectList);
            mongoClient.close();
            return true;

        } catch (Exception e) {
            mongoClient.close();
            return false;
        }
    }

    //update link request success
    public Boolean UpdateSuccessLink(Link linkModel) {
        MongoClient mongoClient = null;
        DB db;
        DBCollection linkDB;
        try {
            mongoClient = getMongoClient_1();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Link.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
            BasicDBObject whereClause = new BasicDBObject(MyConstants.URL_FIELD, linkModel.getLinkText());
            BasicDBObject object = new BasicDBObject();
            object.append(MyConstants.FLAG_FIELD, true);
            BasicDBObject object1 = new BasicDBObject();
            object1.append(MyConstants.NUMBER_REQUEST_FIELD, 1);
            //change status flag in db(true if success)
            //increase reuqest time  1
            BasicDBObject values = new BasicDBObject();
            values.put("$set", object);
            values.put("$inc", object1);
            // excute update flag URL.
            WriteResult result = linkDB.update(whereClause, values);
            mongoClient.close();
            return true;
        } catch (Exception e) {
            mongoClient.close();
            return false;
        }
    }

    //update link request fail
    public Boolean UpdateFailLink(Link linkModel) {
        MongoClient mongoClient = null;
        DB db;
        DBCollection linkDB;
        try {
            mongoClient = getMongoClient_1();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Link.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
            BasicDBObject whereClause = new BasicDBObject(MyConstants.URL_FIELD, linkModel.getLinkText());
            BasicDBObject object = new BasicDBObject();
            object.append(MyConstants.FLAG_FIELD, false);
            BasicDBObject object1 = new BasicDBObject();
            object1.append(MyConstants.NUMBER_REQUEST_FIELD, 1);
            object1.append(MyConstants.NUMBER_FAIL_REQUEST_FIELD, 1);
            //change status flag in db(true if success)
            //increase request time  1
            //increase fail time 1
            BasicDBObject values = new BasicDBObject();
            values.put("$set", object);
            values.put("$inc", object1);
            // excute update flag URL.
            WriteResult result = linkDB.update(whereClause, values);
            clone();
            return true;
        } catch (Exception e) {
            mongoClient.close();
            return false;
        }
    }

    //delete by linktext
    public Boolean DeleteLink(Link linkModel) {
        MongoClient mongoClient = null;
        DB db;
        DBCollection linkDB;
        try {
            mongoClient = getMongoClient_1();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Link.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
            linkDB.remove(new BasicDBObject().append(MyConstants.URL_FIELD, linkModel.getLinkText()));
            mongoClient.close();
            return true;
        } catch (Exception e) {
            mongoClient.close();
            return false;
        }
    }
}
