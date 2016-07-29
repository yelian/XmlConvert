package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class StringResolve extends ValueResolve<String>{

	@Override
	public String getValue(Element element, String fieldName, Type type) {
		return getElementText(element, fieldName);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: object.toString());
	}
	
	public static final ValueResolve<String> instance = new StringResolve();
}
