/**
 * 
 */
package huawei;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Lee 
 * 考试题目和要点：
 *         1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万
 *         、亿、元、角、分、零、整等字样填写。（30分） 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如￥
 *         532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。（30分）
 *         3、阿拉伯数字中间有“0”时，中文大写要写“零”字，
 *         阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如￥6007.14，应写成“人民币陆仟零柒元壹角肆分“。
 */
public class RMBSwap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		double num = s.nextDouble();
		String outStr = num2RMB(num);
		System.out.println(outStr);
		s.close();
	}
	
	public static String num2RMB(double num){
		if(num == 0){
			return "人民币零元";
		}
		String str = new DecimalFormat("#.00").format(num);
		str = str.replaceAll("\\.", "");
		char[] digit={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
		String unit="仟佰拾亿仟佰拾万仟佰拾元角分";
		int unitLen = unit.length();
		int strLen = str.length();
		StringBuffer sb = new StringBuffer(unit);
		for(int i=strLen-1;i>=0;i--){
			sb = sb.insert(unitLen-strLen+i, digit[(str.charAt(i)-0x30)]);
		}
		str = sb.substring(unitLen-strLen,unitLen+strLen);
		str = str.replaceAll("零[拾佰仟]", "零").replaceAll("零{2,}", "零").replaceAll("零([亿万元])", "$1").replaceAll("零[角分]", "").replaceAll("壹拾", "拾");
		str = "人民币"+str;
		if(!str.contains("角") && !str.contains("分") && str.contains("元")){
			str += "整";
		}
		if(str.contains("分") && !str.contains("整") && !str.contains("角")){
			str = str.replace("元","元零");
		}
		return str;
	}
}