/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2052 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String first = s.nextLine();
		String second = s.nextLine();
		System.out.println(addStr(first,second));
	}
	public static String addStr(String first, String second){//判断正负
		if(first.charAt(0) == '-' && second.charAt(0) == '-'){
			return add(first.substring(1),second.substring(1),true);
		}else if(first.charAt(0) == '-' && second.charAt(0) != '-'){
			return minusStr(first.substring(1),second,true);
		}else if(first.charAt(0) != '-' && second.charAt(0) == '-'){
			return minusStr(first,second.substring(1),false);
		}else if(first.charAt(0) != '-' && second.charAt(0) != '-'){
			return add(first,second,false);
		}
		return "0";
	}
	public static String minusStr(String first, String second, boolean flag){//判断两个数字大小，大的在前面
		int len1 = first.length();
		int len2 = second.length();
		if(len1>len2){
			return minus(first,second,flag);
		}else if(len1==len2){
			if(first.compareTo(second)>0){
				return minus(first,second,true);
			}else if(first.compareTo(second)<0){
				if(flag){
					return minus(second,first,false);
				}else{
					return minus(second,first,true);
				}
			}else{
				return "0";
			}
			
		}else{
			if(flag){
				return minus(second,first,false);
			}else{
				return minus(second,first,true);
			}
		}
	}
	public static String add(String str1, String str2,boolean flag){
		//System.out.println("add"+str1+"+"+str2+flag);
		StringBuffer sb1 = new StringBuffer().append(str1);
		StringBuffer sb2 = new StringBuffer().append(str2);
		int carry = 0;
		StringBuffer sb3 = new StringBuffer();
		while(sb1.length()>0 || sb2.length()>0 || carry>0){
			int len1 = (sb1.length()>=4)?4:sb1.length();
			int len2 = (sb2.length()>=4)?4:sb2.length();
			int num1 = (len1 == 0)?0:Integer.parseInt(sb1.substring(sb1.length()-len1));
			int num2 = (len2 == 0)?0:Integer.parseInt(sb2.substring(sb2.length()-len2));
			int num3 = num1 + num2 + carry;
			carry = num3/10000;num3 = num3%10000;		
			sb3 = new StringBuffer().append(Integer.toString(num3)).append(sb3);
			sb1.delete(sb1.length()-len1, sb1.length());
			sb2.delete(sb2.length()-len2, sb2.length());
		}
		if(flag)sb3 = new StringBuffer().append("-").append(sb3);
		return sb3.toString();
	}
	public static String minus(String str1,String str2, boolean flag){
		//System.out.println("minus"+str1+"-"+str2+flag);
		StringBuffer sb1 = new StringBuffer().append(str1);
		StringBuffer sb2 = new StringBuffer().append(str2);
		int carry = 0;
		StringBuffer sb3 = new StringBuffer();
		while(sb1.length()>0 || sb2.length()>0 || carry>0){
			int len1 = (sb1.length()>=4)?4:sb1.length();
			int len2 = (sb2.length()>=4)?4:sb2.length();
			int num1 = (len1 == 0)?0:Integer.parseInt(sb1.substring(sb1.length()-len1));
			int num2 = (len2 == 0)?0:Integer.parseInt(sb2.substring(sb2.length()-len2));
			int num3 = num1 - num2 - carry;
			if(num3<0){
				carry = 1;
				num3 += 10000 ;
			}else{
				carry = 0;
			}	
			sb3 = new StringBuffer().append(Integer.toString(num3)).append(sb3);
			sb1.delete(sb1.length()-len1, sb1.length());
			sb2.delete(sb2.length()-len2, sb2.length());
		}
		if(flag)sb3 = new StringBuffer().append("-").append(sb3);
		return sb3.toString();
	}
}
