# Definition for binary tree with next pointer.
# class TreeLinkNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

# bfs
# 参考 http://www.cnblogs.com/zuoyuan/p/3745369.html
class Solution:
    # @param root, a tree link node
    # @return nothing
    def connect(self, root):
        if root:
            # 遍历当前层，cur是上一层的节点用于完成本次遍历，last是将当前层连接起来，next是选择下一层需要遍历的第一个节点
            cur = root; last = None; next = None
            while cur:
                if cur.left:
                    if last: last.next = cur.left
                    last = cur.left
                    if next == None: next = last
                if cur.right:
                    if last: last.next = cur.right
                    last = cur.right
                    if next == None: next = last
                cur = cur.next
            # 遍历下一层的节点
            self.connect(next)
