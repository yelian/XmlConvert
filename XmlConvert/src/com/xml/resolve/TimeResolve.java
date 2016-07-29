package com.xml.resolve;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;

import com.xml.exception.XmlConvertException;

public class TimeResolve extends ValueResolve<Time> {
	private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");
	@Override
	public Time getValue(Element element, String fieldName, Type type) {
		String value = getElementText(element, fieldName);
		if(value == null)
			return null;
		try {
			Date date = format.parse(value);
			Time time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			throw new XmlConvertException(e);
		}	
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		Time time = (Time)object;
		Date date = new Date(time.getTime());
		setElementText(element, fieldName, object==null? null: format.format(date));
	}
	
	public static final ValueResolve<Time> instance = new TimeResolve();
}
