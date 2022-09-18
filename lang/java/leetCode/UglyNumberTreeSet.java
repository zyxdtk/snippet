package leetCode;

import com.google.common.base.Stopwatch;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyong on 16/5/14.
 */
public class UglyNumberTreeSet {


  public int nthUglyNumber(int n) {
    if (1 == n) {
      return 1;
    }
    TreeSet<Integer> treeSetTwo = new TreeSet();
    TreeSet<Integer> treeSetThree = new TreeSet();
    TreeSet<Integer> treeSetFive = new TreeSet();
    treeSetTwo.add(1);
    treeSetThree.add(1);
    treeSetFive.add(1);
    int lastResult = 1;
    for (int index = 2; index <= n; index++) {
      int minTwo = getFirst(treeSetTwo);
      int minThree = getFirst(treeSetThree);
      int minFive = getFirst(treeSetFive);
      int candidateTwo = minTwo * 2;
      int candidateThree = minThree * 3;
      int candidateFive = minFive * 5;
      int result = lastResult;

      if (candidateTwo < candidateThree && candidateTwo < candidateFive) {
        result = candidateTwo;
        treeSetTwo.remove(minTwo);
      } else if (candidateThree < candidateTwo && candidateThree < candidateFive){
        result = candidateThree;
        treeSetThree.remove(minThree);
      } else if (candidateFive < candidateTwo && candidateFive < candidateThree) {
        result = candidateFive;
        treeSetFive.remove(minFive);
      } else if (candidateTwo == candidateThree && candidateTwo < candidateFive) {
        result = candidateTwo;
        treeSetTwo.remove(minTwo);
        treeSetThree.remove(minThree);
      } else if (candidateTwo == candidateFive && candidateTwo < candidateThree) {
        result = candidateTwo;
        treeSetTwo.remove(minTwo);
        treeSetFive.remove(minFive);
      } else if (candidateFive == candidateThree && candidateFive < candidateTwo) {
        result = candidateFive;
        treeSetThree.remove(minThree);
        treeSetFive.remove(minFive);
      } else if (candidateFive == candidateThree && candidateFive == candidateTwo) {
        result = candidateFive;
        treeSetFive.remove(minFive);
        treeSetThree.remove(minThree);
        treeSetTwo.remove(minTwo);
      }
      if (lastResult < result ) {
        treeSetTwo.add(result);
        treeSetThree.add(result);
        treeSetFive.add(result);
        lastResult = result;
      } else {
        index--;
      }
    }
    return lastResult;
  }

  private int getFirst(TreeSet<Integer> treeSet) {
    if(null == treeSet || treeSet.isEmpty()) {
      return Integer.MAX_VALUE;
    } else {
      return treeSet.first();
    }
  }

  public static void main(String[] arg) {
    Stopwatch wholeStopWatch = Stopwatch.createStarted();
    UglyNumberTreeSet uglyNumber = new UglyNumberTreeSet();
    System.out.println(uglyNumber.nthUglyNumber(39));
    wholeStopWatch.stop();
    System.out.println(wholeStopWatch.elapsed(TimeUnit.MICROSECONDS));
  }
}
