package edu.amrita.cb.en.aie.ds2;

import java.util.Comparator;

public class KVPair <V1 extends Comparable<V1>,V2 extends Comparable<V2>,Weight extends Comparable<Weight>> implements Comparable<KVPair> {

    V1 fromnode;
	V2 tonode;
	Weight weight;
	// constructor 
	public KVPair(V1 fromnode, V2 tonode,Weight weight) 
	{
		this.fromnode=fromnode;
		this.tonode=tonode;
		this.weight=weight;
	}

    @Override
    // comparing two KVPair objects 
    public int compareTo(KVPair o) 
    {
    	// first comparing the fromnodes of the two KVPair objects
        int i = fromnode.compareTo((V1) o.fromnode);
        // If they are not the same, return the difference
        if (i != 0) return i;

       // if the from nodes are the same, then compare the tonodes of the two KVPair objects
        i = tonode.compareTo( (V2) o.tonode);
       // If they are not the same, return the difference
        if (i != 0) return i;

        return 0;
    }

    // prints the KVPair objects in a proper formated manner
    @Override
    public String toString() {
        return "KVPair{" +
                "fromnode=" + fromnode +
                ", tonode=" + tonode +
                ", weight=" + weight +
                '}';
    }
}
