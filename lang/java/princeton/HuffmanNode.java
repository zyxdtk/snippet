/**
 * 
 */
package princeton;

/**
 * @author Lee
 *  哈夫曼数节点
 */
public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {
	private T data;//编码的数据
	private double weight;//权重
	private String coding = "";//哈夫曼编码
	private HuffmanNode<T> left;//左节点
	private HuffmanNode<T> right;//右节点
	
	public HuffmanNode(T data,double weight){//构造函数
		this.data = data;
		this.weight = weight;
	}
	
	public T getData(){
		return data;
	}
	
	public void setData(T data){
		this.data = data;
	}
	
	public double getWeight(){
		return weight;
	}

	public void setWeight(double weight){
		this.weight = weight;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public HuffmanNode<T> getLeft() {
		return left;
	}

	public void setLeft(HuffmanNode<T> left) {
		this.left = left;
	}

	public HuffmanNode<T> getRight() {
		return right;
	}

	public void setRight(HuffmanNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HuffmanNode [data=" + data + ", weight=" + weight + ", coding="
				+ coding + "]";
	}

	@Override
	public int compareTo(HuffmanNode<T> other) {
		if(this.getWeight() < other.getWeight()){
			return -1;
		}else if(this.getWeight() > other.getWeight()){
			return 1;
		}else{
			return 0;
		}
	}
	
	
}
