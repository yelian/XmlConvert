package com.xml.resolve.factory;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xml.cache.TypeVariableCache;
import com.xml.resolve.ArrayResolve;
import com.xml.resolve.BeanResolve;
import com.xml.resolve.BigDecimalResolve;
import com.xml.resolve.BigIntegerResolve;
import com.xml.resolve.BooleanResolve;
import com.xml.resolve.ByteResolve;
import com.xml.resolve.CharacterResolve;
import com.xml.resolve.DateResolve;
import com.xml.resolve.DoubleResolve;
import com.xml.resolve.FloatResolve;
import com.xml.resolve.IntegerResolve;
import com.xml.resolve.ListResolve;
import com.xml.resolve.LongResolve;
import com.xml.resolve.MapResolve;
import com.xml.resolve.SetResolve;
import com.xml.resolve.ShortResolve;
import com.xml.resolve.StringResolve;
import com.xml.resolve.TimeResolve;
import com.xml.resolve.UnsureTypeResolve;
import com.xml.resolve.ValueResolve;
import com.xml.util.TypeUtil;

public class ValueResolveFactory {

	public static ValueResolve<?> getInstance(Type toResolveType){
		Type actual = toResolveType;
		if(toResolveType instanceof TypeVariable<?>) {
			actual = TypeVariableCache.getType((TypeVariable<?>)toResolveType);
		} else if(toResolveType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType)toResolveType;
			actual = pt.getRawType();
		} else if(toResolveType instanceof WildcardType) {
			WildcardType wt = (WildcardType)toResolveType;
			actual = wt.getUpperBounds()[0];
		}
		
		Class<?> rawType = TypeUtil.getRawType(actual);
		if(String.class == actual) {
			return StringResolve.instance;
		} else if (int.class == actual || Integer.class == actual) {
			return IntegerResolve.instance;
		} else if(float.class == actual || Float.class == actual) {
			return FloatResolve.instance;
		} else if(short.class == actual || Short.class == actual) {
			return ShortResolve.instance;
		} else if(long.class == actual || Long.class == actual) {
			return LongResolve.instance;
		} else if(boolean.class == actual || Boolean.class == actual) {
			return BooleanResolve.instance;
		} else if(double.class == actual || Double.class == actual) {
			return DoubleResolve.instance;
		} else if(byte.class == actual || Byte.class == actual) {
			return ByteResolve.instance;
		} else if(char.class == actual || Character.class == actual) {
			return CharacterResolve.instance;
		} else if(Date.class == actual) {
			return DateResolve.instance;
		} else if(Time.class == actual) {
			return TimeResolve.instance;
		} else if(BigDecimal.class == actual) {
			return BigDecimalResolve.instance;
		} else if(BigInteger.class == actual) {
			return BigIntegerResolve.instance;
		} else if (actual instanceof GenericArrayType || (actual instanceof Class && ((Class<?>)actual).isArray())) {
			return ArrayResolve.instance;
		} else if(Map.class.isAssignableFrom(rawType)) {
			return MapResolve.instance;
		} else if(List.class.isAssignableFrom(rawType)) {
			return ListResolve.instance;
		} else if(Set.class.isAssignableFrom(rawType)) {
			return SetResolve.instance;
		} else if (actual == Object.class) {
			return UnsureTypeResolve.instance;
		} else {
			return BeanResolve.instance;
		}
	}
}
