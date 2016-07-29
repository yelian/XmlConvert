/**
 * 
 */
package com.xml.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.xml.cache.TypeVariableCache;
import com.xml.exception.XmlConvertException;


/**
 * @author Administrator
 *
 */
public class TypeResovle<T> {
    
    protected TypeResovle() {
		Type superclass = getClass().getGenericSuperclass();
	    if (superclass instanceof Class) {
	      throw new XmlConvertException("type类型参数不能为空");
	    }
	    ParameterizedType parameterized = (ParameterizedType) superclass;
	    TypeVariableCache.loadCache(parameterized.getActualTypeArguments()[0]);
    }
}