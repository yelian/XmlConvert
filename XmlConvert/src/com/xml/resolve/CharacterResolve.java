package com.xml.resolve;

import java.lang.reflect.Type;

import org.dom4j.Element;

public class CharacterResolve extends ValueResolve<Character> {

	@Override
	public Character getValue(Element element, String fieldName, Type type) {
		String value = this.getElementText(element, fieldName);
		return value==null? null: value.charAt(0);
	}

	@Override
	public void setElement(Element element, String fieldName, Object object) {
		setElementText(element, fieldName, object==null? null: ((Character)object).toString());
	}
	
	public static final ValueResolve<Character> instance = new CharacterResolve();
}
