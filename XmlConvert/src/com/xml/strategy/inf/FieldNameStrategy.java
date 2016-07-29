package com.xml.strategy.inf;

import java.lang.reflect.Field;

public abstract class FieldNameStrategy {

	public abstract String convert(Field field);
	
	protected String separateCamelCase(String fieldName, char separate) {
		StringBuffer sb = new StringBuffer();
		for(int index=0; index<fieldName.length(); index++) {
			char cur = fieldName.charAt(index);
			if(index != 0 && Character.isUpperCase(cur)) {
				sb.append(separate);
			}
			sb.append(cur);
		}
		return sb.toString().toLowerCase();
	}
}
