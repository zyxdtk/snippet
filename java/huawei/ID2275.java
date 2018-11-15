/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee 
 * ID2275 识别有效的IP地址和掩码并进行分类统计
 */
public class ID2275 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] count = new int[7];
		for(int i=0; i<7; i++){
			count[i] = 0;
		}
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if(line.isEmpty()){
				break;
			}
			String[] temp = line.split("~");
			if(isTrueIP(temp[0]) && isTrueIP(temp[1]) && isTruemask(temp[1])){
				//System.out.println("matches"+temp[0]);
				if(isABCD(temp[0])>=0){
					count[isABCD(temp[0])]++;
				}
				if(isPrivate(temp[0])){
					count[6]++;
				}
			}else{
				count[5]++;
			}			
		}
		for(int i=0; i<6; i++){
			System.out.print(count[i]+" ");
		}
		System.out.println(count[6]);
	}
	
	public static boolean isTrueIP(String str){
		if(str.matches("[0-9]+.[0-9]+.[0-9]+.[0-9]+")){
			//System.out.println("matches"+str);
			String[] s = str.split("\\.");
			if(s.length != 4)return false;
			for(int i=0; i<s.length; i++){
				if(s[i] == "")return false;
				int d = Integer.parseInt(s[i]);
				if(d<0 || d>255){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
		
	}
	public static boolean isTruemask(String str){
		if(str.matches("[0-9]+.[0-9]+.[0-9]+.[0-9]+")){
			//System.out.println("matches"+str);
			String[] s = str.split("\\.");
			if(s.length != 4)return false;
			int flag = 0;
			String binary = "";
			for(int i=0; i<s.length; i++){
				Integer d = Integer.parseInt(s[i],10);
				String eight = to8(Integer.toBinaryString(d));
				binary = binary.concat(eight);				
			}
			//System.out.println("isTruemask"+binary);
			for(int j=0; j<binary.length()-1; j++){
				if(binary.charAt(j) == '0' && binary.charAt(j+1)=='1'){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
		
	}
	public static String to8(String str){
		while(str.length() <8){
			str = "0"+str;
		}
		return str;
	}
	public static int isABCD(String str){
		//System.out.println("isABCD"+str);
		String[] s = str.split("\\.");
		int tempint = Integer.parseInt(s[0]);
		if(tempint>=1 && tempint<127)return 0;
		if(tempint>=128 && tempint<192)return 1;
		if(tempint>= 192 && tempint<224)return 2;
		if(tempint>=224 && tempint<240)return 3;
		if(tempint>=240 && tempint<256)return 4;
		return -1;
	}
	public static boolean isPrivate(String str){
		String[] s = str.split("\\.");
		if(Integer.parseInt(s[0]) == 10)return true;
		if(Integer.parseInt(s[0]) == 172 && Integer.parseInt(s[1])>=16 && Integer.parseInt(s[1]) <32)return true;
		if(Integer.parseInt(s[0]) == 192 && Integer.parseInt(s[1])==168)return true;
		return false;
	}
}
