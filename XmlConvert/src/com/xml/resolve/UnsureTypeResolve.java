package com.xml.resolve;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class UnsureTypeResolve extends ValueResolve<Object> {

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(Element element, String fieldName, Type type) {
		if(element.isTextOnly()) 
			return element.getTextTrim();
		                                                                                                                    
		List<Element> innerEles = element.elements();
		Map<String, Object> map = new HashMap<String, Object>();
		for(Element innerEle: innerEles) {
			String k = innerEle.getName();
			Object v = getValue(innerEle, null, type);
			if(map.keySet().contains(k)) {
				Object storedVal = map.get(k);
				if(List.class.isAssignableFrom(storedVal.getClass())) {
					List<Object> list = (List<Object>)storedVal;
					list.add(v);
				} else {
					List<Object> list = new ArrayList<Object>();
					list.add(map.get(k));
					list.add(v);
					map.put(k, list);
				}
			} else 
				map.put(k, v);
		}
		return map;
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: object.toString());
	}
	
	public static final ValueResolve<Object> instance = new UnsureTypeResolve();
}
