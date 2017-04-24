/**
 * 作者 : 黄斌
 * 创建时间 : 2017.3.27
 * 概述 : 用来解析流得到文本
 */
package com.MBackData.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * 
 * 类: BackFileParseWithSAX 
 * 概述 : 使用SAX方式解析后台文件
 */
public class BackFileParseWithSAX
{
	/**
	 * 
	 * 方法: getStringwithSAX
	 * 
	 * @param InputStream
	 *            input
	 * @return 解析结果
	 */

	public String getStringwithSAX(InputStream input)
	{
		String string = null;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser parser = factory.newSAXParser();
			SAXParseHandler handler = new SAXParseHandler();
			parser.parse(input, handler);
			string = handler.getValue();

		} catch (ParserConfigurationException | SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

}
