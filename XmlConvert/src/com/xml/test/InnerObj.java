/**
 * 
 */
package com.xml.test;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class InnerObj<T> {

    private T t;
    private List<Object> l;
    public T getT() {
        return t;
    }
    public void setT(T t) {
        this.t = t;
    }
    public List<Object> getL() {
        return l;
    }
    public void setL(List<Object> l) {
        this.l = l;
    }
    
}
