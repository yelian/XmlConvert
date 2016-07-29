package com.xml.test.junit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import com.xml.XMLConvert;
import com.xml.test.InnerObj;
import com.xml.test.Obj;
import com.xml.test.Student;
import com.xml.type.TypeResovle;

public class TestXmlConvert {
	public static void main(String[] args) {
		Object o = test1();
		String xml = test1(o);
		System.out.println(xml);
	}
	
	public static Object test1(){
		//TypeToken<Obj<String>> t = new TypeToken<Obj<String>>() {};
		TypeResovle<Obj<InnerObj<Date>, Student[][]>> os = new TypeResovle<Obj<InnerObj<Date>, Student[][]>>() {};
		System.out.println(os);
		Class<?> clazz = os.getClass();
		Type superType = clazz.getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType)superType;
		Type type = parameterized.getActualTypeArguments()[0];
		return new XMLConvert().fromXml("d:/test.txt", type);
	}
	
	public static String test1(Object o){
		String xml = new XMLConvert().toXml("root", o);
		return xml;
	}
}
