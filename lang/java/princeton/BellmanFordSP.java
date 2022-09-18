/**
 * 
 */
package princeton;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Lee
 *	引用http://algs4.cs.princeton.edu/44sp/BellmanFordSP.java.html
 *  定义了BellmanFord最短路径算法
 */
public class BellmanFordSP {
	private double[] distTo;			//数组，存储源节点s到其他节点v的最短距离
	private DirectedEdge[] edgeTo;	//数组，存储源节点s到其他节点v的最短距离的上一跳边
	private boolean[] onQueue;			//数组，节点v是否在队列中
	private Queue<Integer> queue;		//需要释放的节点的队列
	private int cost;						//
	private Iterable<DirectedEdge> cycle;
	
	
	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQueue = new boolean[G.V()];
		for (int index = 0; index < G.V(); index++)//初始化距离为无穷大
			distTo[index] = Double.POSITIVE_INFINITY;
		distTo[s]  = 0.0;//s到自己的距离为0；
		
		//Bellman-Ford 算法
		queue = new ArrayDeque<Integer>();
		queue.offer(s);
		onQueue[s] = true;
		while (!queue.isEmpty() && !hasNegativeCycle()){
			int v = queue.poll();
			onQueue[v] = false;
			relax(G, v);
		}
		assert check(G, s);
	}

	private void relax(EdgeWeightedDigraph G, int v){
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQueue[w]){
					queue.offer(w);
					onQueue[w] = true;
				}
			}
			if(cost++ % G.V() == 0){
				findNegativeCycle();
			}
		}
	}
	
	public boolean hasNegativeCycle(){
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
	}
	
	private void findNegativeCycle(){
		int V =  edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++){
			if(edgeTo[v] != null){
				spt.addEdge(edgeTo[v]);
			}
		}
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
		cycle = finder.cycle();			
	}
	
	public double distTo(int v){
		if(hasNegativeCycle()) throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(hasNegativeCycle()){
			throw new UnsupportedOperationException("Negative cost cycle exists");
		}
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	
	private boolean check(EdgeWeightedDigraph G, int s){
		if(hasNegativeCycle()){
			double weight = 0.0;
			for(DirectedEdge e : negativeCycle()){
				weight += e.weight();
			}
			if(weight >= 0.0){
				System.err.println("error: weight of negative cycle = " + weight);
				return false;
			}
		}
		//没有到源节点的环
		else{
			if(distTo[s] != 0.0 || edgeTo[s] != null){
				System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
				return false;
			}
			for (int v = 0; v < G.V(); v++){
				if(v == s) continue;
				if(edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY){
					System.err.println("distTo[] and edgeTo[] inconsistent");
					return false;
				}
			}
	      // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
         for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (distTo[v] + e.weight() < distTo[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    	}
                	}
         	}

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            	}
        	}
		}
		System.out.println("Satisfies optimality conditions");
		System.out.println();
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
      int s = in.nextInt();
      EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

      BellmanFordSP sp = new BellmanFordSP(G, s);

      // print negative cycle
      if (sp.hasNegativeCycle()) {
    	  for (DirectedEdge e : sp.negativeCycle())
    		  System.out.println(e);
        }

      // print shortest paths
      else{
			for (int v = 0; v < G.V(); v++) {
				if(sp.hasPathTo(v)) {
					System.out.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
			      for (DirectedEdge e : sp.pathTo(v)) {
			      	System.out.print(e + "   ");
			        }
			      System.out.println();
			    }
				else {
					System.out.printf("%d to %d           no path\n", s, v);
				}
			}// end for
      }//end else

	}

}
