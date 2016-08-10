package MatrixAlgorithm;

import java.util.ArrayList;
//sql 命中的门店
//received order Item counted
//只包含sql选择的order sku 并不一定是所有的sku
public class Shop {
	private String shopCode;
	private ArrayList<Item> shopItemlist = new ArrayList();
	
	public Shop() {
		// TODO Auto-generated constructor stub
		
	}
	//矩阵构造 （不需要数量）
	public Shop(String shopCode,String sku){
		this.shopCode=shopCode;
		shopItemlist.add(new Item(sku));
	}
	
	public Shop(String shopCode,String sku,int amount){
		this.shopCode=shopCode;
		shopItemlist.add(new Item(sku,amount));
	}
	//return Item
	public Item getItem(int i){
		return shopItemlist.get(i);
		
	}
	
	//add sku
	public void addShopItemlist(String sku){
		shopItemlist.add(new Item(sku));
		
	}
	//return sku数量
	public int getShopItemNum(){
		return shopItemlist.size();
	}
	

	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String toString(){
		return shopCode;
		
	}

}

