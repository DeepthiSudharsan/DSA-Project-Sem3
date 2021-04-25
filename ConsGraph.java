package edu.amrita.cb.en.aie.ds2;
// Array of conssets
public class ConsGraph implements Graph 
{

    ConsSet graph[] = null;
    int V = 0; // number of vertices initially 

    public ConsGraph(int[][] adMatrix) 
    {
        graph = new ConsSet[adMatrix.length]; //creating an array of conssets
        V = adMatrix.length; //setting the total number of vertices
        // iterating through the adjacency matrix
        for(int i=0;i<adMatrix.length;i++) 
        {
            for(int j=0;j<adMatrix.length;j++) 
            {
            	// If it is not any diagonal elements or if weight isnt zero
                if( i != j  && adMatrix[i][j] != 0 ) 
                {
                	// creating an object of KVPair with the fromnode, tonode and weight
                    KVPair kvp = new KVPair<Integer, Integer, Integer>(i, j, adMatrix[i][j]);
                    if( graph[i] == null ) 
                    {
                        graph[i] = NonEmpty.Empty; //First entry, creating empty consset
                    }
                    graph[i] = graph[i].add(kvp); // adding the kvp object to the set

                }
            }
        }
        System.out.println("==========  Adjacency List using Consset  ==========");
        for(int i=0;i<graph.length; i++)
            System.out.println(graph[i]); //printing the array of conssets

    }
    
 // to get the vertex whose distance/cost is min
    public int minimize(int[] cost, boolean[] taken)
    {
        int minIndex = -1, minValue = Integer.MAX_VALUE;

        // iterating through the V vertices
        for(int i=0;i<V;i++)
        {
        	// If the vertex hasnt been visited and the cost of the vertex is less than the min cost so far
            if((!taken[i]) && (cost[i]<minValue))
            {
            	// update minvalue and minindex
                minValue = cost[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public int[] mst(int src) 
    {
    	    // prims minimum spanning tree
    	    // Initial declaration
            int []dist = new int[V];
            int []parent = new int[V];
            boolean []taken = new boolean[V];

            /* initially all the distances are given Integer.MAX_VALUE and the taken or 
                the visited status is set to false */
            for(int i=0;i<V;i++)
            {
                dist[i] = Integer.MAX_VALUE;
                taken[i] = false;
            }
            // distance of source is 0 and the parent node of source is set to -1
            dist[src] = 0;
            parent[src] = -1;
            // iterating through V-1 vertices
            for(int i=0;i<V-1;i++)
            {
            	// u will store the destination vertex value for which the distance is minimum
                int u = minimize(dist,taken);
                // the taken status of u is set to true as it has been visited
                taken[u] = true;

                for(int v=0;v<V;v++)
                {
                    if((getWeight(u,v)!=0)&&(!taken[v]))
                    {
                        if(dist[v]>getWeight(u,v))
                        {
                            dist[v] = getWeight(u,v);
                            parent[v] = u;
                        }
                    }
                }
            }
            return parent;
    }
    @Override
    public int[] dijkstras(int src) 
    {
    	// dijkstras implementation
    	// Initial declaration
        int []dist = new int[V];
        boolean []taken = new boolean[V];

        /* initially all the distances are given Integer.MAX_VALUE and the taken or 
        the visited status is set to false */
        for(int i=0;i<V;i++)
        {
            dist[i] = Integer.MAX_VALUE;
            taken[i] = false;
        }
        // distance of the source vertex is set to 0
        dist[src] = 0;
        // iterating through V-1 vertices
        for(int i = 0; i<V-1;i++)
        {
        	// u will store the destination vertex value for which the distance is minimum
            int u = minimize(dist,taken);
            // the taken status of u is set to true as it has been visited
            taken[u] = true;
            // iterating through the V vertices
            for(int v = 0; v<V;v++)
            {
            	// If the vertex hasnt been visited and the weight of the vertex is not 0
                if((getWeight(u, v)!=0) && (!taken[v]))
                {
                	// updating the total minimum distance
                    if(dist[v] > (getWeight(u, v) + dist[u]))
                    {
                        dist[v] = getWeight(u, v) + dist[u];
                    }
                }
            }
        }
        return dist;
    }
    
        
    // to retrieve the distance/weight from the KVPair object
    public int getWeight(int u, int v) 
    {
    	/* For a given fromnode u, it will check the tonode that match with the 
    	 tonode that has been passed in from the KVPair object and if the tonode v
    	 for a u exists, return the weight or else return null */
        KVPair pair = (KVPair) graph[u].get(new KVPair(u, v, 0));
        if( pair != null ) 
        {
            return (int) pair.weight;
        } else 
        {
            return 0;
        }
    }

    
    public static void main(String[] args) 
    {

    	// adjacency matrix
        int [][]adMatrix = 	{
				{0,5,0,0,0,4,0},
				{5,0,10,0,0,0,0},
				{0,10,0,7,12,0,0},
				{0,0,7,0,21,0,9},
				{0,0,12,21,0,1,0},
				{4,0,0,0,1,0,11},
				{0,0,0,9,0,11,0}
		};
        Graph g = new ConsGraph(adMatrix);
       
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
        long startTime1 = System.nanoTime();
        int[] parentdij = g.dijkstras(2);
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
