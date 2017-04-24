/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月14日
 * 概述 : 对文件中的question和answer进行解析
 */
package com.MShow.util;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.MBackData.util.XmlBuilder;

/**
 * 类: Answer
 *
 */
public class Answer
{
	/**
	 * 
	 * 方法: main
	 * 
	 * @param args
	 *            命令行参数
	 */
	public static void main(String[] args)
	{
		String path = "G:\\questionThree\\BSJPXA8S-2.2.2-七-2.lft";
		System.out.println(new Answer().getAns(path));

	}

	/**
	 * 
	 * 方法: getQuestion
	 * 
	 * @param path
	 *            文件路劲
	 * @return 得到question字符串
	 */
	public String getQuestion(String path)
	{
		String string = null;
		try
		{
			File file = new File(path);
			InputStream inputStream = new XmlBuilder().getAllstream(file);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			QuestionSAXHandler handler = new QuestionSAXHandler();
			parser.parse(inputStream, handler);
			string = handler.getQuestion();

		} catch (Exception exception)
		{
			exception.printStackTrace();
		}

		return string;
	}

	/**
	 * 
	 * 方法: getAns
	 * 
	 * @param path
	 *            文件路劲
	 * @return 得到answer字符串
	 */
	public String getAns(String path)
	{
		String string = null;
		try
		{
			File file = new File(path);
			InputStream inputStream = new XmlBuilder().getAllstream(file);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			QuestionSAXHandler handler = new QuestionSAXHandler();
			parser.parse(inputStream, handler);
			string = handler.getAns();

		} catch (Exception exception)
		{
			exception.printStackTrace();
		}

		return string;
	}
}
