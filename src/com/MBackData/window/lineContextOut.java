/**
 * 作者: 黄斌
 * 创建时间：2017.3.27
 * 概述: 接口ContextOut的一个实现类
 */
package com.MBackData.window;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.MBackData.util.BackFileParseWithSAX;
import com.MBackData.util.EncodingDetect;
import com.MBackData.util.FileCharsetConverter;
import com.MBackData.util.XmlBuilder;

/**
 * 
 * 类: lineCcontextOut 概述: 接口ContextOut的一个实现类，包含一个测试方法main
 */
public class lineContextOut implements ContextOut
{
	/**
	 * 
	 * 方法: main
	 * 
	 * @param args
	 *            用于测试的主方法
	 */
	public static void main(String[] args)
	{
		String string = new lineContextOut().getBaseString(new File("C:\\Users\\vayne\\Desktop\\东营服务中心2[195]\\test"))
				.get(2);
		String st = string.replace(" ", "");
		System.out.println(st.replace("\n", "").replace("\\s", ""));
	}

	/**
	 * 方法: getBaseString
	 * 
	 * @param 指定的一个file
	 * @return file经过处理之后file的名字、路径和内容组成的list
	 */
	@Override
	public List<String> getBaseString(File file)
	{
		String result = null;
		String file_path = null;
		String file_name = null;
		ArrayList<String> list = null;
		XmlBuilder convertToxml = new XmlBuilder();
		BackFileParseWithSAX saxparser = new BackFileParseWithSAX();
		try
		{
			file_path = file.getAbsolutePath();
			file_name = file.getName();
			/*
			 * if (EncodingDetect.getJavaEncode(file_path).equals("UTF-8")) {
			 * FileCharsetConverter.convert(file, "GB2312", "UTF-8"); //
			 * System.out.println("文件编码：" + "UTF-8"); } else if
			 * (EncodingDetect.getJavaEncode(file_path).equals("GB2312")) { //
			 * FileCharsetConverter.convert(file, "GB2312", "UTF-8"); //
			 * System.out.println("文件编码：" + "GB2312"); } else if
			 * (EncodingDetect.getJavaEncode(file_path).equals("GBK")) {
			 * FileCharsetConverter.convert(file, "GBK", "GB2312"); //
			 * System.out.println("文件编码：" + "GBK"); }
			 */

//			File newFile = new File(file.getAbsolutePath());
			InputStream in = convertToxml.getAllstream(file);
			result = saxparser.getStringwithSAX(in);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (result == null)
		{
			return null;
		}
		list = new ArrayList<>();
		list.add(file_name);
		list.add(file_path);
		list.add(result);
		return list;
	}

}
