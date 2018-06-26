package ModelDAO;

import Model.Link;
import MongoDB.MyConstants;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static MongoDB.MongoUtils.getMongoClient;

public class LinkDAO {
    MongoClient mongoClient;
    DB db;
    DBCollection linkDB;

    public LinkDAO() throws UnknownHostException {
        try {
            mongoClient = getMongoClient();
            db = mongoClient.getDB(MyConstants.DB_NAME);
            // Lấy ra Collection với tên Department.
            // Không nhất thiết Collection này phải tồn tại trong DB.
            linkDB = db.getCollection(MyConstants.LINK_COLLECTION_NAME);
        } catch (Exception e) {
        }
    }

    //Insert Link
    public void InsertLink(Link linkModel) {
        try {
            BasicDBObject object = new BasicDBObject();
            object.append(MyConstants.URL_FIELD, linkModel.getLinkText());
            //number Fail Request
            object.append(MyConstants.NUMBER_FAIL_REQUEST_FIELD, linkModel.getNumberFailedRequest());
            //number request
            object.append(MyConstants.NUMBER_REQUEST_FIELD, linkModel.getNumberRequest());
            //Flag to mark fail or success request
            object.append(MyConstants.FLAG_FIELD, linkModel.getFlag());
            linkDB.insert(object);
            System.out.println("Done!");
        } catch (Exception e) {
        }
    }

    //Inset List Link
    public void InsertListLink(List<Link> linkList) {
        try {
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

        } catch (Exception e) {
        }
    }

    //update link request success
    public void UpdateSuccessLink(Link linkModel) {
        try {
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
            System.out.println("Done!");
        } catch (Exception e) {
        }
    }

    //update link request fail
    public void UpdateFailLink(Link linkModel) {
        try {
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
            System.out.println("Done!");
        } catch (Exception e) {
        }
    }

    //delete by linktext
    public void DeleteLink(Link linkModel) {
        try {
            linkDB.remove(new BasicDBObject().append(MyConstants.URL_FIELD, linkModel.getLinkText()));
            System.out.println("Done!");
        } catch (Exception e) {
        }
    }
}
