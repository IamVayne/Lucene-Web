/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月14日
 * 概述 : 展示搜索结果 
 */
package com.MShow.util;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.MFSearch.window.SearchWindow;

/**
 * 类: ShowRes
 *
 */
public class ShowRes
{
	/**
	 * 
	 * 方法: show01
	 * 
	 * @param result
	 *            搜索结果文本
	 * @return 返回一个可以用于html显示的文本
	 * @throws IOException
	 */
	public String show01(String result) throws IOException
	{
		Query query = SearchWindow.getQuery();
		String showtext = null;
		if (query == null)
		{
			return null;
		}
		Scorer scorer = new QueryScorer(query);
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"color\">", "</font>");

		Highlighter highlighter = new Highlighter(formatter, scorer);

		@SuppressWarnings("resource")
		TokenStream tokenStream = new IKAnalyzer(true).tokenStream("text", new StringReader(result));
		try
		{
			showtext = highlighter.getBestFragment(tokenStream, result);

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{

		}
		tokenStream.close();
		return showtext;
	}

}
