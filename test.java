package MatrixAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class test {
	private static int[][] matrix;
	private static ArrayList<Shop> shoplist = new ArrayList<Shop>();
	private static ArrayList<Order> orderlist = new ArrayList<Order>();
	private static int box =0;
	private static Set<String> skuNum = new HashSet<String>();
	private static Object[] pos ;
	private static int[] ignore;
	private static int[] covered;
	private static boolean state = true;
	private static Order order = null ;
	
	//控制台输入
		public static void ConsoleInput() {
			
			Scanner sc = new Scanner(System.in);
			
			//TODO:输入模拟数据库数据
			System.out.println("----------请输入每个sku对应的门店----------");
			System.out.println("shopcode\tsku");
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] input = line.split(" ");
				if(input[0].equals("exit"))return;
				//输入Shop第一项为shopCode，第二项为sku
				shoplist.add(new Shop(input[1],input[0]));
				}
			
		}

	
	public static void initMatrix(){
			/*	//数据初始化
				//门店1
				Shop shopOne = new Shop("100","sku2");
				shopOne.addShopItemlist("sku3");
				//门店2
				Shop shopTwo = new Shop("101","sku1");
				shopTwo.addShopItemlist("sku2");
				//门店3
				Shop shopThree = new Shop("102","sku1");
				
				shoplist.add(shopOne);
				shoplist.add(shopTwo);
				shoplist.add(shopThree);*/
		
				//构造sku的序号数组pos
				for(int i=0;i<shoplist.size();i++)
				{
				//	System.out.println(shoplist.get(i).getItem(0).getSku());
						skuNum.add(shoplist.get(i).getItem(0).getSku());
				}
			
				//		整合
				for(int i=0;i<shoplist.size();i++)
				{
					for(int j=i+1;j<shoplist.size();j++)
					{
						//System.out.println(shoplist.size()+"  "+i+" "+j+" "+shoplist.get(i).getShopCode()+"  "+shoplist.get(i).getItem(0).getSku());
						
						if(shoplist.get(j).getShopCode().equals(shoplist.get(i).getShopCode()))
						{
							shoplist.get(i).addShopItemlist(shoplist.get(j).getItem(0).getSku());
							shoplist.remove(j);
							j--;
						}
					}
				}
			
				
			/*	//构造sku的序号数组pos
				for(int i=0;i<shoplist.size();i++)
				{
					for(int j=0;j<shoplist.get(i).getShopItemNum();j++)
						skuNum.add(shoplist.get(i).getItem(j).getSku());
				}*/
				box=skuNum.size();
				pos = skuNum.toArray();
				ignore = new int[box];
				covered = new int[shoplist.size()];
				
				//shop的数量shoplist.size，sku的数量box
				matrix = new int[box][shoplist.size()];
				
				//矩阵赋值
				for(int i=0;i<shoplist.size();i++)
				{
					for(int k=0;k<shoplist.get(i).getShopItemNum();k++)
						for(int j=0;j<box;j++)
							{		
								if(pos[j].equals(shoplist.get(i).getItem(k).getSku()))	
								{
									matrix[j][i]=1;
									//System.out.println(shoplist.get(i).getShopCode()+"  "+shoplist.get(i).getItem(0).getSku());
								}
							}
				}
		
		
	}
	
	
	public static void showMatrix(){
		
		//矩阵显示
		for(int k=0;k<shoplist.size();k++){
			System.out.print("\t"+shoplist.get(k).getShopCode());
		}
		System.out.println("");
		for(int i=0;i<box;i++){
		System.out.print(pos[i]+"\t");
			for(int k=0;k<shoplist.size();k++){
				System.out.print(matrix[i][k]+"\t");
						}
			System.out.println(" ");
			}
		
	}


	
	//列求和
	public static int sumColum(int[] line,int[] down){
		int maxNum = 0;
		int maxColum = 0;
		int temp =0;
		/*for(int i=0;i<line.length;i++)
			System.out.println(line[i]);*/
		for(int i=0;i<shoplist.size();i++)
		{
			if(down[i]!=-1)
			{
				for(int j=0;j<box;j++)
				{
						if(line[j]!=-1) 
						 temp += matrix[j][i];
					
				}
				if(temp>maxNum) {maxNum = temp;maxColum=i;}
				temp=0;
			}
			
		}
		return maxColum;
	}
	
	
	
	//拆单
	public static void calculate(){
	
		//MySum ms = new MySum(matrix,box,shoplist);
		
		while(state){
			state=false;
			int p = sumColum(ignore,covered);
			covered[p]=-1;
			order = new Order(shoplist.get(p).getShopCode());
			for(int i=0;i<box;i++){
				if(matrix[i][p]==1&&ignore[i]!=-1)
					{				
					order.addOrderItemlist((String)pos[i]);
					ignore[i]=-1;
					}
			}
			orderlist.add(order);
			for(int i=0;i<box;i++){
				if(ignore[i]!=-1) state=true;
			}
		}

	}
		/*	for(int i=0;i<box;i++){
				if(matrix[i][p]==1) 
					{
					ignore[i]=-1;
					order.setOrderId(shoplist.get(p).getShopCode());
					order.addOrderItemlist((String)pos[i]);
					}
			}
			orderlist.add(order);
			//置状态
			for(int i=0;i<box;i++){
				//存在未分配的行就置状态为true
				if(ignore[i]!=-1) state=true;
			}*/
			
			//求和分组
			//分组最终输出到order
			//对各列求和
		
		
	
	
	public static void showResult(){
		//输出结果
				for(int i = 0;i<orderlist.size();i++){		
					for(int j=0;j<orderlist.get(i).getOrderItemNum();j++)
						System.out.println(orderlist.get(i).toString(j));
					}
		
	}
	
	
	
	public static void main(String[] args) {	
	
		ConsoleInput();
		long before = System.currentTimeMillis();
		initMatrix();
	/*	for(int i=0;i<ignore.length;i++)
			System.out.println(ignore[i]);*/
		showMatrix();
		calculate();
		showResult();
		long after = System.currentTimeMillis();
		System.out.println("------------运算时间------------");
		System.out.println("time="+(after-before)+"millis");

		}
}
