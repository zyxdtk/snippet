/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 * ID2168 字符串排序
 * 引用插入排序http://zhdkn.iteye.com/blog/1136253
 */
public class ID2168 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		System.out.println(sort(str));
	}

	public static String sort(String str){
		StringBuffer sb = new StringBuffer().append(str);
		int len = sb.length();
		int j;			//当前值的位置  
		int i;			//指向j前的位置  
		char key; 		//当前要进行插入排序的值  
		 //从数组的第二个位置开始遍历值  
		for(j=1; j<len; j++){
			key = sb.charAt(j);
			if(key<'A' || (key>'Z' && key<'a') || key>'z')continue;
			i = j -1;
			int last=j;
			int cur = getNext(sb, last);
			if(cur == last)continue;
			while(cur>=0 && toUpcase(sb.charAt(cur))>toUpcase(key)){
				sb.setCharAt(last, sb.charAt(cur));
				last = cur;
				cur = getNext(sb, last);
				if(cur == last)break;
			}
			sb.setCharAt(last, key);
		}
		return sb.toString();
	}
	public static int getNext(StringBuffer sb, int cur){//获取下一个字母
		char curChar ;
		do{
			if(cur > 0){
				cur--;
			}else{
				return cur;
			}
			curChar = sb.charAt(cur);
		}while(curChar<'A' || (curChar>'Z' && curChar<'a') || curChar>'z');
		return cur;
	}
	public static char toUpcase(char c){
		if(c<='z' && c>='a'){
			return (char)(c-32);
		}else{
			return c;
		}
	}
}
