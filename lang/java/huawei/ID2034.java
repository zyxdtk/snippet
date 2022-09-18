/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * ID2034  查找两个字符串a,b中的最长公共子串 
 */
public class ID2034 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String str1 = s.nextLine();
		String str2 = s.nextLine();
		System.out.println(getCommonStrLength(str1, str2));
	}
	public static String getCommonStrLength(String str1,String str2){
		String longStr = (str1.length()>str2.length())?str1:str2;
		String shortStr = (str1.length()>str2.length())?str2:str1;
		int shortLen = shortStr.length();
		for(int i=shortLen; i>0; i--){
			for(int j=0;j<=shortLen-i;j++){
				String subStr = shortStr.substring(j, j+i);
				for(int k=0; k<=longStr.length()-i;k++){
					if(longStr.substring(k, k+i).equals(subStr)){
						return subStr;
					}
				}
			}
		}
		return null;
	}
}
