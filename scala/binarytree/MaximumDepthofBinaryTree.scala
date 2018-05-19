/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
// 递归版本 428 ms
object Solution {
    def maxDepth(root: TreeNode): Int = {
        if(root == null)  
            return 0;  
        Math.max(maxDepth(root.left), maxDepth(root.right)) + 1        
    }
}


// 复用 Binary Tree Level Order Traversal 代码，bfs 444 ms
import scala.collection.mutable._
object Solution {
    def maxDepth(root: TreeNode): Int = {
        val st = Queue[TreeNode]()
        var depth = 0;
        if (root != null) {
            st.enqueue(root)
        }
        while (!st.isEmpty) {
            depth = depth + 1
            val size = st.size
            for(a <- 1 to size){
                val node = st.dequeue()
                if (node.left != null) {
                    st.enqueue(node.left)
                }
                if (node.right != null) {
                    st.enqueue(node.right)
                } 
            }
        }
        depth    
    }
}
