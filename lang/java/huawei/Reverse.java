package huawei;

import java.util.Scanner;

public class Reverse {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String lineScanner = scanner.nextLine();
		String str[] = lineScanner.split(" ");
		for(int i=str.length-1;i>=0;i--){
			System.out.print(str[i]+" ");
		}
		
	}

}
