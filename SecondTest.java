package myTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;





public class SecondTest {
	private static List<Map<String,Object>> shopList = new ArrayList<Map<String,Object>>();
	private static List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
	private static int[][] matrix;
	
	private static Object[] skuArray ;
	private static Object[] shopArray ;
	

	private static int[] rowIgnore;
	private static int[] columnIgnore;
	private static boolean rowState = true;
	
	
	
	public static void ConsoleInput() {
		Map<String,Object> tempMap;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("sku\tshopcode");
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] input = line.split(" ");
			if(input[0].equals("exit"))return;
			
			tempMap = new HashMap<String,Object>();
			tempMap.put("sku",input[1]);                                                                        
			tempMap.put("shopcode",input[0]);
			shopList.add(tempMap); 
		}	
	}
	
	//TODO 初始化并输出矩阵
	public static void initMatrix(){
		 Set<String> skuNum = new HashSet<String>();
		 Set<String> shopNum = new HashSet<String>();
		 
			for(Map<String,Object> param :shopList) {
				skuNum.add(String.valueOf(param.get("sku")));
				shopNum.add(String.valueOf(param.get("shopcode")));
			}
			int row;
			int column;
			
			row=skuNum.size();
			column=shopNum.size();
			
			skuArray = skuNum.toArray();
			shopArray = shopNum.toArray();
			
			rowIgnore = new int[row];
			columnIgnore = new int[column];
			
			//TODO 矩阵赋值
			matrix = new int[row][column];
			for(Map<String,Object> param :shopList) {
				int i,j;
				for(i=0;i<row;i++) {
					if(skuArray[i].toString().equals(param.get("sku"))) {
						break;
					}
				}
				
				for(j=0;j<column;j++) {
					if(shopArray[j].toString().equals(param.get("shopcode"))) {
						break;
					}
				}
				matrix[i][j] = 1;
			}
			
			//TODO 输出结果
			for(int i=0;i<column;i++) {
				System.out.print("\t"+shopArray[i]);
			}
			System.out.println("");
			for(int i=0;i<row;i++) {
				System.out.print(skuArray[i]+"\t");
				for(int j=0;j<column;j++) {
					System.out.print("["+matrix[i][j]+"]\t");
				}
				System.out.println("");
			}
	
}
	
	
	//TODO 求列和
	public static int sumColum(int[] row,int[] column){
		int maxNum = 0;
		int maxColum = 0;
		int temp =0;
		
		for(int i=0;i<column.length;i++)
		{
			if(column[i]!=-1)
			{
				for(int j=0;j<row.length;j++)
				{
						if(row[j]!=-1) 
						 temp += matrix[j][i];
					
				}
				if(temp>maxNum) {maxNum = temp;maxColum=i;}
				temp=0;
			}
			
		}
		return maxColum;
	}

	
	
	
	public static void calculate(){
		Map<String,Object> tempMap ;
		
		while(rowState){
			rowState=false;
			int p = sumColum(rowIgnore,columnIgnore);
			columnIgnore[p]=-1;
			tempMap = new HashMap<String,Object>();
			
			for(int i=0;i<rowIgnore.length;i++){
				if(rowIgnore[i]!=-1&&matrix[i][p]==1)
					{	
					tempMap.put("shopcode",shopArray[p]);
					tempMap.put("sku",skuArray[i]);
					//System.out.println(shopArray[p]+" "+skuArray[i]);
					orderList.add(tempMap);
					tempMap = new HashMap<String,Object>();
					rowIgnore[i]=-1;
					}
				
			}
			
			for(int i=0;i<rowIgnore.length;i++){
				if(rowIgnore[i]!=-1) rowState=true;
			}
		}

	}
	
	
	

	public static void showResult(){
		
		for(Map<String,Object> param :orderList){
			System.out.println(param.get("shopcode")+"  "+param.get("sku"));
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		ConsoleInput();
		long before = System.currentTimeMillis();
		initMatrix();

		calculate();
		showResult();
		long after = System.currentTimeMillis();
		System.out.println("------------------------");
		System.out.println("time="+(after-before)+"millis");
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
