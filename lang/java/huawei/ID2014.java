/**
 * 
 */
package huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lee
 * ID2014 小强的暑假作业
 */
public class ID2014 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		ID2014 id = new ID2014();
		int num = s.nextInt();
		Homework hk = id.new Homework(num);
		while(num-->0){
			hk.add(s.nextInt(), s.nextInt());
		}
		hk.setRemainTime(s.nextInt());
		System.out.println(hk.getMaxValue());
	}
	
	class Homework{
		int num;//卷子数
		int remainTime;//剩余时间
		List<Integer> time ;//每张卷子花费时间，
		List<Integer> value;//价值
		Homework(int num){
			this.num = num;
			time = new ArrayList<Integer>();
			value = new ArrayList<Integer>();
		}
		void add(int time, int value){
			this.time.add(time);
			this.value.add(value);
		}
		void setRemainTime(int time){
			this.remainTime = time;
		}
		int getMaxValue(){
			return perm(0,this.remainTime,0);
		}
		private int perm(int i,int remaintime, int value){//i遍历到第i张卷子,remaintime当前剩余时间，value所写的卷子的总价值
			int tempTime;//当前卷子的花费时间
			int tempValue;//当前的价值
			if(i>=num)return value;
			tempTime = this.time.get(i);
			tempValue = this.value.get(i);
			
			if(remaintime >= tempTime){
				i++;
				int choose = perm(i,remaintime-tempTime,value+tempValue);
				int notchoose = perm(i,remaintime,value);
				return (choose>=notchoose)?choose:notchoose;
			}else{
				i++;
				return perm(i,remaintime,value);
			}
		}
	}
}
