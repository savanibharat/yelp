package edu.sjsu.cmpe.yelp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.UnknownHostException;

import org.codehaus.jettison.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * The Class FindTest.
 */
/**
 * @author Savani Bharat
 *
 */
public class YelpService {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnknownHostException the unknown host exception
	 * @throws JSONException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws UnknownHostException, JSONException, Exception {

		MongoClient client = new MongoClient();
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:\\tweets.txt")));
		DB db = client.getDB("twitter");
		DBCollection collection = db.getCollection("tweets");

		BasicDBObject query=new BasicDBObject();
		BasicDBObject fields=new BasicDBObject("text",1).append("_id", false);
		DBCursor cursor=collection.find(query,fields);
		int i=0;
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				bw.write(cur+"\n");
				i++;
				if(i%1000000==0)
					System.out.println("records done "+i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			bw.close();
		}
	}
}