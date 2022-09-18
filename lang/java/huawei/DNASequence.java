/**
 * 
 */
package huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lee
 *         一个DNA序列由A/C/G/T四个字母的排列组合组成。G和C的比例（定义为GC-Ratio）是序列中G和C两个字母的总的出现次数除以总的字母数目
 *         （也就是序列长度）。在基因工程中，这个比例非常重要。因为高的GC-Ratio可能是基因的起始点。
 *         给定一个很长的DNA序列，以及要求的最小子序列长度，研究人员经常会需要在其中找出GC-Ratio最高的子序列。
 *
 */
public class DNASequence {

	/**
	 * @param args
	 *            样例输入: AACTGTGCACGACCTGA 
	 *            5
	 *            样例输出: GCACG
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String dnaSeq = s.nextLine().toUpperCase();
		int num = s.nextInt();
		String outStr = "";
		if (num > 0 && dnaSeq.length() > num) {
			outStr = highestSeq(dnaSeq, num);
		}
		System.out.print(outStr);
	}

	/*
	 * 计算GC比例最高的字串
	 */
	public static String highestSeq(String seq, int num) {
		double highestGCRatio = 0;
		String outStr = "";
		for (int i = 0, len = seq.length() - num; i <= len; i++) {
			String subSeq = seq.substring(i, i + num);
			double tempGCRatio = gcRatio(subSeq);
			if (highestGCRatio < tempGCRatio) {
				highestGCRatio = tempGCRatio;
				outStr = subSeq;
			}
		}
		return outStr;
	}

	/*
	 * 计算序列的GCRatio
	 */
	public static double gcRatio(String subSeq) {
		int len = subSeq.length();
		double count = 0;
		Pattern p = Pattern.compile("[GC]");
		Matcher m = p.matcher(subSeq);
		while (m.find()) {
			count++;
		}
		return count / len;
	}
}
