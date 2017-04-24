/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月14日
 * 概述 : Question提取器
 */
package com.MShow.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 类: QuestionSAXHandler
 *
 */
public class QuestionSAXHandler extends DefaultHandler
{
	String question = "";
	String part = "";
	String answer = "";
	static boolean flag = false;

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();

	}

	@Override
	public void endDocument() throws SAXException
	{
		super.endDocument();
	}

	/**
	 * 方法：startElement 控制标签是否是question
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("question"))
		{
			flag = true;
		}
	}

	/**
	 * 方法：endElement 如果标签在question内部，那么进行字符串的保存
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		super.endElement(uri, localName, qName);
		if (qName.equals("question"))
		{
			flag = false;
		}
		if (flag && qName.equals("line"))
		{
			question += part;

		} else if (!flag && (qName.equals("line") || qName.equals("name") || qName.equals("step")))
		{
			answer += part;
		}
	}

	/**
	 * 方法：characters 保存标签之间的内容
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		String temp = new String(ch, start, length);
		part = temp;

	}

	/**
	 * 
	 * 方法: getQuestion
	 * 
	 * @return 最终question字符串
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * 
	 * 方法: getAns
	 * 
	 * @return 最终answer字符串
	 */
	public String getAns()
	{
		return answer;
	}
}
