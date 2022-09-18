/**
 * 
 */
package huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class SimpleErrorRecord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		List<Integer> errorRecordNum = new ArrayList<Integer>();
		List<String> errorRecordList=new ArrayList<String>();
		
		while(s.hasNextLine()){
			String line = s.nextLine();
			if(line.isEmpty())break;
			
			String[] strList = line.split("( )+");
			int j=0;
			while(strList[j].length() == 0)j++;
			String[] temp = strList[j].split("\\\\");
			String fileNameRead = temp[temp.length-1];
			String num = strList[j+1];
			int len = fileNameRead.length();
			if(len>16){
				fileNameRead = fileNameRead.substring(len-16, len);
			}
			String fileInfo  = fileNameRead+" "+num;
			int t = findRecord(errorRecordList,fileInfo);
			if(t<8){
				errorRecordNum.set(t, errorRecordNum.get(t)+1);
			}else{
				errorRecordList.add(fileInfo);
				errorRecordNum.add(1);
				while(errorRecordList.size()>8){
					errorRecordList.remove(0);
					errorRecordNum.remove(0);
				}
			}
		}
		printRecord(errorRecordList,errorRecordNum);
	}
	
	public static int findRecord(List recordList, String record){
		for(int i=recordList.size()-1;i>=0;i--){
			if(recordList.get(i).equals(record)){
				return i;
			}
		}
		return 8;
	}

	public static void printRecord(List recordList,List recordNum){
		for(int i=0;i<recordList.size();i++){
			System.out.print(recordList.get(i)+" ");
			System.out.println(recordNum.get(i));
		}
	}
}
