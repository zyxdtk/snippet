/**
 * 
 */
package huawei;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Lee
 * ID2270 删除字符串中出现次数最少的字符  
 */
public class ID2270 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<line.length(); i++){
			if(map.containsKey(line.substring(i, i+1))){
				map.put(line.substring(i, i+1), map.get(line.substring(i, i+1))+1);
			}else{
				map.put(line.substring(i, i+1), 1);
			}
		}
		Collection<Integer> c =  map.values();
		Iterator<Integer> i =  c.iterator();
		int min = c.size();
		while(i.hasNext()){
			int nxt = i.next();
			if(min>nxt)min=nxt;
		}
		Set<String> k = map.keySet();
		Iterator<String> j = k.iterator();
		while(j.hasNext()){
			String nxt = j.next();
			int value = map.get(nxt);
			if(value == min){
				line = line.replaceAll(nxt, "");
			}
		}
		System.out.println(line);
	}

}
