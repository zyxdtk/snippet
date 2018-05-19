/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
// 递归版本 332 ms
object Solution {
    def isSymmetric(root: TreeNode): Boolean = {
        var result = true;
        if (root != null) {
            result = isSymmetric(root.left, root.right)
        }
        result
    }
    
    def isSymmetric(left: TreeNode, right: TreeNode): Boolean = {
        var result = true;
        if (left == null && right == null) {
            result = true
        } else if (left == null || right == null) {
            result = false
        } else if (left.value != right.value){
            result = false;
        } else {
            result = isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
        }
        result
    }
}
