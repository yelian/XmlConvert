package com.xml.strategy;

import java.lang.reflect.Field;

import com.xml.strategy.inf.FieldNameStrategy;

public class LowerCaseWithUnderscoresStrategy extends FieldNameStrategy {

/**
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   *   <li>someFieldName ---> some_field_name</li>
   *   <li>_someFieldName ---> _some_field_name</li>
   *   <li>aStringField ---> a_string_field</li>
   *   <li>aURL ---> a_u_r_l</li>
   * </ul>
   */
	@Override
	public String convert(Field field) {
		String fieldName = field.getName();
		return this.separateCamelCase(fieldName, '_').toLowerCase();
	}
}
