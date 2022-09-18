/**
 * 
 */
package princeton;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lee
 *  引用http://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html
 *  定义了一个定向带权值有向图
 */
public class EdgeWeightedDigraph {
	private final int V;//节点个数
	private int E;//边的个数
	private Bag<DirectedEdge>[] adj;//数组，每个元素是对应节点的边的列表
	
	//初始化有向图，指定顶点个数
	public EdgeWeightedDigraph(int V) {
		if(V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int index=0; index<V; index++){
			adj[index] = new Bag<DirectedEdge>();
		}
	}
	
	//初始化有向图，指定顶点个数和边的个数
	public EdgeWeightedDigraph(int V, int E){
		this(V);
		if(E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
		for(int i = 0; i < E; i++){
			int v = (int) (Math.random() * V);
			int w = (int) (Math.random() * V);
			double weight = Math.round(100 * Math.random()) / 100.0;
			DirectedEdge e = new DirectedEdge(v, w, weight);
			addEdge(e);
		}
	}
	
	//按照输入，初始化有向图
	public EdgeWeightedDigraph(Scanner s){
		this(s.nextInt());
		int E = s.nextInt();
		if ( E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++){
			int v = s.nextInt();
			int w = s.nextInt();
			if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
			if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
			double weight = s.nextDouble();
			addEdge(new DirectedEdge(v, w, weight));
		}
	}
	
	//复制
	public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
		this(G.V());
		this.E = G.E();
		for (int index = 0; index < G.V(); index++){
			Stack<DirectedEdge> reverse =  new Stack<DirectedEdge>();
			for(DirectedEdge e : G.adj[index]){
				reverse.push(e);
			}
			for(DirectedEdge e : reverse){
				adj[index].add(e);
			}
		}
	}

	
	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	//添加边
	public void addEdge(DirectedEdge e){
		int v = e.from();
		adj[v].add(e);
		E++;
	}

	//返回指定节点的所有边
	public Iterable<DirectedEdge> adj(int v){
		if( v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
		return adj[v];
	}
	
	//返回所有边
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> list = new Bag<DirectedEdge>();
		for(int index = 0; index < V; index++){
			for(DirectedEdge e:adj(index)){
				list.add(e);
			}
		}
		return list;
	}
	
	//指定节点的边的个数，出度
	public int outdegree(int v){
		if( v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
		return adj[v].size();
	}
	
	
	@Override
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append(V+" "+E+NEWLINE);
		for(int index = 0; index < V; index++){
			sb.append(index + ": ");
			for(DirectedEdge e : adj[index]){
				sb.append(e + " ");
			}
			sb.append(NEWLINE);
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(scanner);
		System.out.println(G);

	}

}
