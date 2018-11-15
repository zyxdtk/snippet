/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee 
 * 题目标题： 计算两个字符串的最大公共字串的长度，字符不区分大小写 
 * 详细描述： 接口说明 
 * 原型： int getCommonStrLength(char * pFirstStr, char * pSecondStr);
 *  输入参数： char * pFirstStr //第一个字符串 char * pSecondStr//第二个字符串
 */
public class CommonSubString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String str1 = s.next();
		String str2 = s.next();
		int len = getCommonStrLength(str1, str2);
		System.out.println(len);
	}
	/*
	 * 从较短的字符串中选择出所有可能的字串，然后看是否也是较长字符串的字串。遍历顺序从最长字串开始遍历。
	 */
	public static int getCommonStrLength(String str1,String str2){
		String longStr = (str1.length()>str2.length())?str1.toLowerCase():str2.toLowerCase();
		String shortStr = (str1.length()>str2.length())?str2.toLowerCase():str1.toLowerCase();
		int shortLen = shortStr.length();
		for(int i=shortLen; i>0; i--){
			for(int j=0;j<=shortLen-i;j++){
				String subStr = shortStr.substring(j, j+i);
				for(int k=0; k<=longStr.length()-i;k++){
					if(longStr.substring(k, k+i).equals(subStr)){
						return i;
					}
				}
			}
		}
		return 0;
	}
}
