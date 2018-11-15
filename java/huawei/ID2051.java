/**
 * 
 */
package huawei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class ID2051 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n= s.nextInt();
		int k= s.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		int i=0;
		while(i<n){
			int tempInt = s.nextInt();
			i++;
			Iterator<Integer> it = list.iterator();
			int count=0;
			while(it.hasNext()){
				int nxt = it.next();
				if(nxt<=tempInt){
					count++;
					continue;
				}else{
					
					break;
				}
			}
			list.add(count, tempInt);
		}
		
		int j=0;
		while(j<k-1){
			System.out.print(list.get(j)+" ");
			j++;
		}
		System.out.println(list.get(k-1));
	}

}
