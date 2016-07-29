package com.xml.strategy;

import java.lang.reflect.Field;

import com.xml.annotation.Mapping;
import com.xml.strategy.inf.FieldNameStrategy;

public class AnnotationStrategy extends FieldNameStrategy {

	@Override
	public String convert(Field field) {
		Mapping annotation = field.getAnnotation(Mapping.class);
		if(annotation != null)
			return annotation.mapping();
		return null;
	}
}
