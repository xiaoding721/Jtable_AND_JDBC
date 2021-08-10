package com.Util;

import java.util.Random;

public class Randoms {

	/**
	 * @param len_min   长度下限
	 * @param len_max   长度上限
	 * @return          返回随机字符串
	 */
	public String random_str(int len_min, int len_max){
		String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		int stringLength = this.random_int(len_max,len_min);
		for (int j = 0; j < stringLength; j++) {
			int index = random.nextInt(str.length());
			char c = str.charAt(index);
			stringBuilder.append(c);
		}
		return stringBuilder.toString();    //将stringBuilder转换为String类型的字符串
	}

	/**
	 * @param max 随机小数的上限
	 * @param min 随机小数的下限
	 * @param end 随机小数的尾数
	 * @return  返回随机小数
	 */
	public double random_double(int min,int max,int end){
		Random random = new Random();
		Format format = new Format();
		double dou =random.nextDouble()*max+min;
		return format.Double_Format(dou,end);
	}

	/**
	 * @param max   随机整数的上限
	 * @param min   随机整数的下限
	 * @return      返回随机整数
	 */
	public int random_int(int max,int min){
		Random random = new Random();
		return random.nextInt(max)+min;
	}

}
