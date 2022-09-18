/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *  合法IP
 */
public class ID1993 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		System.out.println(isTrueIP(line));
	}
	public static String isTrueIP(String str){
		if(str.matches("[0-9]+.[0-9]+.[0-9]+.[0-9]+")){
			//System.out.println("matches"+str);
			String[] s = str.split("\\.");
			if(s.length != 4)return "NO";
			for(int i=0; i<s.length; i++){
				if(s[i] == "")return "NO";
				int d = Integer.parseInt(s[i]);
				if(d<0 || d>255){
					return "NO";
				}
			}
		}else{
			return "NO";
		}
		return "YES";	
	}
}

