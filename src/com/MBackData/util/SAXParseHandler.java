/**
 * 作者: 黄斌
 * 创建时间: 2017.3.27
 * 概述: 这个类用来提供SAX解析需要的handler
 */
package com.MBackData.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * 类: SAXParseHandler 
 * 概述: SAX解析的handler类
 */
public class SAXParseHandler extends DefaultHandler
{
	// 记录标签内的字符串
	String value = "";
	// 记录最终返回的结果
	String res = "";
	// 用于记录解析文件的数目
	static int filecount = 0;

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
	}

	/**
	 * 方法：endDocument 
	 * 文件解析结束之后执行该方法，后台控制台输出日志情况
	 */
	@Override
	public void endDocument() throws SAXException
	{
		super.endDocument();
		filecount++;
		System.out.println("第 " + filecount + " 个文件提取完毕~");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, qName, attributes);

	}

	/**
	 * 方法: endElement
	 * 
	 * 标签解析结束之后执行该方法，可以获取标签内的内容
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if (qName.equals("line") || qName.equals("step") || qName.equals("name"))
		{
			res += value;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String temp = new String(ch, start, length);
		value = temp;
	}

	/**
	 * 
	 * 方法: getValue
	 * 
	 * @return 结果字符串res 用来得到经过解析之后得到的文本的结果
	 */
	public String getValue()
	{
		return res;
	}
}
