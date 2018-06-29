package MongoDB;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoUtils {
	private static final String HOST = "localhost";
	private static final int PORT = 27017;

	//
	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	// Cách kết nối vào MongoDB không bắt buộc bảo mật.
	public static MongoClient getMongoClient_1() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(HOST, PORT);
		return mongoClient;
	}

	// Cách kết nối vào DB MongoDB có bảo mật.
	public static MongoClient getMongoClient_2() throws UnknownHostException {
		MongoCredential credential = MongoCredential.createMongoCRCredential(USERNAME, MyConstants.DB_NAME,
				PASSWORD.toCharArray());

		MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));
		return mongoClient;
	}
	//Close connection

	public static MongoClient getMongoClient() throws UnknownHostException {
		// Kết nối vào MongoDB không bắt buộc bảo mật.
		return getMongoClient_1();
		// Bạn có thể thay thế bởi getMongoClient_2()
		// trong trường hợp kết nối vào MongoDB có bảo mật.
	}
//	//Test for connect DB
//	private static void ping() throws UnknownHostException {
//		MongoClient mongoClient = getMongoClient();
//
//		System.out.println("List all DB:");
//
//		// Danh sách các DB Names.
//		List<String> dbNames = mongoClient.getDatabaseNames();
//		for (String dbName : dbNames) {
//			System.out.println("+ DB Name: " + dbName);
//		}
//
//		System.out.println("Done!");
//	}
//
//	// Test
//	public static void main(String[] args) throws UnknownHostException {
//		ping();
//	}
}
