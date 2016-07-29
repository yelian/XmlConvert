package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class LongResolve extends ValueResolve<Long> {

	@Override
	public Long getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return null==value? null: Long.parseLong(value);
	}

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Long)object).toString());
	}
	
	public static final ValueResolve<Long> instance = new LongResolve();
}
