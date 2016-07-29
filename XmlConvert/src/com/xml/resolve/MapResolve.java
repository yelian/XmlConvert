package com.xml.resolve;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Element;

import com.xml.resolve.factory.ValueResolveFactory;
import com.xml.util.TypeUtil;

public class MapResolve extends ValueResolve<Map<?, ?>> {

	@SuppressWarnings("unchecked")
	@Override
	public Map<?, ?> getValue(Element element, String fieldName, Type type) {
		Element ele = element.element(fieldName);
		if(ele == null) 
			return null;
		Type[] kvType = TypeUtil.getMapType(type);
		List<Element> mapElements = ele.elements();
		if(mapElements == null || mapElements.size() == 0) 
			return null;
		Type vType = kvType[1];
		ValueResolve<?> resolve = ValueResolveFactory.getInstance(vType);
		Map<String, Object> map = new HashMap<String, Object>();
		for(Element mapElement: mapElements) {
			String k = mapElement.getName();
			map.put(k, resolve.getValue(mapElement, null, vType));
		}
		return map;
	}
	
	@Override
	public void setElement(Element element, String fieldName, Object object) {
		if(fieldName != null) {
			element = element.addElement(fieldName);
		}
			
		if(object == null) {
			//element.addElement(fieldName);
			return;
		}
		Map<?, ?> map = (Map<?, ?>)object;
		Set<?> set = map.entrySet();
		int size = set.size();
		if(size == 0) {
			//element.addElement(fieldName);
			return;
		}
		Iterator<?> it = set.iterator();
		while(it.hasNext()){
			Entry<?, ?> entry = (Entry<?, ?>)it.next();
			Object oKey = entry.getKey();
			String key = (oKey==null || "".equals(oKey.toString().trim())) ? "NULL": oKey.toString();
			Object oValue = entry.getValue();
			ValueResolve<?> resolve = ValueResolveFactory.getInstance(oValue.getClass());
			resolve.setElement(element, key, oValue);
		}
	}
	
	public static final ValueResolve<Map<?, ?>> instance = new MapResolve();
}
