/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * ID2072 求某正整数重新打乱后最大数与最小数的差值
 */
public class ID2072 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		System.out.println(GetResult(num));
	}
	public static int GetResult(int num){
		int[] temp = new int[4];
		temp[0] = num/1000;
		temp[1] = num%1000/100;
		temp[2] = num%100/10;
		temp[3] = num%10;
		for(int i=0; i<4; i++){
			for(int j=i+1; j<4; j++){
				if(temp[i] < temp[j]){
					int key = temp[i];
					temp[i] = temp[j];
					temp[j] = key;
				}
			}
		}
		int result = temp[0]*1000+temp[1]*100+temp[2]*10+temp[3]-(temp[0]+temp[1]*10+temp[2]*100+temp[3]*1000);
		return result;
	}

}
