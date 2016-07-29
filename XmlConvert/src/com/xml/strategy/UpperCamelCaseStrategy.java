package com.xml.strategy;

import java.lang.reflect.Field;

import com.xml.strategy.inf.FieldNameStrategy;

public class UpperCamelCaseStrategy extends FieldNameStrategy {

  /**
   * <p>Here's a few examples of the form "Java Field Name" ---> "JSON Field Name":</p>
   * <ul>
   *   <li>someFieldName ---> SomeFieldName</li>
   *   <li>_someFieldName ---> _SomeFieldName</li>
   * </ul>
   */
	@Override
	public String convert(Field field) {
		String fieldName = field.getName();
		int index = 0;
		for(; index<fieldName.length(); index++) {
			if(Character.isLetter(fieldName.charAt(index))) {
				if(Character.isLowerCase(fieldName.charAt(index)))
					break;
				else 
					return fieldName;
			}			
		}
		char[] chs = fieldName.toCharArray();
		chs[index] = Character.toUpperCase(chs[index]);
		return String.valueOf(chs);
	}
}
