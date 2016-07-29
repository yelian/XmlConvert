package com.xml.resolve;

import java.lang.reflect.Type;
import java.math.BigInteger;

import org.dom4j.Element;

public class BigIntegerResolve extends ValueResolve<BigInteger> {

	@Override
	public BigInteger getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		return value==null? null: new BigInteger(value);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((BigInteger)object).toString());
	}
	
	public static final ValueResolve<BigInteger> instance = new BigIntegerResolve();
}
