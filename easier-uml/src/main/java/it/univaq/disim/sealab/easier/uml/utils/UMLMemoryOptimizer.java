package it.univaq.disim.sealab.easier.uml.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.uml2.common.util.CacheAdapter;

public class UMLMemoryOptimizer {

	
	public static void cleanup() {
		org.eclipse.uml2.common.util.CacheAdapter ca = org.eclipse.uml2.common.util.CacheAdapter.getInstance();
		ca.clear();
		String field = System.getProperty("org.eclipse.uml2.common.util.CacheAdapter.ThreadLocal") == null ? "INSTANCE" : "THREAD_LOCAL";
		
		try {
			java.lang.reflect.Field instance = ca.getClass().getDeclaredField(field);
			instance.setAccessible(true);
			instance.set(ca, null);
			
			System.gc();
			Method createCache = CacheAdapter.class.getDeclaredMethod("createCacheAdapter");
			createCache.setAccessible(true);
			instance.set(ca, createCache.invoke(ca));
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
