/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee 
 * 			计算最少出列多少位同学，使得剩下的同学排成合唱队形 说明：
 *         N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
 *         合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，
 *         则他们的身高满足存在i（1<=i<=K）使得Ti<T2<......<Ti-1<Ti>Ti+1>......>TK。
 *         你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 *         
 *         参照http://www.myexception.cn/program/1694114.html
 */
public class Chorus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		int[] heights = new int[num];
		int[] l2r = new int[num];
		int[] r2l = new int[num];
		for(int i=0;i<num;i++){
			heights[i] = s.nextInt();//存放身高
		}
		for(int i=0; i<num; i++){//从左向右遍历
			l2r[i] = 1;//至少有一个人符合条件，就是他自己。所以赋初值1.
			for(int j=i-1; j>=0; j--){
				if(heights[i]>heights[j] && l2r[i]<l2r[j]+1){
					l2r[i] = l2r[j]+1;
				}
			}
		}	
		for(int i=num-1; i>=0; i--){
			r2l[i] = 1;//至少有一个人符合条件，就是他自己。所以赋初值1.	
			for(int j=i+1; j<num; j++){
				if(heights[i]>heights[j] && r2l[i]<r2l[j]+1){
					r2l[i] = r2l[j]+1;
				}
			}
		}
		int total = r2l[0]+l2r[0];
		for(int i=1; i<num; i++){
			if(total < r2l[i]+l2r[i]){
				total = r2l[i]+l2r[i];
			}
		}
		System.out.println(num-total+1);
	}
}
