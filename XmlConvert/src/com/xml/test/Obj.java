/**
 * 
 */
package com.xml.test;

import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
public class Obj<T, D extends Object> {

	//private Map<String, T>[] map;
	List<T> t;
	private Object[] o;
	private T[] tt;
	private Map<? extends Object, ? extends Object> l;
	private Map<? extends Object, T> n;
	public List<T> getT() {
		return t;
	}
	public void setT(List<T> t) {
		this.t = t;
	}
	public Map<? extends Object, ? extends Object> getL() {
		return l;
	}
	public void setL(Map<? extends Object, ? extends Object> l) {
		this.l = l;
	}
	public Map<? extends Object, T> getN() {
		return n;
	}
	public void setN(Map<? extends Object, T> n) {
		this.n = n;
	}
	private D d;
    private T c;
    private int i;
	public Object[] getO() {
		return o;
	}
	public void setO(Object[] o) {
		this.o = o;
	}
	public T[] getTt() {
		return tt;
	}
	public void setTt(T[] tt) {
		this.tt = tt;
	}
	public D getD() {
		return d;
	}
	public void setD(D d) {
		this.d = d;
	}
	public T getC() {
        return c;
    }
    public void setC(T c) {
        this.c = c;
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    
}
