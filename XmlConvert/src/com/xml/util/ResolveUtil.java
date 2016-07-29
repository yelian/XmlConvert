package com.xml.util;

import com.xml.resolve.ArrayResolve;
import com.xml.resolve.ListResolve;
import com.xml.resolve.SetResolve;
import com.xml.resolve.ValueResolve;

public class ResolveUtil {

	public static boolean isLoopStruct(ValueResolve<?> resolve) {
		if(resolve instanceof ArrayResolve ||
				resolve instanceof SetResolve ||
				resolve instanceof ListResolve) {
			return true;
		} else 
			return false;
	}
}
