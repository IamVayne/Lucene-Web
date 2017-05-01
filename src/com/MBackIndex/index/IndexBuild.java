/**
 * 作者 : 黄斌
 * 创建时间 : 2017年3月27日
 * 概述 : 索引构建层文件
 */
package com.MBackIndex.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.MBackData.window.ContextOut;
import com.MBackData.window.lineContextOut;

/**
 * 类: IndexBuild 概述：索引构建类
 */
public class IndexBuild
{
	static String INDEX_PATH = "D:\\index03";
	static String SOURCE_PATH = "G:\\questionThree";
	Analyzer analyzer = null;
	IndexWriter indexWriter = null;
	Directory directory = null;
	Document document;

	/**
	 * 
	 * 方法: main
	 * 
	 * @param args
	 *            命令行参数 用于测试
	 */
	public static void main(String[] args)
	{
		ContextOut ct = new lineContextOut();
		File dir = new File(SOURCE_PATH);
		IndexBuild ib = new IndexBuild();
		List<File> list = new ArrayList<>();
		List<File> files = ib.getFilelist(dir, list);
		for (File file : files)
		{
			List<String> res = ct.getBaseString(file);
			ib.buildIndex(res);
		}
	}

	public List<File> getFilelist(File file, List<File> list)
	{
		if (!file.exists())
		{
			return null;
		}
		if (file.isDirectory())
		{
			for (File e : file.listFiles())
			{
				getFilelist(e, list);
			}
		} else
		{
			list.add(file);
		}
		return list;
	}

	/**
	 * 
	 * 方法: buildIndex
	 * 
	 * @param lib_text_path
	 *            含有库文件文件名、文件绝对路径和文件内文本的list
	 */
	public void buildIndex(List<String> lib_text_path)
	{
		String file_name = lib_text_path.get(0);
		String file_path = lib_text_path.get(1);
		String file_text = lib_text_path.get(2);
		try
		{
			analyzer = new IKAnalyzer();
			directory = FSDirectory.open(new File(INDEX_PATH));
			IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
			indexWriter = new IndexWriter(directory, iwConfig);
			document = new Document();
			document.add(new TextField("name", file_name, Store.YES));
			document.add(new TextField("path", file_path, Store.YES));
			document.add(new TextField("text", file_text, Store.YES));

			indexWriter.addDocument(document);
			indexWriter.commit();
			indexWriter.close();

		} catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * 方法: getINDEX_PATH
	 * 
	 * @return 返回硬盘上的索引位置
	 */

	public static String getINDEX_PATH()
	{
		return INDEX_PATH;
	}

}
