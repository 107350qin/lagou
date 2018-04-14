package com.lagou.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	private static String the="abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ0123456789";
	//判空
	public static boolean isNotNull(String string) {
		if(string==null || "".equals(string)) {
			return false;
		}else {
			return true;
		}
	}
	
	//截取访问路径的关键字
	public static String getTypeStr(String string) {
		return string.substring(string.lastIndexOf('/')+1);
	}
	
	//随机生成x位的字符串
	public static String getRandomStr(int x) {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<x;i++) {
			sb.append(the.charAt(((int)(Math.random()*the.length()))));
		}
		return sb.toString();
	}
	
	//时间格式化
	public static String getTimeStr() {
		return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
	}
	
	//去空
	public static String removeKong(String str) {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==' ') {}
			else {
				sb.append(str.charAt(i));
			}
		}
		// TODO Auto-generated method stub
		return sb.toString();
	}
	
	//乱码处理
	public static String rmGarbled(String str) {
		String string="";
		try {
			string= new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	/**
	 * 将字符串数组转换为都好分割的字符串
	 */
	public static String arrayToString(String[] str) {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<str.length-1;i++) {
			sb.append(str[i]+",");
		}
		sb.append(str[str.length-1]);
		return sb.toString();
	}
}
