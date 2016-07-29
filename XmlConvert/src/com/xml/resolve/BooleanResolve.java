package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class BooleanResolve extends ValueResolve<Boolean> {

	@Override
	public Boolean getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return value==null? null: Boolean.parseBoolean(value);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Boolean)object).toString());
	}
	
	public static final ValueResolve<Boolean> instance = new BooleanResolve();
}
