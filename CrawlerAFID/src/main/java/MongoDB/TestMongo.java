package MongoDB;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static MongoDB.MongoUtils.*;

public class TestMongo {
    //Ket noi mongo DB
    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = getMongoClient();

        // Kết nối tới Database
        // (Không nhất thiết DB này phải tồn tại sẵn
        // nó sẽ được tự động tạo ra nếu chưa tồn tại).
        DB db = mongoClient.getDB(MyConstants.DB_NAME);

        // Lấy ra Collection với tên Department.
        // Không nhất thiết Collection này phải tồn tại trong DB.
        DBCollection dept = db.getCollection("Department");

        // Insert Document 1
//        BasicDBObject doc1 = new BasicDBObject();
//        doc1.append("_id", 11);
//        doc1.append("dept_no", "D10");
//        doc1.append("dept_name", "ACCOUNTING");
//        doc1.append("location", "NEW YORK");
//        dept.insert(doc1);
//
//        // Insert Document 2
//
//        BasicDBObject doc2 = new BasicDBObject();
//        doc2.append("_id", 21);
//        doc2.append("dept_no", "D20");
//        doc2.append("dept_name", "RESEARCH");
//        doc2.append("location", "DALLAS");
//        doc2.append("description", "First department");
//        dept.insert(doc2);
//
//        // Insert Document 3
//        BasicDBObject doc3 = new BasicDBObject();
//        doc3.append("_id", 31);
//        doc3.append("dept_no", "D30");
//        doc3.append("dept_name", "SALES");
//        doc3.append("location", "CHICAGO");
//        dept.insert(doc3);

        List<String> cmt =  new ArrayList();
        cmt.add("a");
        cmt.add("b");
        cmt.add("c");
        // Insert Document 4

        BasicDBObject doc4 = new BasicDBObject();
        //doc4.append("_id", 42);
        doc4.append("dept_no", "D40");
        doc4.append("dept_name", "OPERATIONS");
        doc4.append("location", "BOSTON");
        doc4.append("cmt",cmt);
        dept.insert(doc4);

        System.out.println("Done!");
    }
    }

