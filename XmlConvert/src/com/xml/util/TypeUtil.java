package com.xml.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import com.xml.cache.TypeVariableCache;
import com.xml.exception.XmlConvertException;

public class TypeUtil {

	public static Class<?> getRawType(Type type) {
		if(type instanceof Class<?>) {
			return (Class<?>)type;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)type;
			Type rawType = pt.getRawType();
			if(rawType instanceof Class<?>) {
				return (Class<?>)rawType;
			} else {
				throw new XmlConvertException("无法将"+rawType+"转换为Class");
			}
		} else if (type instanceof TypeVariable) {
			return getRawType(TypeVariableCache.getType((TypeVariable<?>)type));
		} else if (type instanceof WildcardType) {
			WildcardType wt = (WildcardType) type;
			return getRawType(wt.getUpperBounds()[0]);
		} else if (type instanceof GenericArrayType) {
			GenericArrayType gat = (GenericArrayType)type;
			return getRawType(gat.getGenericComponentType());
		} else {
			throw new XmlConvertException(type.getClass().getName()+"不是一个有效的type类型");
		}
	}
	
	public static Type getFieldType(Type toResolveType) {
		if(toResolveType instanceof Class<?>) {
			return toResolveType;
		} else if (toResolveType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)toResolveType;
			Type rawType = pt.getRawType();
			if(rawType instanceof Class<?>) {
				return (Class<?>)rawType;
			} else {
				throw new XmlConvertException("无法将"+rawType+"转换为Class");
			}
		} else if (toResolveType instanceof TypeVariable) {
			return TypeVariableCache.getType((TypeVariable<?>)toResolveType);
		} else if (toResolveType instanceof WildcardType) {
			WildcardType wt = (WildcardType) toResolveType;
			return getRawType(wt.getUpperBounds()[0]);
		} else if (toResolveType instanceof GenericArrayType) {
			GenericArrayType gat = (GenericArrayType)toResolveType;
			return getRawType(gat.getGenericComponentType());
		} else {
			return toResolveType;
		}
	}
	
	/**
	 * 
	 * @Title: getComonentType
	 * @Description 获取数组的component类型，字段数组定义存在如下情况：
	 * <ul>
	 * 	<li>T t: T为泛型变量，实际类型如String[]</li>
	 * 	<li>T[] t: T为泛型变量，实际类型为如String或，String[]</li>
	 * 	<li>String[] t: 此时数组为class，通过getComponentType获取component类型</li>
	 * </ul>
	 * @param type
	 * @return
	 */
	public static Type getComonentType(Type type){
		if(type instanceof TypeVariable) {
			type = TypeVariableCache.getType((TypeVariable<?>)type);
		}
		return type instanceof GenericArrayType
		        ? ((GenericArrayType) type).getGenericComponentType()
		        : ((Class<?>) type).getComponentType();
	}
	
	public static Type getCollectionType(Type type) {
		if(type instanceof Class) {
			return Object.class;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)type;
			return pt.getActualTypeArguments()[0];
		} else {
			throw new XmlConvertException("无效的集合类型"+type.getClass().getName());
		}
	}
	
	public static Type[] getMapType(Type type) {
		if(type instanceof Class) {
			return new Type[]{Object.class, Object.class};
		} else if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)type;
			return new Type[]{getFieldType(pt.getActualTypeArguments()[0]), 
						pt.getActualTypeArguments()[1]};
		} else {
			throw new XmlConvertException("无效的集合类型"+type.getClass().getName());
		}
	}
}
