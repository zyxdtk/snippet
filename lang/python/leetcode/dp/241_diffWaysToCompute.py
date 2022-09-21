# 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
# 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
# 示例 1：
# 输入：expression = "2-1-1"
# 输出：[0,2]
# 解释：
# ((2-1)-1) = 0 
# (2-(1-1)) = 2

# 来源：力扣（LeetCode）
# 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution:
    @cache
    def diffWaysToCompute(self, expression: str) -> List[int]:
        ans = [] 
        if all(c.isdigit() for c in expression):
            return [int(expression)]
        for i, c in enumerate(expression):
            if not c.isdigit():
                ans += [eval(f'{a}{c}{b}') 
                        for a in self.diffWaysToCompute(expression[:i])
                        for b in self.diffWaysToCompute(expression[i+1:])]
        return ans


# 知识点：eval函数   https://www.runoob.com/python/python-func-eval.html
# 知识点：cache https://docs.python.org/zh-cn/3/library/functools.html