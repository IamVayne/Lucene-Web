/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月1日
 * 概述 : 搜索核心类，提供多个搜索方法
 */
package com.MFSearch.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.MBackIndex.index.IndexBuild;
import com.MFSearch.util.GenerateQuery;
import com.MFSearch.util.ListOccurs;

import edu.vayne.window.SLBFilter;

/**
 * 类: SearchTest
 *
 */
public class SearchCore
{
	public static void main(String[] args)
	{
		String input = "在平面内,下列数据不能确定物体位置的是";
		search1(input);
		System.out.println("END");
	}

	/**
	 * 展示的结果数目控制
	 */
	static int RESULT_COUNT = 5;
	static Query querysave = null;

	/**
	 * 
	 * 方法: search1
	 * 
	 * @param input
	 * @return 搜索结果列表，该列表仅仅包含Document
	 */
	public static List<Document> search1(String input)
	{
		String Index_Source = IndexBuild.getINDEX_PATH();
		ArrayList<Query> querys;
		ArrayList<Occur> occurs;
		Directory directory;
		List<Document> search_result = new ArrayList<>();
		try
		{
			directory = FSDirectory.open(new File(Index_Source));
			IndexReader iReader = DirectoryReader.open(directory);
			IndexSearcher iSearcher = new IndexSearcher(iReader);
			querys = new ArrayList<>();
			occurs = new ArrayList<>();
			Analyzer analyzer = new IKAnalyzer(true);
			input = SLBFilter.remAndrepWords(input).replace(" ", "");
			ListOccurs.getOccursList(input, analyzer, querys, occurs);
			Query query = GenerateQuery.getQuery(querys, occurs);
			querysave = query;
			Sort sort = new Sort();
			TopDocs topDocs = iSearcher.search(query, RESULT_COUNT, sort);
			System.out.println("命中：" + topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (topDocs.totalHits < RESULT_COUNT)
			{
				RESULT_COUNT = topDocs.totalHits;
			}
			for (int i = 0; i < RESULT_COUNT; i++)
			{
				search_result.add(iSearcher.doc(scoreDocs[i].doc));
				Document targetDoc = iSearcher.doc(scoreDocs[i].doc);
				Explanation explanation = iSearcher.explain(query, scoreDocs[i].doc);
				System.out.print("path：" + targetDoc.get("path"));
				System.out.println(" grade: " + explanation.getValue());
			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return search_result;
	}

	/**
	 * 
	 * 方法: search2
	 * 
	 * @param input
	 * @return document和评分机制得分组成的map对象
	 */

	public static Map<Document, Float> search2(String input)
	{
		String Index_Source = IndexBuild.getINDEX_PATH();
		ArrayList<Query> querys;
		ArrayList<Occur> occurs;
		Directory directory;
		Map<Document, Float> map = new LinkedHashMap<>();
		try
		{
			directory = FSDirectory.open(new File(Index_Source));
			IndexReader iReader = DirectoryReader.open(directory);
			IndexSearcher iSearcher = new IndexSearcher(iReader);
			querys = new ArrayList<>();
			occurs = new ArrayList<>();
			Analyzer analyzer = new IKAnalyzer(true);
			input = SLBFilter.remAndrepWords(input).replace(" ", "");
			ListOccurs.getOccursList(input, analyzer, querys, occurs);
			Query query = GenerateQuery.getQuery(querys, occurs);
			querysave = query;
			Sort sort = new Sort();
			TopDocs topDocs = iSearcher.search(query, RESULT_COUNT, sort);
			System.out.println("命中：" + topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (topDocs.totalHits < RESULT_COUNT)
			{
				RESULT_COUNT = topDocs.totalHits;
			}

			for (int i = 0; i < RESULT_COUNT; i++)
			{
				Document targetDoc = iSearcher.doc(scoreDocs[i].doc);
				Explanation explanation = iSearcher.explain(query, scoreDocs[i].doc);
				map.put(targetDoc, explanation.getValue());
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 方法: getQuery
	 * 
	 * @return 得到用于搜索时的Query对象
	 */
	public static Query getQuery()
	{

		return querysave;
	}

	/**
	 * 
	 * 方法: getSearchCount
	 * 
	 * @return 得到搜索结果控制数目
	 */
	public static int getSearchCount()
	{
		return RESULT_COUNT;
	}

}
