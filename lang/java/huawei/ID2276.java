/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2276 {

	/**
	 * @param args
	 * ID2276 坐标移动
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s =new Scanner(System.in);
		String line = s.nextLine();
		parse(line);
	}
	
	public static void parse(String line){
		String[] args = line.split(";");
		int[] point = new int[2];
		point[0]=point[1]=0;
		for(int i=0; i<args.length; i++){
			if(args[i].matches("^[ASWD][0-9]{1,2}$")){
				point = move(point,args[i]);
			}	
		}
		print(point);
	}
	public static int[] move(int[] point, String arg){
		char direction = arg.charAt(0);
		int distance = Integer.parseInt(arg.substring(1));
		switch(direction){
			case 'A':point[0] -= distance;break;
			case 'S':point[1] -= distance;break;
			case 'W':point[1] += distance;break;
			case 'D':point[0] += distance;break;
			default :break;
		}
		return point;
	}
	public static void print(int[] point){
		System.out.println(point[0]+","+point[1]);
	}
}
