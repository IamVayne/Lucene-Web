/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月2日
 * 概述 : 生成Query用于查询
 */
package com.MFSearch.util;

import java.util.ArrayList;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;

/**
 * 类: GenerateQuery
 *
 */
public class GenerateQuery
{
	/**
	 * 
	 * 方法: getQuery
	 * 
	 * @param querys
	 *            多个TermQuery组成的列表
	 * @param occurs
	 *            多个Occur组成的列表
	 * @return 返回一个BooleanQuery
	 */
	public static Query getQuery(ArrayList<Query> querys, ArrayList<Occur> occurs)
	{
		if (querys.size() != occurs.size())
		{
			System.err.println("ERROR");
			return null;
		}

		BooleanQuery query = new BooleanQuery();

		for (int i = 0; i < querys.size(); i++)
		{
			query.add(querys.get(i), occurs.get(i));
		}

		return query;
	}

}
