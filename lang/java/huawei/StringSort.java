/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class StringSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String str = s.next();
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int len = sb.length();
		for(int i=0; i<len; i++){
			for(int j=i+1; j<len; j++){
				if(sb.charAt(i)>sb.charAt(j)){
					char temp = sb.charAt(i);
					sb.setCharAt(i, sb.charAt(j));
					sb.setCharAt(j, temp);
				}
			}
		}
		System.out.print(sb);
	}

}
