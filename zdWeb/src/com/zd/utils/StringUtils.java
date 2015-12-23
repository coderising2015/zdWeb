package com.zd.utils;

/**
 * 字符串工具类
 * @author hzcl_sky
 *
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return obj != null && !"".equals(obj);
	}
	
	public static String toStr(Object obj) {
		if (isEmpty(obj)) {
			return obj.toString();
		} else
			return "";
	}
	public static int toInt(Object obj) {
		if (isEmpty(obj)) {
			return Integer.parseInt(toStr(obj));
		} else
			return 0;
	}
	public static float toFloat(Object obj) {
		if (isEmpty(obj)) {
			return Float.parseFloat(toStr(obj));
		} else
			return 0;
	}
	public static double toDouble(Object obj) {
		if (isEmpty(obj)) {
			return Double.parseDouble(toStr(obj));
		} else
			return 0;
	}
	
	
	
}
