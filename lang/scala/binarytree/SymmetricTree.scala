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


// bfs 352 ms
import scala.collection.mutable._
object Solution {
    def isSymmetric(root: TreeNode): Boolean = {
        var result = true;
        if (root != null) {
            val st = Stack[TreeNode]()
            result = visitChild(st, root.left, root.right)
            while (!st.isEmpty && result) {
                val left = st.pop()
                val right = st.pop()
                if (left.value != right.value){
                    result = false;
                } else {
                    result = visitChild(st, left.left, right.right)
                    if (result) {
                        result = visitChild(st, left.right, right.left)
                    }
                }
            }
        }
        result   
    }
    
    def visitChild(st: Stack[TreeNode], left: TreeNode, right: TreeNode): Boolean = {
        var result = true;
        if (isEmpty(left) && isEmpty(right)) {
            result = true
        } else if (isEmpty(left) || isEmpty(right)) {
            result = false
        } else{
            st.push(left)
            st.push(right)
        }
        result
    }
    
    def isEmpty(root: TreeNode): Boolean = {
        root == null
    }
}
