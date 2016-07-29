package com.xml.strategy;

import java.lang.reflect.Field;

import com.xml.strategy.inf.FieldNameStrategy;

public class UpperCaseWithUnderscoresStrategy extends FieldNameStrategy {

	@Override
	public String convert(Field field) {
		String fieldName = field.getName();
		return this.separateCamelCase(fieldName, '_').toUpperCase();
	}
}
