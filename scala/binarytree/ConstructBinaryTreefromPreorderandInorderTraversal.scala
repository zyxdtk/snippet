/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
 // 532 ms
object Solution {
    def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
        if (inorder.size == preorder.size && inorder.size > 0 ) {
            buildTree(preorder, inorder, 0, 0, inorder.size)
        } else {
            null
        }
    } 
    
    def buildTree(preorder: Array[Int], inorder: Array[Int], prefrom: Int, infrom: Int, size: Int): TreeNode = {
        if (size > 0) {
            val root = preorder(prefrom)
            val result = TreeNode(root)
            val index = findRootIndex(inorder, infrom, size, root)
            if (index > 0) {
               result.left = buildTree(preorder, inorder, prefrom+1, infrom, index)  
            }
            if (index >= 0 && index+1 < size) {
                result.right = buildTree(preorder, inorder, prefrom+1+index, infrom + index + 1, size - index -1)
            }
            result
        } else {
            null
        }
    } 
 
    def findRootIndex(inorder: Array[Int], infrom: Int, size: Int, root: Int): Int = {
        var index = 0
        var isFound = false
        while (!isFound && index < size) {
            if (inorder(index + infrom) == root ) {
                isFound = true
            } else {
                index += 1    
            }
        }
        if (isFound) index else -1
    }
}
