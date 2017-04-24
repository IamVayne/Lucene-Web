/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月15日
 * 概述 : 高亮显示类
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Document;

import com.MFSearch.search.SearchCore;
import com.MShow.util.ShowRes;

/**
 * 类: DealResult
 *
 */
public class DealResult
{
	static List<String> paths = null;

	/**
	 * 
	 * 方法: getHighlightString
	 * 
	 * @param input
	 * @return 对最终结果进行高亮显示的方法
	 */
	public static List<String> getHighlightString(String input)
	{
		paths = new ArrayList<>();
		List<String> hightextlist = new ArrayList<>();
		try
		{
			String input2 = new String(input.getBytes("ISO-8859-1"), "UTF-8");
			Map<Document, Float> map = SearchCore.search2(input2);
			for (Entry<Document, Float> entry : map.entrySet())
			{
				// String id = entry.getKey().get("name");
				String path = entry.getKey().get("path");

				String text = entry.getKey().get("text");
				// Float score = entry.getValue();
				String highlightext = new ShowRes().show01(text);
				paths.add(path);
				hightextlist.add(highlightext);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hightextlist;
	}

	/**
	 * 
	 * 方法: getPaths
	 * 
	 * @return 搜索结果路径集合
	 */
	public static List<String> getPaths()
	{
		return paths;
	}
}
