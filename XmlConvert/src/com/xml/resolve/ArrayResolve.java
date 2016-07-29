package com.xml.resolve;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.util.ResolveUtil;
import com.xml.util.TypeUtil;

public class ArrayResolve<E> extends ValueResolve<Object> {

	/**
		* <p>获取数据结构值，支持多维数组</p>
		* <ul>如下为二维数组表示形式：
		*   <li>&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltname&gt1&lt/name&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltage&gt1&lt/age&gt</li>
		*   <li>&nbsp;&nbsp;&lt/d&gt</li>
		*   <li>&nbsp;&nbsp;&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltname&gt2&lt/name&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltage&gt2&lt/age&gt</li>
		*   <li>&nbsp;&nbsp;&lt/d&gt</li>
		*   <li>&lt/d&gt</li>
		*   <li>&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltname&gt11&lt/name&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltage&gt11&lt/age&gt</li>
		*   <li>&nbsp;&nbsp;&lt/d&gt</li>
		*   <li>&nbsp;&nbsp;&ltd&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltname&gt22&lt/name&gt</li>
		*   <li>&nbsp;&nbsp;&nbsp;&nbsp;&ltage&gt22&lt/age&gt</li>
		*   <li>&nbsp;&nbsp;&lt/d&gt</li>
		*   <li>&lt/d&gt</li>
		*/
	@Override
	@SuppressWarnings("unchecked")
	public Object getValue(Element element, String fieldName, Type type) {
		List<Element> eles = element.elements(fieldName);
		if(eles == null || eles.size() == 0) 
			return null;
		Type compenontType = TypeUtil.getComonentType(type);
		ValueResolve<E> resolve = (ValueResolve<E>) ValueResolveFactory.getInstance(compenontType);
		List<Object> list = new ArrayList<Object>();
		for(Element ele: eles){
			Object result = null;
			//字段为多维数组情况
			if(ResolveUtil.isLoopStruct(resolve)) 
				result = resolve.getValue(ele, fieldName, compenontType);
			else 
				result = resolve.getValue(ele, null, compenontType);
			list.add(result);
		}
		if(list.size() > 0) {
			Object array = Array.newInstance(list.get(0).getClass(), list.size());
			for (int i = 0; i < list.size(); i++) {
				Array.set(array, i, list.get(i));
			}
			return array;
		}
		return null;
	}

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		if(object == null) {
			element.addElement(fieldName);
			return;
		}
		int length = Array.getLength(object);
		if(length == 0) {
			element.addElement(fieldName);
			return;
		}
		for(int index=0; index<length; index++) {
			Object arrayObj = Array.get(object, index);
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(arrayObj.getClass());
			boolean isLoopStruct = ResolveUtil.isLoopStruct(resolve);
			Element arrayEle = element;
			if(isLoopStruct)
				arrayEle = element.addElement(fieldName);
			resolve.setElement(arrayEle, fieldName, arrayObj);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static final ValueResolve<?> instance = new ArrayResolve();
}
