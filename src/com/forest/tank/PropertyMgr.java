package com.forest.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Forest
 * @Date: 2019/6/3
 */
public class PropertyMgr {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key){
		if (properties == null) return null;
		return properties.get(key);
	}
}
