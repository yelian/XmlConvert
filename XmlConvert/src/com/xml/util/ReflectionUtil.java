package com.xml.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import com.xml.exception.XmlConvertException;

public class ReflectionUtil {

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Type type){
		Class<?> cls = TypeUtil.getRawType(type);
		Constructor<?> constructor = null;
		try {
			constructor = cls.getConstructor((Class<?>[])null);
			return (T)constructor.newInstance((Object[])null);
		} catch (NoSuchMethodException e) {
			throw new XmlConvertException(cls.getName() + "必须包含一个无参构造函数", e);
		}  catch (Exception e) {
			throw new XmlConvertException(e);
		} 
	}
}
