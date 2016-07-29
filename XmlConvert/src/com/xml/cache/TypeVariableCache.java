package com.xml.cache;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeVariableCache {

	private static Map<TypeVariable<?>, Type> cache = new HashMap<TypeVariable<?>, Type>();
	
	public static void loadCache(Type type){
		if(type instanceof Class) {
			return;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterized = (ParameterizedType)type;
			Class<?> cls = (Class<?>)parameterized.getRawType();
			TypeVariable<?>[] typeVariables = cls.getTypeParameters();
			Type[] actualTypes = parameterized.getActualTypeArguments();
			for(int x=0; x<typeVariables.length; x++) {
				cache.put(typeVariables[x], actualTypes[x]);
				loadCache(actualTypes[x]);
			}
		}
	}
	
	public static Type getType(TypeVariable<?> typeVariable){
		return cache.get(typeVariable);
	}
}
