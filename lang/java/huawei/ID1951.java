/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID1951 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		System.out.println(reverse(line));
	}

	public static String reverse(String str){
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		while(len-->0){
			sb.append(str.charAt(len));
		}
		return sb.toString();
	}

}
