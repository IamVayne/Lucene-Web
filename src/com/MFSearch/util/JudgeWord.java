/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月1日
 * 概述 : 一些词语判断规则
 */
package com.MFSearch.util;

import java.util.regex.Pattern;

/**
 * 类: JudgeWord
 *
 */
public class JudgeWord
{
	/**
	 * 用于测试 方法: main
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String string = "f(x)";
		System.out.println(isLetter(string));
	}

	/**
	 * 判断是否为数字 方法: isNumeric
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str)
	{
		for (int i = str.length(); --i >= 0;)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为字母 方法: isLetter
	 * 
	 * @param string
	 * @return
	 */

	public static boolean isLetter(String string)
	{
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		return pattern.matcher(string).matches();
	}

}
