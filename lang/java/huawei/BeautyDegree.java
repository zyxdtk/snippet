/**
 * 
 */
package huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class BeautyDegree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		String[] names = new String[num];
		for(int i=0; i<num; i++){
			names[i] = s.next();
		}
		for(int i=0; i<num; i++){
			System.out.println(getBestBeautyDegree(names[i]));
		}
	}
	
	public static int getBestBeautyDegree(String name){
		char[] s = name.toLowerCase().toCharArray();
		int[] count = new int[s.length];
		Arrays.sort(s);
		int seq = 0;
		count[seq] = 1;
		for(int i=1; i<s.length; i++){
			if(s[i] != s[i-1]){
				seq++;
				count[seq] = 1;
			}else{
				count[seq] += 1;
			}
		}
		Arrays.sort(count);
		int beautyDegree = 0;
		for(int i=0; i<=seq; i++){
			beautyDegree += (26-i)*count[s.length-i-1];
		}
		return beautyDegree;
	}

}
