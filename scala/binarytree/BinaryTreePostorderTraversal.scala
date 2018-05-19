/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
import scala.collection.mutable._
object Solution {
    def postorderTraversal(root: TreeNode): List[Int] = {
        val trivial = ListBuffer[Int]()
        val st = Stack[TreeNode]()
        if (root != null) {
            st.push(root)
        }
        while (!st.isEmpty) {
            val node = st.pop()
            if (node.right == null && node.left == null) {
                trivial += node.value
            } else {
                st.push(node)
                if (node.right != null) {
                    st.push(node.right)
                    node.right = null
                } 
                if (node.left != null) {
                    st.push(node.left)
                    node.left = null
                }  
            }
        }
        trivial.toList
    }
}
