/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * ID2060 取石子游戏
 * 引用 http://www.cnblogs.com/jiangjun/archive/2012/10/25/2740194.html
 * 
 */
public class ID2060 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		int b = s.nextInt();
		System.out.println(judge(a,b));
	}
	public static int judge(int a, int b){
		double p = (Math.sqrt((double)5)+1)/(double)(2); 
		int dif = (a>b)?a-b:b-a;
		a = a<b?a:b;
		if(a==(int)(p*dif))return 0;
		else return 1;
	}
}
