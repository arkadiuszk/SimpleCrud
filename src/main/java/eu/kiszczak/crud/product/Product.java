package eu.kiszczak.crud.product;

public class Product {
	
	private String id;
	private String name;
	private String sn;
	private String pn;
	private String category;
	
	public Product(){}
	
	public Product( String name, String sn, String pn){
		this(null, name, sn, pn, null);
	}
	
	public Product( String name, String sn, String pn, String category){
		this(null, name, sn, pn, category);
	}
	
	public Product(String id,String name, String sn, String pn, String category){
		
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.pn = pn;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {

		return "Product [ id = "+this.id+", name = "+this.name+", sn = "+this.sn+", pn = "+this.pn+(this.category!=null?", category = "+this.category:"")+" ]";
	}	
	
	
}
