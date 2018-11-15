package leetCode;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyong on 16/5/14.
 */
public class UglyNumberII {


  public int nthUglyNumber(int n) {
    if (n <= 6) {
      return n;
    }
    int[] numbers = new int[n];
    numbers[0] = 1;
    numbers[1] = 2;
    numbers[2] = 3;
    numbers[3] = 4;
    numbers[4] = 5;
    numbers[5] = 6;
    int index = 6;
    int i2 = 3;
    int i3 = 2;
    int i5 = 1;
    while (index < n) {
      int m2 = numbers[i2] * 2;
      int m3 = numbers[i3] * 3;
      int m5 = numbers[i5] * 5;
      int temp = (m2 <= m3) ? m2 : m3;
      int m = (m5 <= temp) ? m5 : temp;
      numbers[index++] = m;
      if (m == m2) i2++;
      if (m == m3) i3++;
      if (m == m5) i5++;
    }
    return numbers[n-1];
  }


  public static void main(String[] arg) {
    Stopwatch wholeStopWatch = Stopwatch.createStarted();
    UglyNumberII uglyNumber = new UglyNumberII();
    System.out.println(uglyNumber.nthUglyNumber(10000));
    wholeStopWatch.stop();
    System.out.println(wholeStopWatch.elapsed(TimeUnit.MICROSECONDS));
  }
}
