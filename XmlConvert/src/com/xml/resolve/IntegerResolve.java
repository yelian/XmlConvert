package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class IntegerResolve extends ValueResolve<Integer>{

//	@Override
//	public String getString(String val) {
//		return val;
//	}
	@Override
	public Integer getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return null==value? null: Integer.parseInt(value);
	}
	
	public static final ValueResolve<Integer> instance = new IntegerResolve();

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Integer)object).toString());
	}
}
