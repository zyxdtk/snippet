/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2272 {

	/**
	 * @param args
	 * ID2272 简单密码破解
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s =new Scanner(System.in);
		String line = s.nextLine();
		System.out.println(encode(line));
	}
	public static String encode(String line){
		StringBuffer sb = new StringBuffer(line);
		for(int i=0; i<sb.length(); i++){
			if(sb.charAt(i)>='a' && sb.charAt(i)<='z')sb.setCharAt(i, a2n(sb.charAt(i)));
			if(sb.charAt(i)>='A' && sb.charAt(i)<='Z')sb.setCharAt(i, A2a(sb.charAt(i)));
		}
		line = sb.toString();
		return line;
	}
	public static char a2n(char c){
		if(c>='a' && c<='c')return '5';
		if(c>='d' && c<='f')return '3';
		if(c>='g' && c<='i')return '4';
		if(c>='j' && c<='l')return '5';
		if(c>='m' && c<='o')return '6';
		if(c>='p' && c<='s')return '7';
		if(c>='t' && c<='v')return '8';
		if(c>='w' && c<='z')return '9';
		return '0';
	}
	public static char A2a(char c){
		c += 33;
		if(c>'z')c='a';
		return c;
	}

}
