package com.xml.resolve;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.dom4j.Element;

import com.xml.UseConfig;
import com.xml.exception.XmlConvertException;
import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.util.ReflectionUtil;
import com.xml.util.TypeUtil;

public class BeanResolve<T> extends ValueResolve<T>{

	@Override
	public T getValue(Element element, String fieldName, Type type) {
		if(fieldName != null)
			element = element.element(fieldName);
		if(element == null)
			return null;
		Class<?> cls = TypeUtil.getRawType(type);
		T instance = ReflectionUtil.getInstance(cls);
		for(Field field: cls.getDeclaredFields()) {
			Type fieldType = field.getGenericType();
			String fName = UseConfig.fieldNameStrategy.convert(field);
			if(fName == null)
				continue;
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(fieldType);
			Object fieldValue = resolve.getValue(element, fName, fieldType);
			try {
				if(fieldValue != null) {
					field.setAccessible(true);
					field.set(instance, fieldValue);
				}
			} catch (Exception e) {
				throw new XmlConvertException(e);
			} 
		}
		return instance;
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		//Element ele = element;
		if(fieldName != null){
			element = element.addElement(fieldName);
		}
		if(object == null)
			return;
		Class<?> tClass = object.getClass();
		for(Field field: tClass.getDeclaredFields()) {
			String fName = UseConfig.fieldNameStrategy.convert(field);
			if(fName == null)
				continue;
			Object fValue = null;
			try {
				field.setAccessible(true);
				fValue = field.get(object);
			} catch (Exception e) {
				throw new XmlConvertException(e);
			}
			if(fValue == null){
				element.addElement(fName);
				continue;
			}				
			Class<?> fClass = fValue.getClass();
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(fClass);
			resolve.setElement(element, fName, fValue);
		}
	}
	
	public static final ValueResolve<?> instance = new BeanResolve<Object>();
}
