/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2048 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println(FindChar(s.nextLine()));
	}
	
	public static char FindChar(String str){
		StringBuffer sb = new StringBuffer(str);
		int len = sb.length();
		int i=0;
		while(i<len){
			char tempChar = sb.charAt(i);
			int j=i+1;
			boolean hassame=false;
			
			while(j<len){
				
				if(tempChar == sb.charAt(j)){
					hassame=true;
					sb.deleteCharAt(j);
					len--;
					j--;
				}
				j++;
			}
			if(hassame){
				sb.deleteCharAt(i);
				i--;
				len--;
			}else{
				return tempChar;
			}
			i++;
		}
		return '.';
	}

}
