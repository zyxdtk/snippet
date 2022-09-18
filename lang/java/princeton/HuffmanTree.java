/**
 * 
 */
package princeton;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * @author Lee
 * 引用：http://128kj.iteye.com/blog/1637940
 */
public class HuffmanTree<T> {

	/**
	 * @param args
	 */
	
	//构造哈夫曼树
	public HuffmanNode<T> createTree(List<HuffmanNode<T>> nodes){
		while(nodes.size() > 1){
			//将w1、w2、…，wn看成是有n 棵树的森林(每棵树仅有一个结点);
			Collections.sort(nodes);
			// 在森林中选出两个根结点的权值最小的树合并，作为一棵新树的左、右子树，且新树的根结点权值为其左、右子树根结点权值之和；
			HuffmanNode<T> left = nodes.get(0);
			HuffmanNode<T> right = nodes.get(1);
			HuffmanNode<T>
			    parent = new HuffmanNode<T>(null,left.getWeight()+right.getWeight());
			parent.setLeft(left);
			parent.setRight(right);
			//从森林中删除选取的两棵树，并将新树加入森林；　
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(nodes.size()-1);
	}
	
	//递归生成Huffman编码
	public void generateHuffmanCode(HuffmanNode<T> root){
		if(root==null)return;
		if(root.getLeft() != null){
			root.getLeft().setCoding(root.getCoding()+"0");
		}
		if(root.getRight() != null){
			root.getRight().setCoding(root.getCoding()+"1");
		}
		generateHuffmanCode(root.getLeft());
		generateHuffmanCode(root.getRight());
	}
	
	//广度优先遍历哈夫曼树
	public List<HuffmanNode<T>> breadthTraversal(HuffmanNode<T> root){
		List<HuffmanNode<T>> list = new ArrayList<HuffmanNode<T>>();
		Queue<HuffmanNode<T>> queue = new ArrayDeque<HuffmanNode<T>>();
		if(root != null){
			queue.offer(root);
		}
		while(!queue.isEmpty()){
			list.add(queue.peek());
			HuffmanNode<T> node = queue.poll();
			if(node.getLeft() != null){
				queue.offer(node.getLeft());
			}
			if(node.getRight() != null){
				queue.offer(node.getRight());
			}
		}
		return list;
	}
}

