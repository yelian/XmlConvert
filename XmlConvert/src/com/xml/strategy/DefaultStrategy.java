package com.xml.strategy;

import java.lang.reflect.Field;

import com.xml.strategy.inf.FieldNameStrategy;

public class DefaultStrategy extends FieldNameStrategy {

	@Override
	public String convert(Field field) {
		return field.getName();		
	}
}