/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月9日
 * 概述 : 与搜索系统交互的接口层
 */
package com.MFSearch.window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;

import com.MBackData.util.BackFileParseWithSAX;
import com.MBackData.util.XmlBuilder;
import com.MFSearch.search.SearchCore;
import com.MShow.util.ShowRes;

import edu.vayne.window.SLBFilter;

/**
 * 类: SearchWindow
 *
 */
public class SearchWindow
{
	/**
	 * 
	 * 方法: search1
	 * 
	 * @param input
	 * @return 返回搜索结果列表信息
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		String in = "1例 7]已知如图20-20,在 \\text{??}ABC?? \\angleA=120^{\\circ},点D、E在 Bcr.\\text{??}ADE为等边三 角形,求证: {\\frac{AB^{2}}{AE^{2}}}=\\frac{BC}{EC}\\cdot";
		in = in.trim().replace(" ", "");
		int count = 0;
		// System.out.println(in);
		in = SLBFilter.remAndrepWords(in);
		Map<Document, Float> map = search2(in);
		System.out.println("============");
		Iterator<Entry<Document, Float>> it = map.entrySet().iterator();
		BackFileParseWithSAX saxparser = new BackFileParseWithSAX();
		XmlBuilder convertToxml = new XmlBuilder();
		File file1 = new File("D:\\temp.html");
		PrintStream printStream = new PrintStream(file1);
		while (it.hasNext())
		{
			count++;
			if (count == 3)
			{
				break;
			}
			String path = it.next().getKey().get("path");
			File file = new File(path);
			System.out.println(path);
			String res = saxparser.getStringwithSAX(convertToxml.getAllstream(file));
			res = res.replace("$", "");
			try
			{
				String xx = new ShowRes().show01(res);
				printStream.append(xx);
				printStream.println();
				System.out.println(xx);

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("*****************************");
		}
		printStream.close();
	}

	/**
	 * 
	 * 方法: search1
	 * 
	 * @param input使用搜索核心类的search1方法
	 * @return 返回list
	 */
	public static List<Document> search1(String input)
	{
		List<Document> list = SearchCore.search1(input);
		for (Document targetDoc : list)
		{
			System.out.print("path：" + targetDoc.get("path"));
		}
		return list;
	}

	/**
	 * 
	 * 方法: search2
	 * 
	 * @param input
	 * @return 返回搜索结果及其评分信息
	 */
	public static Map<Document, Float> search2(String input)
	{
		Map<Document, Float> map = SearchCore.search2(input);
		Iterator<Entry<Document, Float>> it = map.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<Document, Float> entry = it.next();
			System.out.print(entry.getKey().get("path") + "   ");
			System.out.println(entry.getValue());
		}

		return map;
	}

	/**
	 * 
	 * 方法: getQuery
	 * 
	 * @return 用于搜索的Query
	 */
	public static Query getQuery()
	{
		return SearchCore.getQuery();
	}

}
