package com.synonyms.util;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

	public static boolean isNotNull(Object object) {
		return (object != null);
	}

	public static boolean isEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static <T> boolean isEmpty(Collection<T> objList) {
		return isNull(objList) || objList.size() == 0;
	}

	public static <T> boolean isNotEmpty(Collection<T> objList) {
		return !isEmpty(objList);
	}

	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return isNull(map) || map.size() == 0;
	}

	public static boolean isNull(Object object) {
		return (object == null);
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumericNo(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
