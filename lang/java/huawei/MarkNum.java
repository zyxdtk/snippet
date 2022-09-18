/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * 1984	表示数字
 * 将一个字符中所有出现的数字前后加上符号“*”，其他字符保持不变
 */
public class MarkNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		String outStr = getMarkNum(line);
		System.out.println(outStr);
	}

	public static String getMarkNum(String str){
		String result = str.replaceAll("([0-9]+)", "*"+"$1"+"*");
		return result;
	}

}
