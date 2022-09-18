/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID1950 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println(reverse(s.nextLine()));
	}
	
	public static String reverse(String str){
		String[] word = str.split(" ");
		StringBuffer sb = new StringBuffer();
		int len = word.length;
		while(len-->1){
			sb.append(word[len]).append(" ");
		}
		sb.append(word[0]);
		return sb.toString();
	}

}
