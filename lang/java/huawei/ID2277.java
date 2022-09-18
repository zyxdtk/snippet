/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 * id2277        购物单
 */
public class ID2277 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int m = s.nextInt();
		int[][] v = new int[m][5]; //v,p,q,c. c为所选组合中改商品的个数
		for(int i=0; i<m; i++){
			for(int j=0; j<3; j++){
				v[i][j] = s.nextInt();
			}
			v[i][3] = 0;//已选个数
			v[i][4] = 0;//附件个数
		}
		int[] argv = new int[3];
		argv[0] = 0;argv[1]=0;argv[2]=0;
		System.out.println(perm(v,argv,N));
	}
	/*
	 * v为存储了商品信息的二位数组
	 *  args是一维数组，{遍历深度，购物个数，当前购物金额}
	 */
	public static  int perm(int[][] v,int[] args,int N){//用递归方法，遍历所有可能的购物组合
		int max = 0;
		if(args[0] >= v.length)return getMetric(v);//遍历了所有商品，返回重要度
		int count = 0;
		int p = v[args[0]][2];//查看该商品是不是附件
		if(p == 0){
			count =1;//主件最多只能有一个
		}else{
			if(v[p-1][3] == 0){
				count = 0;//没有主件，就没有附件
			}else{
				count = 2-v[p-1][4];//附件最多可已有2个
			}
		}
		for(int j=0; j<=count; j++){//遍历当前商品买不同个数时的最大中重要度
			args[2] += v[args[0]][0]*j;
			args[1] += j;//购物数量加j
			if(p != 0)v[p-1][4] += j;
			//System.out.println("j"+j+" "+max+" "+args[0]+" "+args[1]+" "+args[2]);
			if(args[2] > N){//金钱不能超过总额N
				
			}else{	
				v[args[0]][3] = j;
				int metric = getMetric(v);//计算当前重要度
				if(max<metric)max=metric;
				
				args[0] ++;//遍历的下一种商品
				metric = perm(v,args,N);
				//System.out.println("metric"+metric+" "+v[0][3]+" "+v[1][3]+" "+v[2][3]+" "+v[3][3]+" "+v[4][3]);
				args[0] --;//还原
				if(max<metric)max=metric;
				//还原args
				v[args[0]][3] = 0;
			}
			if(p != 0)v[p-1][4] -= j;
			args[1] -= j;
			args[2] -= v[args[0]][0]*j;
		}
		return max;
	}
	
	public static int getMetric(int[][] v){//计算重要度
		int metric = 0;
		for(int i=0; i<v.length; i++){
			metric += v[i][0]*v[i][1]*v[i][3];
		}
		return metric;
	}

}
