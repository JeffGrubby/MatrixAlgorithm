package MatrixAlgorithm;

public class Item {
	private String sku;
	private int amount;
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String sku){
		this.sku= sku;
		
	}
	
	
	public Item(String sku,int amount){
		this.sku= sku;
		this.amount = amount;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}



}
