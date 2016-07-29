package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public abstract class ValueResolve<T> {

	public abstract T getValue(Element element, String fieldName, Type type);
	
	public abstract void setElement(Element element, String fieldName, Object object);
	
	protected String getElementText(Element element, String fieldName){
		if(fieldName != null)
			element = element.element(fieldName);
		if(element == null)
			return null;
		String val = element.getTextTrim();
		return "".equals(val)? null :val;
	}
	
	protected void setElementText(Element element, String fieldName, String fieldValue){
		if(fieldName != null)
			element = element.addElement(fieldName);
		
		if(fieldValue == null) {
			return;
		}

		element.setText(fieldValue);
	}
}
