package edu.amrita.cb.en.aie.ds2;

//Binary Search Tree
public class NonEmpty<T extends Comparable> extends ConsSet<T>
{
    T key;
    ConsSet<T> left;
    ConsSet<T> right;
    
    static final ConsSet<Comparable<Object>> Empty = new ConsSet<Comparable<Object>>()
    {

		@Override
		public boolean has(Comparable<Object> k) 
		{
			return false;
		}

		@SuppressWarnings({"unchecked","rawtypes"})
		@Override
		public ConsSet add(Comparable<Object> k) 
		{
			
			return new NonEmpty(this,k,this);
		}

        @Override
        protected Comparable<Object> get(Comparable<Object> k) {
            return null;
        }

        @Override
		public String toString() 
		{
			
			return "";
		}
    	
    };
    public ConsSet<T> getEmpty()
    {
        // Forced Conversion....
    	return (ConsSet<T>) Empty;
    }
    public NonEmpty(T k)
    {
    	key = k;
    	left = getEmpty();
    	right = getEmpty();
    }
    public NonEmpty(ConsSet<T> l,T k,ConsSet<T> r)
    {
    	key = k;
    	left = l;
    	right = r;
    }
	@Override
	public boolean has(T k) 
	{
		if(k.compareTo(key)<0) return left.has(k);
		else if(k.compareTo(key)>0) return right.has(k);
		return true;
	}

	public T get(T k)
    {
		// If the value is less than 0, goes to left node
        if(k.compareTo(key)<0) 
        {
            return left.get(k);
        }
     // If the value is greater than 0, goes to right node
        else if(k.compareTo(key)>0) 
        {
            return right.get(k);
        }

        return key;
    }

	@Override
	public ConsSet<T> add(T k) 
	{
		if(k.compareTo(key)<0) return new NonEmpty<T>(left.add(k),key,right);
		else if(k.compareTo(key)>0) return new NonEmpty<T>(left,key,right.add(k));
		return this;
	}
	@Override
	public String toString() 
	{
		return '('+left.toString() + "," + key + "," + right.toString() + ')'; 
	}
	
	public static void main(String[] args)
	{
		ConsSet s = new NonEmpty(5.2);
		System.out.println(s);
		ConsSet s1 = s.add(3.2);
		System.out.println(s1);
		ConsSet s2 = s1.add(74.5);
		System.out.println(s2);
		ConsSet s3 = s2.add(2.2);
        System.out.println("-------");
		System.out.println(s3);
        System.out.println(s2);

	}

}
