package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class FloatResolve extends ValueResolve<Float> {

	@Override
	public Float getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return null==value? null: Float.parseFloat(value);
	}

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Float)object).toString());
	}
	
	public static final ValueResolve<Float> instance = new FloatResolve();
}
