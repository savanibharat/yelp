package edu.sjsu.cmpe.yelp;

import java.net.UnknownHostException;
import java.util.Random;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;

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
	 */
	public static void main(String[] args) throws UnknownHostException, JSONException {

		MongoClient client = new MongoClient();
		DB db = client.getDB("YelpNewDataSet");
		DBCollection collection = db.getCollection("business");

		System.out.println("findOne()");
		BasicDBObject query=new BasicDBObject();
		BasicDBObject fields=new BasicDBObject("full_address",1).append("_id", false);
		DBObject doc = collection.findOne(query,fields);
		//System.out.println(doc.get("full_address"));
		String s=doc.get("full_address").toString();
		System.out.println(s);
	}
}