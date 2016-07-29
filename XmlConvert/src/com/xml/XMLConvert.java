package com.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xml.exception.XmlConvertException;
import com.xml.resolve.ValueResolve;
import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.strategy.DefaultStrategy;
import com.xml.strategy.inf.FieldNameStrategy;
import com.xml.util.TypeUtil;

public class XMLConvert {

	public <T> T fromXml(String xmlPath, Type type){
		Element element = getDocument(xmlPath);
		T t = doConvert(element, type);
		return t;
	}
	
	public <T> String toXml(String rootElementName, T t){
		StringWriter sw = new StringWriter();
		Document document = createDocument(rootElementName);
		Element element = document.addElement(rootElementName);
		ValueResolve<?> resolve = ValueResolveFactory.getInstance(t.getClass());
		resolve.setElement(element, null, t);
		try {
			document.write(sw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	@SuppressWarnings("unchecked")
	<T> T doConvert(Element element, Type type){
		ValueResolve<?> resolve = ValueResolveFactory.getInstance(TypeUtil.getRawType(type));
		return (T)resolve.getValue(element, null, type);
	}
	
	protected Element getDocument(String xmlPath){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(xmlPath));
			Element root = document.getRootElement();
			return root;
		} catch (FileNotFoundException e1) {
			throw new XmlConvertException(e1);
		} catch (DocumentException e1) {
			throw new XmlConvertException(e1);
		}
	}
	
	protected Document createDocument(String rootElementName){
		Document document = DocumentFactory.getInstance().createDocument("utf-8");
		return document;
	}
	
	public XMLConvert(){
		UseConfig.fieldNameStrategy = new DefaultStrategy();
	}
	
	public XMLConvert(FieldNameStrategy strategy) {
		UseConfig.fieldNameStrategy = strategy;
	}
}
