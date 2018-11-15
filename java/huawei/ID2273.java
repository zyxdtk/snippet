/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2273 {

	/**
	 * @param args
	 * ID2273 密码验证合格程序   
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine()){
			System.out.println(parse(s.nextLine()));
		}
	}
	public static String parse(String str){
		if(str.length()<=8)return "NG";//1.长度超过8位
		//2.包括大小写字母.数字.其它符号,以上四种至少三种
		int count = 0;
		if(str.matches(".*[A-Z]+.*"))count++;
		if(str.matches(".*[a-z]+.*"))count++;
		if(str.matches(".*[0-9]+.*"))count++;
		if(str.matches(".*\\W.*")){
			//System.out.println("W");
			count++;
		}
		//System.out.println(count);
		if(count<3)return "NG";
		//3.不能有相同长度超2的子串重复
		if(isRepeat(str)){
			return "NG";
		}
		
		return "OK";
	}
	public static boolean isRepeat(String str){
		//System.out.println(str);
		for(int l=3; l<str.length();l++){
			for(int i=0; i<=str.length()-l; i++){
				String sub = str.substring(i, i+l);
				for(int j=i+1; j<=str.length()-l; j++){
					if(str.substring(j,j+l).equals(sub)){
						return true;//有重复
					}
				}
			}
		}
		return false;
	}
}
