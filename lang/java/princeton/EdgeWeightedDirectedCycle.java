/**
 * 
 */
package princeton;

import java.util.Stack;

/**
 * @author Lee
 *  引用http://algs4.cs.princeton.edu/44sp/EdgeWeightedDirectedCycle.java.html
 *  检查有向图是否存在环。
 */
public class EdgeWeightedDirectedCycle {
	private boolean[] marked;//标记顶点v
	private DirectedEdge[] edgeTo;//到v的路径的上一跳的边
	private boolean[] onStack;//顶点是否在栈中
	private Stack<DirectedEdge> cycle;//直接环
	
	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++){
			if(!marked[v]) dfs(G, v);
		}
		assert check(G);
	}

	private void dfs(EdgeWeightedDigraph G, int v){
		onStack[v] = true;
		marked[v] = true;
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if(cycle != null)return;//已经找到环，退出
			else if(!marked[w]){//递归寻找新的边
				edgeTo[w] = e;
				dfs(G, w);
			}
			else if(onStack[w]){//直到发现节点w被遍历过，且在栈中，也就说有一个环
				cycle = new Stack<DirectedEdge>();
				while (e.from() != w){
					cycle.push(e);
					e = edgeTo[e.from()];
				}
				cycle.push(e);
			}	
		}
		onStack[v] = false;
	}

	public boolean hasCycle(){
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
	
	private boolean check(EdgeWeightedDigraph G){
		if(hasCycle()){
			//验证环
			DirectedEdge first = null, last = null;
			for (DirectedEdge e : cycle){
				if(first == null) first = e;
				if(last != null){
					if(last.to() != e.from()){
						System.err.printf("cycle edges %ds and %s not incident\n", last,e);
						return false;
					}
				}
				last = e;
			}
			if ( last.to() != first.from()){
				System.err.printf("cycle edges %s and %s not incident\n", last,first);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		 // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++) vertices[i] = i;
        random_shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = Random.uniform(V);
                w = Random.uniform(V);
            } while (v >= w);
            double weight = Math.random();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = (int) (Math.random() * V);
            int w = (int) (Math.random() * V);
            double weight = Math.random();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        System.out.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            System.out.print("Cycle: ");
            for (DirectedEdge e : finder.cycle()) {
                System.out.print(e + " ");
            }
            System.out.println();
        }

        // or give topologial sort
        else {
            System.out.println("No directed cycle");
        }
	}//main
	 */
}
