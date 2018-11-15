/**
 * 
 */
package princeton;

/**
 * @author Lee
 *	引用http://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html
 *  定义了一个定向带权值边的类
 */ 
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;
	
	
	/**
	 * @param v 向量的尾
	 * @param w 向量的头
	 * @param weight 向量的权重
	 */
	public DirectedEdge(int v, int w, double weight) {
		if(v < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
		if(w < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
		if(Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public int from() {
		return v;
	}
	public int to() {
		return w;
	}
	public double weight() {
		return weight;
	}
	@Override
	public String toString() {
		return "DirectedEdge [" + v + "->" + w + " " + String.format("%5.2f", weight) + "]";
	}
	
	public static void main(String[] args){
		DirectedEdge e = new DirectedEdge(12,23,3.14);
		System.out.println(e);
	}
}
