/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月2日
 * 概述 : 对切词之后的词汇片段进行初步处理
 */
package com.MFSearch.util;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * 类: ListOccurs
 *
 */
public class ListOccurs
{
	/**
	 * 
	 * 方法: getOccursList
	 * 
	 * @param input
	 *            用户输入的搜索文本
	 * @param analyzer
	 *            文本分析器
	 * @param queries
	 *            储存Query
	 * @param occurs
	 *            储存Occur 一些不重要的词汇单元可以通过该方法进行搜索控制
	 */
	public static void getOccursList(String input, Analyzer analyzer, ArrayList<Query> queries, ArrayList<Occur> occurs)
	{
		try
		{
			TokenStream ts = analyzer.tokenStream("", input);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			ts.reset();
			while (ts.incrementToken())
			{
				String keyword = term.toString();
				if (JudgeWord.isNumeric(keyword) || JudgeWord.isLetter(keyword))
				{
					occurs.add(Occur.SHOULD);
				} else
				{
					occurs.add(Occur.SHOULD);
				}
				TermQuery termquery = new TermQuery(new Term("text", keyword));
				queries.add(termquery);
				System.out.print(term.toString() + "|");
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
