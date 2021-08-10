package com.Util;

import java.text.DecimalFormat;

public class Format {
	
	public String ifnull(String s1,String def){
		if (s1==null) {
			return def;
		}else return s1;
	}
	
	/**
	 * @param strSrc   原始字符串
	 * @param flag      用于补齐的字符串
	 * @param strSrcLength  补齐长度
	 * @return      返回补齐后的字符串
	 */
	//左补齐空格
	public String leftPading(String strSrc,String flag,int strSrcLength) {
		String strReturn;
		StringBuilder strTemp = new StringBuilder();
		int curLength = strSrc.trim().length();
		if (curLength > strSrcLength&&strSrc != null) {
			strReturn = strSrc.trim().substring(0, strSrcLength);
		} else if (curLength == strSrcLength && strSrc != null ) {
			strReturn = strSrc.trim();
		} else {
			strTemp.append(String.valueOf(flag).repeat((strSrcLength - curLength)));
			strReturn = strTemp + strSrc.trim();
		}
		return strReturn;
	}

	/**
	 *
	 * @param dou   传入格式化的小数
	 * @param end   小数的尾数
	 * @return  返回格式化后的小数
	 */
	
	public Double Double_Format(double dou,int end){
		DecimalFormat two = new DecimalFormat("#." + "0".repeat(end));
		String string = two.format(dou);
		return Double.parseDouble(string);
	}
}
