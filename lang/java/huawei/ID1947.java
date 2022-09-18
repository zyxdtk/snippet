/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID1947 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println(count(s.nextInt()));
	}

	public static int count(int n){
		int count=0;

		while(n != 0){
			count++;
			n=n&(n-1);
		}
		return count;
	}
}
