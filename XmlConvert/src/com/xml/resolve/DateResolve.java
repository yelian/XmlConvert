package com.xml.resolve;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;

import com.xml.exception.XmlConvertException;

public class DateResolve extends ValueResolve<Date> {

	@Override
	public Date getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		if(value == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		try {
			sdf.setLenient(true);
			return sdf.parse(value);
		} catch (ParseException e) {
			throw new XmlConvertException("日期格式化失败：无法将"+value+"根据日期格式"+date_format+"进行格式化。", e);
		}
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		Date dt = (Date)object;
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
		setElementText(element, fieldName, object==null? null: sdf.format(dt));
	}
	
	private String date_format = "yyyyMMdd";
	public static final ValueResolve<Date> instance = new DateResolve();

}
