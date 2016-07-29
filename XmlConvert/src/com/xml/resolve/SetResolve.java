package com.xml.resolve;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.util.TypeUtil;

public class SetResolve extends ValueResolve<Set<?>> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<?> getValue(Element element, String fieldName, Type type) {
		List<Element> eles = element.elements(fieldName);
		if(eles == null || eles.size() == 0)
			return null;
		Type elementType = TypeUtil.getCollectionType(type);
		ValueResolve<?> resolve = ValueResolveFactory.getInstance(elementType);
		Set set = new HashSet();
		for(Element ele: eles) {
			set.add(resolve.getValue(ele, null, elementType));
		}
		return set;
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		if(object == null) {
			element.addElement(fieldName);
			return;
		}
		Set<?> set = (Set<?>)object;
		int size = set.size();
		if(size == 0) {
			element.addElement(fieldName);
			return;
		}
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			Object listObj = it.next();
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(listObj.getClass());
			resolve.setElement(element, fieldName, listObj);
		}
	}
	
	public static final ValueResolve<Set<?>> instance = new SetResolve();
}
