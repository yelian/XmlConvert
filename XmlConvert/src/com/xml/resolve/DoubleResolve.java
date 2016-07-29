package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class DoubleResolve extends ValueResolve<Double>{

	@Override
	public Double getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return null==value? null: Double.valueOf(value);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Double)object).toString());
	}
	
	public static final ValueResolve<Double> instance = new DoubleResolve();
}
