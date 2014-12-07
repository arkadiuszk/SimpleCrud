package eu.kiszczak.crud;

import java.util.List;

import config.Configuration;
import eu.kiszczak.crud.dao.ProductDaoImpl;
import eu.kiszczak.crud.manager.ManagerDB;
import eu.kiszczak.crud.product.Product;

/**
 * Created by
 * Arkadiusz Kiszczak
 *
 */
public class Main 
{
	
	private static void display(List<Product> list){
		if(list == null || list.size() ==0){
			System.out.println("There is no products on the list");
			return;
		}
		for(Product product: list){
      		System.out.println(product.toString());
      	}
	}
	
    public static void main( String[] args )
    {
    	ManagerDB managerDB = (new Configuration()).createConnection();
    	ProductDaoImpl dao = new ProductDaoImpl(managerDB);
    	dao.deleteAllProducts();
    	Product product1 = new Product("NBG5715", "0Z01234","091-123456","Router");  
    	Product product2 = new Product("P-871M", "0Z09876","091-129876", "Modem");  
    	dao.add(product1);
    	dao.add(product2);
      	display(dao.getAllProduct());
    	dao.add(new Product("P-2601HNL-Fx", "0Z09643","091-129734"));
    	display(dao.getAllProduct());
    	System.out.println("Update - product1; Delete - product2");
    	product1.setSn("0Z01111");
    	dao.delete(product1);
    	dao.update(product2);
    	display(dao.getAllProduct());	
    	dao.add(product2);
    	display(dao.getAllProduct());
    	System.out.println("Delete all products");
    	dao.deleteAllProducts();
    	display(dao.getAllProduct());
    }
    
    
}
