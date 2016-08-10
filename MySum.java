package MatrixAlgorithm;

import java.util.ArrayList;

public class MySum {
	private ArrayList<Shop> shoplist = new ArrayList();
	private int box =0;
	private int[][] matrix;
	public MySum(int[][] m,int b,ArrayList<Shop> a) {
		// TODO Auto-generated constructor stub
		matrix=m;
		box =b ;
		shoplist =a ;
	}

	//对各列求和
/*	public  int sumAll(){
	int maxNum = 0;
	int maxColum = 0;
	int temp =0;
	for(int i=0;i<shoplist.size();i++)
	{
		
		for(int j=0;j<box;j++)
		{
			temp += matrix[i][j];
		}
		if(temp>maxNum) {maxNum = temp;maxColum=i;}
		temp=0;
		}
	return maxColum;
	}
	*/
	//重新求和
	public int sumColum(int[] line){
		int maxNum = 0;
		int maxColum = 0;
		int temp =0;
		for(int i=0;i<shoplist.size();i++)
		{
			for(int j=0;j<box;j++)
			{
					if(line[j]!=-1) 
					 temp += matrix[i][j];
				
			}
			if(temp>maxNum) {maxNum = temp;maxColum=i;}
			temp=0;
		}
		return maxColum;
	}
}
