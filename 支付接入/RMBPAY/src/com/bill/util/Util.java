package com.bill.util;

/**
 * ���ù�����
 * @author guangquan.hu
 *
 */
public class Util {
	
	/**
	 * ��֤�ַ����Ƿ�Ϊ��
	 * 
	 * @param str ��Ҫ��֤��String���͵ı���
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		if (str == "" || null == str) {
			return false;
		}
		return true; 	
	}
}
