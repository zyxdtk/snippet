package huawei;

import java.util.Scanner;

public class Matrix {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int matrixList[][] = new  int[n][2];
		for(int i = 0; i<n;i++){
			matrixList[i][0]=scanner.nextInt();
			matrixList[i][1]=scanner.nextInt();
		}
		while(!scanner.hasNext()){};
		String order = scanner.next();	
		int  stack[][] =new int[n][2]; 
		int top = -1,j = 0;
		int sumCost=0;
		for(int i=0 ; i<order.length();i++){
			if(order.charAt(i) == '('){
				
			}else if(order.charAt(i) == ')'){
				if(top>0){
					sumCost += stack[top-1][0]*stack[top][0]*stack[top][1];
					stack[top-1][1] = stack[top][1];
					top--;
				}
			}else{
				top++;
				j = order.charAt(i)-65;
				stack[top][0] = matrixList[j][0];
				stack[top][1] = matrixList[j][1];
			}
		}
		System.out.print(sumCost);
		scanner.close();
	}
}
