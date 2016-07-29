/**
 * 
 */
package com.xml.test;

import java.lang.reflect.Type;

/**
 * @author Administrator
 *
 */
public class TypeResovle<T> {
    
    protected TypeResovle() {
		Type superType = getClass().getGenericSuperclass();
		System.out.println(superType);
    }
}