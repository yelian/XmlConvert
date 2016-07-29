package com.xml.resolve;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.util.TypeUtil;

public class ListResolve extends ValueResolve<List<?>> {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<?> getValue(Element element, String fieldName, Type type) {
		List<Element> eles = element.elements(fieldName);
		if(eles == null || eles.size() == 0)
			return null;
		Type elementType = TypeUtil.getCollectionType(type);
		ValueResolve<?> resolve = ValueResolveFactory.getInstance(elementType);
		List list = new ArrayList();
		for(Element ele: eles) {
			list.add(resolve.getValue(ele, null, elementType));
		}
		return list;
	}

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		if(object == null) {
			element.addElement(fieldName);
			return;
		}
		List<?> list = (List<?>)object;
		int size = list.size();
		if(size == 0) {
			element.addElement(fieldName);
			return;
		}
		for(int index=0; index<size; index++) {
			Object listObj = list.get(index);
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(listObj.getClass());
			resolve.setElement(element, fieldName, listObj);
		}
	}
	
	public static final ValueResolve<List<?>> instance = new ListResolve();
}
