package eu.kiszczak.crud.manager;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ManagerDB {
	
	private MongoClient mongoClient;
	private DB db;
	private String dbName;
	private DBCollection collection;
	private String collectionName;
	private int port;
	private String host;
	
	public ManagerDB(String host,int port,String dbName, String collectionName) {
		
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.collectionName = collectionName;
		init();
	}

	public void init(){
		try {
			
			this.mongoClient = new MongoClient(this.host,this.port);
			this.db = mongoClient.getDB(this.dbName);
			this.collection = this.db.getCollection(this.collectionName);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
	}

	public DB getDb() {
		return this.db;
	}

	public DBCollection getCollection() {
		return this.collection;
	}
	
	public void closeConnection(){
		this.mongoClient.close();
	}
	
}
