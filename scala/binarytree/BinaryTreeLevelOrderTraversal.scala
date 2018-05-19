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
    def levelOrder(root: TreeNode): List[List[Int]] = {
        val trivial = ListBuffer[List[Int]]()
        val st = Queue[TreeNode]()
        var depth = 0;
        if (root != null) {
            st.enqueue(root)
        }
        while (!st.isEmpty) {
            depth = depth + 1
            val size = st.size
            val curLevel = ListBuffer[Int]()  
            for(a <- 1 to size){
                val node = st.dequeue()
                curLevel += node.value
                if (node.left != null) {
                    st.enqueue(node.left)
                }
                if (node.right != null) {
                    st.enqueue(node.right)
                } 
            }
            trivial += curLevel.toList
        }
        trivial.toList
    }
}
