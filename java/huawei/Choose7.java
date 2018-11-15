/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class Choose7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		System.out.print(getChoose7(N));
	}
	
	public static int getChoose7(int n){
		int count = 0;
		for(int i=1; i<=n; i++){
			if(i%7 == 0 || isContain7(i)){
				count++;
			}
		}
		return count;
	}
	
	public static boolean isContain7(int n){
		n -= n/10000*10000;
		if(n/1000 == 7)return true;
		n -= n/1000*1000;
		if(n/100 == 7)return true;
		n -= n/100*100;
		if(n/10 == 7)return true;
		n -= n/10*10;
		if(n==7)return true;
		return false;
	}
}
