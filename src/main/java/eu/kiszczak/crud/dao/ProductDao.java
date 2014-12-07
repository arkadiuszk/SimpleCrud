package eu.kiszczak.crud.dao;

import java.util.List;

import eu.kiszczak.crud.product.Product;

public interface ProductDao {

	Product add(Product product);
	void delete(Product product);
	void update(Product product);
	Product getProduct(Product product);
	List<Product> getAllProduct();
	
}
