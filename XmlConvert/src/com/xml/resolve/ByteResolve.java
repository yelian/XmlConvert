package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class ByteResolve extends ValueResolve<Byte> {

	@Override
	public Byte getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return value==null? null: Byte.parseByte(value);
	}
	
	public static final ValueResolve<Byte> instance = new ByteResolve();

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Byte)object).toString());
	}
}
