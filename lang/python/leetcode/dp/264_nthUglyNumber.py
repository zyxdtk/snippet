
# 给你一个整数 n ，请你找出并返回第 n 个 丑数 。

# 丑数 就是只包含质因数 2、3 和/或 5 的正整数。

#  

# 示例 1：

# 输入：n = 10
# 输出：12
# 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
# 示例 2：

# 输入：n = 1
# 输出：1
# 解释：1 通常被视为丑数。

# 来源：力扣（LeetCode）
# 链接：https://leetcode.cn/problems/ugly-number-ii
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


# 堆的解法 nlog(n)
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        factors = [2, 3, 5]
        seen = {1}
        heap = [1]

        for i in range(n - 1):
            curr = heapq.heappop(heap)
            for factor in factors:
                if (nxt := curr * factor) not in seen:
                    seen.add(nxt)
                    heapq.heappush(heap, nxt)

        return heapq.heappop(heap)


# 动态规划 n 
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[1] = 1
        p2 = p3 = p5 = 1

        for i in range(2, n + 1):
            num2, num3, num5 = dp[p2] * 2, dp[p3] * 3, dp[p5] * 5
            dp[i] = min(num2, num3, num5)
            if dp[i] == num2:
                p2 += 1
            if dp[i] == num3:
                p3 += 1
            if dp[i] == num5:
                p5 += 1
        
        return dp[n]
