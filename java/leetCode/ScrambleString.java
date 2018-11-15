package leetCode;

/**
 * Created by liyong on 16/5/16.
 */
public class ScrambleString {

  public boolean isScramble(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    char[] stack = new char[s1.length()];
    int top = 0;

    for (int index1=0,index2=0; index1<s1.length();) {
      char char1 = s1.charAt(index1);
      if (top>0 && char1 == stack[top-1]) {
        index1++;
      } else {
        for (; index2 < s2.length(); ) {
          if (char1 == s2.charAt(index2)) {
            index1++;
            index2++;
            break;
          } else {
            stack[top++] = s2.charAt(index2++);
          }
        }
      }
    }
    if (top > 0) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    ScrambleString scrambleString = new ScrambleString();

//    System.out.println(scrambleString.isScramble("great","rgeat"));
    System.out.println(scrambleString.isScramble("abc","bca"));
    System.out.println(scrambleString.isScramble("abc","cab"));
    System.out.println(scrambleString.isScramble("great","rgtae"));
  }
}
