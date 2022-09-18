/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 * Binary Tree Preorder Traversal
 */
import scala.collection.mutable._

object Solution {
    def preorderTraversal(root: TreeNode): List[Int] = {
        val trivial = ListBuffer[Int]()
        val st = Stack[TreeNode]()
        if (root != null) {
            st.push(root)
        }
        while (!st.isEmpty) {
            val node = st.pop()
            trivial += node.value
            if (node.right != null) {
                st.push(node.right)    
            }
            if (node.left != null) {
                st.push(node.left)
            } 
        }
        trivial.toList
    }
}