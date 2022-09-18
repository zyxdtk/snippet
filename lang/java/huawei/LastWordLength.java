/**
 * 
 */
package huawei;

import java.util.Scanner;

/**
 * @author Lee
 *
 */
public class LastWordLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		String[] wordList = line.split(" ");
		String lastWord  = wordList[wordList.length-1];
		System.out.println(lastWord.length());
	}
}
