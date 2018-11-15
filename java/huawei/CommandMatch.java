/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class CommandMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String[][] com = {
				{"reset","","reset what"},
				{"reset","board","board fault"},
				{"board","add","where to add"},
				{"board","delet","no board at all"},
				{"reboot","backplane","impossible"},
				{"backplane","abort","install first"}
		};
		
		while(s.hasNextLine()){
			String line = s.nextLine();
			String[] command = line.split(" +");
			if(command[0].length() == 0)continue;
			if(command.length ==1){
				System.out.println(getResult(com,command[0],""));
			}else{
				System.out.println(getResult(com,command[0],command[1]));
			}	
		}
	
	}
	public static String getResult(String[][] com,String first, String second){
		int count = 0;
		String matchStr = "";
		for(int i=0; i<6; i++){
			if(isMatch(com[i],first,second)){
				count++;
				matchStr = com[i][2];
			}
		}
		if(count == 1){
			return matchStr;
		}else{
			return "unkown command";
		}
	}
	
	public static boolean isMatch(String[] com,String first, String second){
		if(com[0].length() < first.length()  || com[1].length() < second.length()){
			return false;
		}
		if(second.length() == 0){
			if(com[1].length() == 0 && com[0].substring(0, first.length()).equals(first)){
				return true;
			}else{
				return false;
			}				
		}
		
		if(com[0].substring(0, first.length()).equals(first) && 
				com[1].substring(0, second.length()).equals(second)){
			return true;
		}else{
			return false;
		}
	}
}
