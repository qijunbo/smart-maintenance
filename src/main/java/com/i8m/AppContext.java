package com.i8m;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	public static final String DomainName = "DomainName";

	private Map<String, Object> cache = new HashMap<String, Object>();

	public Object get(String key) {
		return cache.get(key);
	}

	public Object put(String key, Object value) {
		return cache.put(key, value);
	}

}
