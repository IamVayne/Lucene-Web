/**
 * 作者 ：黄斌
 * 创建时间 ：2017.3.27
 * 概述 ：将后台不标准的lft文件转换成标准的xml文件
 */
package com.MBackData.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * 类: XmlBuilder 概述：建立Xml文件的类
 */
public class XmlBuilder
{
	/**
	 * 
	 * 方法: getAllstream
	 * 
	 * @param file,一个不标准的lft文件
	 * @return 一个标准的xml文件的流 输入文件，得到一个标准的xml文件流
	 */
	public InputStream getAllstream(File file)
	{
		InputStream pre = null;
		InputStream end = null;
		try
		{
			Vector<InputStream> vec = new Vector<>();
			InputStream input = new FileInputStream(file);
			String file_path = file.getAbsolutePath();
			if (EncodingDetect.getJavaEncode(file_path).equals("GB2312"))
			{
//				System.out.println("编码：gb2312");
				pre = new ByteArrayInputStream("<?xml version = \"1.0\" encoding = \"gb2312\"?><data>".getBytes());
				end = new ByteArrayInputStream("</data>".getBytes());
			} else if (EncodingDetect.getJavaEncode(file_path).equals("UTF-8"))
			{
				pre = new ByteArrayInputStream("<?xml version = \"1.0\" encoding = \"utf8\"?><data>".getBytes());
				end = new ByteArrayInputStream("</data>".getBytes());
			} else if (EncodingDetect.getJavaEncode(file_path).equals("GBK"))
			{
				pre = new ByteArrayInputStream("<?xml version = \"1.0\" encoding = \"gbk\"?><data>".getBytes());
				end = new ByteArrayInputStream("</data>".getBytes());
			} else
			{
				pre = new ByteArrayInputStream("<?xml version = \"1.0\" encoding = \"gb2312\"?><data>".getBytes());
				end = new ByteArrayInputStream("</data>".getBytes());
			}

			vec.addElement(pre);
			vec.addElement(input);
			vec.addElement(end);
			Enumeration<InputStream> enums = vec.elements();
			InputStream result = new SequenceInputStream(enums);
			return result;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String getEncode(File file)
	{
		return null;
	}

}
