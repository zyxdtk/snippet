/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * 给定一个正整数N代表火车数量，0<N<10，接下来输入火车入站的序列，一共N辆火车，每辆火车以数字1-9编号。要求以字典序排序输出火车出站的序列号。
 * 全排列引用http://bbs.csdn.net/topics/320011373
 */
public class TrainPullin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine()){
			int n = s.nextInt();
			int[] trains = new int[n];
			for(int i=0; i<n; i++){
				trains[i] = s.nextInt();
			}
			perm(trains,0,n-1);
		}
	}
	
	public static void perm(int[] trains, int start, int end){
		if(start == end){//当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可  
			if(printOutBound(trains)){
				for(int i=0; i<trains.length-1; i++){
					System.out.print(trains[i]+" ");
				}
				System.out.println(trains[trains.length-1]);
			}
		}else{//多个字母全排列
			for(int i=start; i<=end; i++){
				int temp = trains[start];
				trains[start] = trains[i];
				trains[i] = temp;
				
				perm(trains,start+1,end);
				
				temp = trains[start];
				trains[start] = trains[i];
				trains[i] = temp;
			}
		}
	}
	
	public static boolean printOutBound(int[] trains){
		for(int i=0; i<trains.length; i++){
			int smallest = 0;
			for(int j=i+1; j<trains.length; j++){
				if(trains[i]>trains[j]){
					if(smallest == 0){
						smallest = trains[j];
						continue;
					}
					if(trains[j]<smallest){
						smallest = trains[j];
					}else{
						return false;
					}
				}
			}
		}
		return true;
	}
}
