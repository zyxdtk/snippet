package leetCode;

/**
 * Created by liyong on 16/5/12.
 */
public class PerfectSquares {

  public int numSquares(int n) {
    int[] cache = new int[n+1];
    int squareRoot = getSquareRoot(n);
    return numSquares(n, squareRoot, cache);
  }

  public int numSquares(int n, int squareRoot, int[] cache) {
    if (n <= 0) {
      return 0;
    } else if (n <= 3) {
      return n;
    } else {
      if (cache[n] > 0) {
        return cache[n];
      }
      int result = n;

      for (int maxRoot = squareRoot; maxRoot * maxRoot * result > n ; maxRoot--) {
        if (maxRoot * maxRoot > n) {
          continue;
        } else {
          int num = 1 + numSquares(n - maxRoot * maxRoot, maxRoot, cache);
          result = (result <= num) ? result : num;
        }
      }
      cache[n] = result;
      return result;
    }
  }

  public int getSquareRoot(int n) {
    int low = 0;
    int high = n;
    while (low + 1 < high ) {
      int middle = (low + high)/2;
      if (middle * middle <= n) {
        low = middle;
      } else {
        high = middle;
      }
    }
    return low;
  }

  public static void main(String[] arg) {
    PerfectSquares perfectSquares = new PerfectSquares();
    System.out.println(perfectSquares.numSquares(7168));
    System.out.println(perfectSquares.getSquareRoot(7168));
  }
}