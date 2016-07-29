package com.xml.resolve;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import org.dom4j.Element;

public class BigDecimalResolve extends ValueResolve<BigDecimal> {

	@Override
	public BigDecimal getValue(Element element, String fieldName, Type type) {
		String value = this.getElementText(element, fieldName);
		return value==null? null: new BigDecimal(value);
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((BigDecimal)object).toString());
	}
	
	public static final ValueResolve<BigDecimal> instance = new BigDecimalResolve();
}
