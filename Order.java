package MatrixAlgorithm;

import java.util.ArrayList;
//输出的order列表
public class Order {
	private String orderId;
	private ArrayList<Item> orderItemlist = new ArrayList();
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	//不输出数量 sku
	public Order(String orderId){
		this.orderId=orderId;
	}
	//不输出数量
	public Order(String orderId,String sku){
		this.orderId=orderId;
		orderItemlist.add(new Item(sku));
	}
	//输出数量
	
	public Order(String orderId,String sku,int amount){
		this.orderId=orderId;
		orderItemlist.add(new Item(sku,amount));
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	//return Item
	public Item getItem(int i){
		return orderItemlist.get(i);
		
	}
	//add sku
	public void addOrderItemlist(String sku){
		orderItemlist.add(new Item(sku));
		
	}
	//return sku数量
	public int getOrderItemNum(){
		return orderItemlist.size();
	}
	
	public String toString(int i){
		return orderId+"    "+orderItemlist.get(i).getSku();
	}


}

