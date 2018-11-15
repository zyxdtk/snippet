/**
 * 
 */
package huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lee
 * ID2066 四则运算
 * 引用http://justsee.iteye.com/blog/1125174
 */
public class ID2066 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		line = line.replaceAll("([^0-9])(-[0-9]+)", "$1"+"(0"+"$2"+")");
		ID2066 id = new ID2066();
		Operate operate = id.new Operate();
		System.out.println(operate.calculate(line));
	}

   class Operate{
		/**   
		 * 利用栈，进行四则运算的类   
		 * 用两个栈来实现算符优先，一个栈用来保存需要计算的数据numStack,一个用来保存计算优先符priStack   
		 *    
		 * 基本算法实现思路为：用当前取得的运算符与priStack栈顶运算符比较优先级：若高于，则因为会先运算，放入栈顶；   
		 * 若等于，因为出现在后面，所以会后计算，所以栈顶元素出栈，取出操作数运算；   
		 *  若小于，则同理，取出栈顶元素运算，将结果入操作数栈。各个优先级'(' > '*' = '/' > '+' = '-' > ')'   
		 *    
		 */ 
		private Stack<Character> priStack = new Stack<Character>();//操作符栈
		private Stack<Integer> numStack = new Stack<Integer>();//操作数栈
		boolean debug =false;
		/** 功能：四则运算
	     * 输入：strExpression：字符串格式的算术表达式，如: "3+2*{1+2*[-4/(8-6)+7]}"
	     * 返回：算术表达式的计算结果
	     * 约束：
	    * pucExpression字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。
	    * pucExpression算术表达式的有效性由调用者保证; 
	     */
		int calculate(String strExpression){
			StringBuffer tempNum = new StringBuffer();// 用来临时存放数字字符串(当为多位数时)   
			StringBuffer str = new StringBuffer().append(strExpression);// 用来保存，提高效率
			int i=0,len = str.length();
			char tempChar;// 用来临时存放读取的字符      
			while(i<len){
				tempChar = str.charAt(i);
				if(debug)System.out.println("i"+i+" "+tempChar);
				// 判断temp，当temp为操作符时      
				if(!isNum(tempChar)){
					// 1.此时的tempNum内即为需要操作的数，取出数，压栈，并且清空tempNum
					if(!"".equals(tempNum.toString())){
						int num = Integer.parseInt(tempNum.toString());
						numStack.push(num);
						tempNum.delete(0, tempNum.length());
					}
					/**
					 * 用当前取得的运算符与栈顶运算符比较优先级：
					 * 若高于，则因为会先运算，放入栈顶；若等于，因为出现在后面，所以会后计算，所以栈顶元素出栈，取出操作数运算；
					 * 若小于，则同理，取出栈顶元素运算，将结果入操作数栈。
					 * 判断当前运算符与栈顶元素优先级，取出元素，进行计算(因为优先级可能小于栈顶元素，还小于第二个元素等等，需要用循环判断)
					 */
					while(!priStack.empty() && !compare(tempChar)){
						calc();
						if (tempChar == ')' || tempChar==']' || tempChar=='}') {// 当栈顶为'('，而当前元素为')'时，则是括号内以算完，去掉括号   
							char last = priStack.pop();
							if(last == '(' || last=='[' || last=='{'){
								if(!priStack.empty()){
									tempChar = priStack.pop();
									if(debug)System.out.println("[]"+tempChar);
								}else{
									return numStack.pop();
								}
							}else{
								priStack.push(last);
							}
							if(debug)System.out.println("last--"+last);
		                }
						
					}
					// 判断当前运算符与栈顶元素优先级， 如果高，或者低于平，计算完后，将当前操作符号，放入操作符栈      
					priStack.push(tempChar); 
   
				}else{
					tempNum = tempNum.append(tempChar);// 将读到的这一位数接到以读出的数后(当不是个位数的时候)      
				}
				i++;
			}
			// 1.此时的tempNum内即为需要操作的数，取出数，压栈，并且清空tempNum
			if(!"".equals(tempNum.toString())){
				int num = Integer.parseInt(tempNum.toString());
				numStack.push(num);
				tempNum.delete(0, tempNum.length());
			}
			while(!priStack.isEmpty()){
				calc();
			}
			return numStack.pop();
		}
		
		boolean isNum(char c){
			return (c>='0'&&c<='9');
		}
		
		/**   
	     * 比较当前操作符与栈顶元素操作符优先级，如果比栈顶元素优先级高，则返回true，否则返回false   
	     *    
	     * @param str 需要进行比较的字符   
	     * @return 比较结果 true代表比栈顶元素优先级高，false代表比栈顶元素优先级低   
	     */      
	    private boolean compare(char c) {       
		   char last = (char) priStack.lastElement();    
		   int pri1 = getPriority(c);
		   int pri2 = getPriority(last);
		   if(debug)System.out.println("pri:"+pri1+' '+pri2);
		   if(pri2 == 3)return true;
		   if(pri2>=pri1){
			   return false;
		   }else{
			   return true;
		   }
	    }  
	    private int getPriority(char c){
	    	if(c=='+' || c=='-')return 1;
	    	if(c=='*' || c=='/')return 2;
	    	if(c=='(' || c=='[' || c=='{')return 3;
	    	if(c==')' || c==']' || c=='}')return 0;
	    	return -1;
	    }
	    private void calc(){//取出堆栈中的数，计算，结果放入numStack中
	    	int a = (int)numStack.pop();//第二个运算数
	    	if(debug)System.out.println(a);
			int b = (int)numStack.pop();//第一个运算数
			if(debug)System.out.println(b);
			char ope = (char)priStack.pop();
			int result = 0;
			switch(ope){
				case '+':
					result = b + a;
					numStack.push(result);// 将操作结果放入操作数栈      
					break;
				case '-':
					result = b - a;
					numStack.push(result);// 将操作结果放入操作数栈      
					break;
				case '*':
					result = b * a;
					numStack.push(result);// 将操作结果放入操作数栈      
					break;
				case '/':
					result = b / a;
					numStack.push(result);// 将操作结果放入操作数栈      
					break;
			}
			if(debug)System.out.println("result"+result);
	    }
	}//end calss Operate
}//end class ID
