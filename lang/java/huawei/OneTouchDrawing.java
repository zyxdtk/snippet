/**
 * 
 */
package huawei;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class OneTouchDrawing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		String outstr = "true";
		int oddnum = 0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<2*num;i++){
			int point = s.nextInt();
			if(map.containsKey(point)){
				map.put(point, map.get(point)+1);
			}else{
				map.put(point, 1);
			}
		}
		Iterator<Integer> iter = map.values().iterator();
		while(iter.hasNext()){
			int value = iter.next();
			if(value <=1){
				outstr = "false";
				break;
			}else if(value%2 != 0){
				oddnum++;
			}	
		}
		if(outstr.equals("true") && oddnum%2 != 0){
			outstr = "false";
		}
		System.out.println(outstr);
	}

}
