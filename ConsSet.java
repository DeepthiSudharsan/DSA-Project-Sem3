package edu.amrita.cb.en.aie.ds2;

public abstract class ConsSet<T> 
{
	public abstract boolean has(T key);
	public abstract ConsSet<T> add(T key);
	protected abstract T get(T k);
	

}
