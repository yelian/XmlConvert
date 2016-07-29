package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class ShortResolve extends ValueResolve<Short> {

	@Override
	public Short getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return null==value? null: Short.parseShort(value);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Short)object).toString());
	}
	
	public static final ValueResolve<Short> instance = new ShortResolve();
}
