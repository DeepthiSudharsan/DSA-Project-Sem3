package edu.amrita.cb.en.aie.ds2;

public class MatrixGraph implements Graph
{
	int [][]graph; //adjacency matrix
	int V; //no of vertices
	
	public MatrixGraph(int[][] g)
	{
		graph = g;
		V = g.length;
	}
//	@Override
//	public int[] sssp(int src)
//	{
//		return dijkstras(src);
//		//return mst(src);
//	}
	@Override
	public int[] mst(int src)
	{
		//return null;
		int []dist = new int[V];
		int []parent = new int[V];
		boolean []taken = new boolean[V];
		
		for(int i=0;i<V;i++)
		{
			dist[i] = Integer.MAX_VALUE;
			taken[i] = false;
		}
		dist[src] = 0;
		parent[src] = -1;
		
		for(int i=0;i<V-1;i++)
		{
			int u = minimize(dist,taken);
			taken[u] = true;
			
			for(int v=0;v<V;v++)
			{
				if((graph[u][v]!=0)&&(!taken[v]))
				{
					if(dist[v]>graph[u][v])
					{
						dist[v] = graph[u][v];
						parent[v] = u;
					}
				}
			}
		}
		return parent;
		
	}
	public int minimize(int[] cost, boolean[] taken)
	{
		int minIndex = -1, minValue = Integer.MAX_VALUE;
		
		for(int i=0;i<V;i++)
		{
			if((!taken[i]) && (cost[i]<minValue))
			{
				minValue = cost[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	@Override
	public int[] dijkstras(int src)
	{
		int []dist = new int[V];
		boolean []taken = new boolean[V];
		
		for(int i=0;i<V;i++)
		{
			dist[i] = Integer.MAX_VALUE;
			taken[i] = false;
		}
		dist[src] = 0;
		for(int i = 0; i<V-1;i++)
		{
			int u = minimize(dist,taken);
			taken[u] = true;
		
			for(int v = 0; v<V;v++)
			{
				if((graph[u][v]!=0) && (!taken[v]))
				{
					if(dist[v] > (graph[u][v] + dist[u]))
					{
						dist[v] = graph[u][v] + dist[u];
					}
				}
			}
		}
		return dist;
	}
	public static void main(String []args)
	{
		int [][]adMatrix = 	{
				{0,5,0,0,0,4,0},
				{5,0,10,0,0,0,0},
				{0,10,0,7,12,0,0},
				{0,0,7,0,21,0,9},
				{0,0,12,21,0,1,0},
				{4,0,0,0,1,0,11},
				{0,0,0,9,0,11,0}
				
		};
		Graph g = new MatrixGraph(adMatrix);
		long startTime = System.nanoTime();
		int[] parentmst = g.mst(2);
		long endTime = System.nanoTime();
		System.out.println("==================");
		System.out.println("Prims Algorithm");
		System.out.println("Time taken : " + (endTime-startTime) + " nanoseconds");
		System.out.println("------------------");
		for(int i=0;i<parentmst.length;i++)
		{
			System.out.println(i + "->" + parentmst[i]);
		}
		Graph g1 = new MatrixGraph(adMatrix);
		long startTime1 = System.nanoTime();
		int[] parentdij = g1.dijkstras(2);
		long endTime1 = System.nanoTime();
		System.out.println("==================");
		System.out.println("Dijkstras Algorithm");
		System.out.println("Time taken : " + (endTime1-startTime1) + " nanoseconds");
		System.out.println("------------------");
		for(int i=0;i<parentdij.length;i++)
		{
			System.out.println(i + "->" + parentdij[i]);
		}
		System.out.println("==================");
	}
 
}
