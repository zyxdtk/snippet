/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee 
 * 题目：假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 */
public class SmallBall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int h = s.nextInt();
		double len = getJourney(h,5);
		double h5 = getHigh(h,5);
		System.out.println(len);
		System.out.println(h5);
	}

	/**
	 * 统计出第5次落地时，共经过多少米?
	 * 
	 * @param high 球的起始高度 num球跳的次数
	 * @return 球经过的距离
	 */
	public static double getJourney(int high,int num) {
		double jour = -1;
		for(int i=0;i<num;i++){
			jour += Math.pow(0.5, i-1);
		}
		return jour*high;
	}

	/**
	 * 统计出第5次反弹多高?
	 * 
	 * @param high 球的起始高度 num球跳的次数
	 * @return 球的高度
	 */
	public static double getHigh(int high,int num) {
		return high*Math.pow(0.5, num);
	}

}
