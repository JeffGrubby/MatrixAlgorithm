package myTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Algorithm.myTest
 * @author JeffGrubb
 * @version 1.0
 * 2016����11:41:39
 */


//�����㷨--MatrixAlgorithm

public class TestCopy {
	//private static List<Map<String,Object>> shopList = new ArrayList<Map<String,Object>>();
	private static int[][] matrix;
	
	private static Object[] skuArray ;
	private static Object[] shopArray ;

	private static int[] ReposRow;
	private static int[] ReposColumn;
	
	private static int[] rowIgnore;
	private static int[] columnIgnore;
	
	private static boolean ReposState;
	private static boolean rowState = true;
	
	
	/**
	 * @param shopList
	 * TODO ��ʼ�����������
	 */
	public static void initMatrix(List<Map<String, Object>> shopList) {
			
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
				
				skuArray =skuNum.toArray();
				shopArray = shopNum.toArray();
				
				ReposRow = new int[row];
				ReposColumn = new int[column];
				
				//TODO ����ֵ
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
				//�����5�Ų�
				if(shopNum.contains("Repos"))
				{
					ReposFun();
					ReposState = true;
				}
				
				//TODO ������
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
	
	/**
	 * TODO �󻮷�����������(���ⲿ����ѡ��--private)
	 * @param row
	 * @param column
	 * @return
	 */
	private static int sumColum(int[] row,int[] column){
				int maxNum = 0;
				int maxColumn = 0;
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
						if(temp>maxNum) {maxNum = temp;maxColumn=i;}
						temp=0;
						
					}

				}
				return maxColumn;
			}
		//��5�Ųֺ�������(���ⲿ����ѡ��--private)
	private  static void ReposFun(){
				for(int i=0;i<shopArray.length;i++)
				{
					//�ҵ�5�Ųֺ����������ѭ��
					if(shopArray[i].equals("Repos"))
					{
						ReposColumn[i]=-1;
						for(int j=0;j<skuArray.length;j++)
						{
							if(matrix[j][i]==1)
								ReposRow[j]=-1;
						}
						break;
					}
					
				}
			}
			
	/**
	 *  ɸѡ����
	 * @param matrix
	 * @param ReposRow
	 * @param ReposColumn
	 * @return List<TargetShop>
	 */
	public static List<TargetShop> selectShop(int[][] matrix, int[] ReposRow, int[] ReposColumn) {

			TargetShop temp ;
			List<TargetShop> targetShopList = new ArrayList<TargetShop>();
			rowIgnore = ReposRow.clone();
			columnIgnore = ReposColumn.clone();
			
			
			//skuȫ�����޻�
			int k =0;
			for(int i=0;i<skuArray.length;i++){
				for(int j=0;j<shopArray.length;j++)
				{
					if(matrix[i][j]==0) k++;
					
				}
				if(k==shopArray.length){
					rowIgnore[i]=-1;
					temp = new TargetShop();
					temp.setShopCode("NOT");
					temp.getSku().add(skuArray[i].toString());
					targetShopList.add(temp);
				}
				k=0;
			}
			
			
			//������5�Ų�δ��ƥ�� ��д������
			if(ReposState)
			{
				temp = new TargetShop();
				for(int i=0;i<ReposColumn.length;i++)
				{
					//�ҵ�5�Ų����о͸�ֵ������ѭ��
					if(ReposColumn[i]==-1)
					{
						temp.setShopCode(shopArray[i].toString());
						for(int j=0;j<ReposRow.length;j++)
							if(matrix[j][i]==1)
							{					
								 temp.getSku().add(skuArray[j].toString());	
							}
						targetShopList.add(temp);
						break;
					}
				}
				ReposState = false;
			}
			
			
			rowState = false ;
					
			for(int i=0;i<rowIgnore.length;i++){
				if(rowIgnore[i]!=-1) {rowState=true;break;}
				}
			
			while(rowState){	
					rowState=false;
					int p = sumColum(rowIgnore,columnIgnore);
					//����p���ŵ�
					columnIgnore[p]=-1;
					temp = new TargetShop();
					//�����ŵ���
					temp.setShopCode(shopArray[p].toString());
					for(int i=0;i<rowIgnore.length;i++)
					{
						if(rowIgnore[i]!=-1&&matrix[i][p]==1)
							{
							//�����ŵ��Ӧsku
							temp.getSku().add(skuArray[i].toString());
							rowIgnore[i]=-1;
							}
					}
					
					targetShopList.add(temp);
					
					for(int i=0;i<rowIgnore.length;i++)
					{
						if(rowIgnore[i]!=-1) {rowState=true;break;}
					}
				}			
		//չʾ	
			for(TargetShop target :targetShopList){
				for(int i =0;i<target.getSku().size();i++)
					System.out.println(target.getSku().get(i)+"  "+target.getShopCode());	
				}
			return targetShopList;
		}
	
	/**
	 * �����shoplist����Ҫ��ֵ��ŵ�sku
	 * @param shopList
	 * @return List<TargetShop>
	 */
	public static List<TargetShop> finalExam(List<Map<String,Object>> shopList){	
		initMatrix(shopList);		
		return selectShop(matrix,ReposRow,ReposColumn);
	}
	
	// TODO ����ɸѡǰ�Ծ�������,�޸ĺ�������
	public static boolean modify(List<TargetShop> ValidatedTargetShop,List<String> missedSku) {
		
			for(TargetShop targetShop:ValidatedTargetShop){
				if(targetShop.getOrderId()==null){
				
					for(int i= 0;i<shopArray.length;i++)
					{
						if(shopArray[i].equals(targetShop.getShopCode()))
						{
							for(int j=0;j<skuArray.length;j++)	
								for(int k =0;k<missedSku.size();k++)
									if(skuArray[j].equals(missedSku.get(k)) )
									{
										matrix[j][i] = 0;
										if(shopArray[i].equals("Repos"))
										{
											ReposRow[j] = 0;
											ReposState = true;
										}		
									}
							break;
						}
					}
					//TODO ������
					for(int i=0;i<shopArray.length;i++) {
							System.out.print("\t"+shopArray[i]);
						}
						System.out.println("");
						for(int i=0;i<skuArray.length;i++) {
							System.out.print(skuArray[i]+"\t");
							for(int j=0;j<shopArray.length;j++) {
								System.out.print("["+matrix[i][j]+"]\t");
							}
							System.out.println("");
						}
						
						
					
					rowState =true;
					return false;
				}
			}
			rowState = false;
			return true;
		}
	
	public static void main(String[] args) {
		List<Map<String,Object>> shopList = new ArrayList<Map<String,Object>>();
		Map<String,Object> tempMap = new HashMap<String,Object>();
		
		tempMap.put("sku","sku1");                                                                      
		tempMap.put("shopcode","Repos");
		shopList.add(tempMap); 
		
		tempMap = new HashMap<String,Object>();
		tempMap.put("sku","sku2");                                                                      
		tempMap.put("shopcode","Repos");
		shopList.add(tempMap); 
				
		tempMap = new HashMap<String,Object>();
		tempMap.put("sku","sku1");                                                                        
		tempMap.put("shopcode","s1");
		shopList.add(tempMap);
		
		tempMap = new HashMap<String,Object>();
		tempMap.put("sku","sku2");                                                                        
		tempMap.put("shopcode","s1");
		shopList.add(tempMap);
		
		tempMap = new HashMap<String,Object>();
		tempMap.put("sku","sku3");                                                                        
		tempMap.put("shopcode","s3");
		shopList.add(tempMap); 
	
		long before = System.currentTimeMillis();
		
		List<TargetShop> ValidatedShopList = finalExam(shopList);
		
		for(int i = 0;i<ValidatedShopList.size();i++)
		{
			if(i==0) ValidatedShopList.get(i).setOrderId(null);
			else ValidatedShopList.get(i).setOrderId("exist");
		}
		
			
			List<String> sku = new ArrayList<String>();
			//��Ӧ�ŵ겻ƥ���sku
			sku.add("sku2");
//			sku.add("sku1");
			modify(ValidatedShopList,sku);
			ValidatedShopList = selectShop(matrix, ReposRow, ReposColumn);
		
				
		long after = System.currentTimeMillis();
		System.out.println("------------------------");
		System.out.println("time="+(after-before)+"millis");
		
	}
	

}
