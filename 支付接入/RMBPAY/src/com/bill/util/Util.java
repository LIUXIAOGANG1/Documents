package com.bill.util;

/**
 * 常用工具类
 * @author guangquan.hu
 *
 */
public class Util {
	
	/**
	 * 验证字符串是否为空
	 * 
	 * @param str 需要验证的String类型的变量
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		if (str == "" || null == str) {
			return false;
		}
		return true; 	
	}
}
