package eu.kiszczak.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.kiszczak.crud.manager.ManagerDB;
import eu.kiszczak.crud.product.Product;

public class ProductDaoImpl implements ProductDao{
	
	private ManagerDB managerDB;
	
	public ProductDaoImpl(ManagerDB managerDB){
		this.managerDB = managerDB;
	}
		
	private boolean isUnique(DBObject doc){
		DBObject dbObject = (DBObject) this.managerDB.getCollection().findOne(doc);
		
		if(dbObject!=null){
			System.out.println("Product exist in database "+doc.toString());
			return true;
		}
		return false;
	}

	public Product add(Product product) {
		DBObject doc = createDBObject(product);
		if(isUnique(doc)){
			return new Product();
		}
		   
		   DBCollection col = managerDB.getCollection();
		   col.insert(doc);
		   ObjectId id = (ObjectId) doc.get("_id");
		   product.setId(id.toString());
		   return product;
	}
	
	public void update(Product product) {
		 DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(product.getId())).get();
		 this.managerDB.getCollection().update(query, createDBObject(product));
	}

	public void delete(Product product) {
		DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(product.getId())).get();
		this.managerDB.getCollection().remove(query);	
	}
	
	public void deleteAllProducts() {
		this.managerDB.getCollection().drop();
	}

	public Product getProduct(Product product) {
		DBObject query = BasicDBObjectBuilder.start().append("_id", new ObjectId(product.getId())).get();
        DBObject data = this.managerDB.getCollection().findOne(query);
		return createProductObject(data);
	}

	public List<Product> getAllProduct() {
		
	    List<Product> list = new ArrayList<Product>();
        DBCursor cursor = this.managerDB.getCollection().find();
        while (cursor.hasNext()) {
            list.add(createProductObject(cursor.next()));
        }
        return list;

	}
	
	private DBObject createDBObject(Product product){
		   BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start();
		   if (product.getId() != null)
		   documentBuilder.append("_id", new ObjectId(product.getId()));
		   documentBuilder.append("name", product.getName());
		   documentBuilder.append("sn", product.getSn());
		   documentBuilder.append("pn", product.getPn());
		   documentBuilder.append("category", product.getCategory());
		   return documentBuilder.get();
	}
	
	private Product createProductObject(DBObject dBObject){
		
		return new Product(((ObjectId) dBObject.get("_id")).toString(), 
				(String) dBObject.get("name"),
				(String) dBObject.get("sn"),
				(String) dBObject.get("pn"),
				(String) dBObject.get("category"));
	}

}
