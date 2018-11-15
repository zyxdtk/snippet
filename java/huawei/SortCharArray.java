/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class SortCharArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		char[] aData = new char[str.length()];
		for(int i=0;i<str.length();i++){
			aData[i] = str.charAt(i);
		}
		
		char[] aResult = sortChars(aData);
		System.out.print(aResult);
	}

	public static char[] sortChars(char[] aData){
		int end1 , start2, len;
		len = aData.length;
		if(len%2 == 0){
			end1 = len/2;
			start2 = end1;
		}else{
			end1 = len/2;
			start2 = end1 + 1;
		}
		for(int i=0;i<end1;i++){
			for(int j=i+1;j<end1;j++){
				if(aData[i]>aData[j]){
					char temp = aData[i];
					aData[i] = aData[j];
					aData[j] = temp;
				}
			}
		}
		
		for(int i=start2;i<len;i++){
			for(int j=i+1;j<len;j++){
				if(aData[i]<aData[j]){
					char temp = aData[i];
					aData[i] = aData[j];
					aData[j] = temp;
				}
			}
		}
		return aData;
	}
}
