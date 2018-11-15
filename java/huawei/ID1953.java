/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID1953 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println(count(s.nextLine()));
	}

	public static int count(String str){
		StringBuffer sb = new StringBuffer();
		int count=0;
		int len = str.length();
		while(len-->0){
			char temp = str.charAt(len);
			if(temp>=0 && temp<=127){
				int i = sb.length();
				boolean flag = false;
				while(i-->0){
					if(sb.charAt(i) == temp){
						flag = true;
					}
				}
				if(!flag){
					sb.append(temp);
				}
			}
		}
		return sb.length();
	}
}
