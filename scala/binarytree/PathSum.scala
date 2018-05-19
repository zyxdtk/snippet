/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
// dfs
object Solution {
    def hasPathSum(root: TreeNode, sum: Int): Boolean = {
        var result  = false
        if (root != null) {
            if (root.value == sum && root.left == null && root.right == null) {
                result = true
            } else {
                if (root.left != null) {
                    result  = hasPathSum(root.left, sum - root.value)
                }
                if (!result && root.right != null) {
                    result  = hasPathSum(root.right, sum - root.value)
                }
            }
        }
        result 
    }
}
