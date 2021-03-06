/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月14日
 * 概述 :用于将搜索结果输出到文件
 */
package com.ui.window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Document;

import com.MBackData.util.EncodingDetect;
import com.MFSearch.search.SearchCore;

/**
 * 类: INput
 *
 */

//
//
//
// 老师，我测试的时候，待搜索的文本放在D：\\saomiao 这个文件夹下了，您可以在程序60行按您的需求改动
// 搜索结果放在 D：\\huizong.txt文件下，路径您可以在61行处改动，这个文件会自动生成，不需要提前建立。
//
//
//
//

public class INput
{

	/**
	 * 
	 * 方法: getStringFromTxt
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 *             从txt文件中得到字符串
	 */
	public static String getStringFromTxt(File file) throws IOException
	{
		StringBuilder str = new StringBuilder();
		String path = file.getAbsolutePath();
		InputStreamReader read = null;
		System.out.println(path);
		if (EncodingDetect.getJavaEncode(path).equals("UTF-8"))
		{
			read = new InputStreamReader(new FileInputStream(file), "UTF-8");
		} else if (EncodingDetect.getJavaEncode(path).equals("GB2312"))
		{
			read = new InputStreamReader(new FileInputStream(file), "GB2312");
		} else if (EncodingDetect.getJavaEncode(path).equals("GBK"))
		{
			read = new InputStreamReader(new FileInputStream(file), "GBK");
		}else {
			read = new InputStreamReader(new FileInputStream(file), "UTF-8");
		}

		BufferedReader bufferedReader = new BufferedReader(read);
		String txt = "";
		while ((txt = bufferedReader.readLine()) != null)
		{
			str.append(txt.toString().trim());
		}
		bufferedReader.close();
		return str.toString();
	}

	/**
	 * 
	 * 方法: main
	 * 
	 * @param args
	 * @throws Exception
	 *             将文本中的问题进行搜索，搜索结果输出在指定文件中
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception
	{
		String saomiao_dir = "D:\\saomiao";
		String jieguo_huizong = "D:\\you_OCR_result.txt";
		File jie = new File(jieguo_huizong);

		PrintStream printStream = new PrintStream(jie);
		File file = new File(saomiao_dir);
		List<File> r = new ArrayList<>();
		getList(file,r);
		String s1 = "文件输入：   ";
		String s2 = "结果展示：   ";
		int count = 1;
		for (File e : r)
		{
			
			try
			{
				String userinput = getStringFromTxt(e);
				Map<Document, Float> result = SearchCore.search2(userinput);

				printStream.println();
				printStream.println();
				printStream.println("**********第"+count+"个*******");
				printStream.println();
				printStream.println();
				printStream.println(s1);
				printStream.println(userinput);
				printStream.println(s2);
				int s = 1;
				for (Entry<Document, Float> entry : result.entrySet())
				{
					
					String id = entry.getKey().get("name");
					String text = entry.getKey().get("text").replace("$", "");
					Float score = entry.getValue();
					printStream.println("-------------------------------------------------------------");
					printStream.println("--------第"+count+"个题目的第"+s+"个搜索结果------------");
					s++;
					printStream.println("-------------------------------------------------------------");
					printStream.println("id : " + id);
					printStream.println("内容：  ");
					printStream.println(text);
					printStream.println("评分： " + score);
				}
				s=1;
				printStream.println("******************************************************************");
				printStream.println("**********输入题目的分割线*************");
				printStream.println("******************************************************************");

			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			} finally
			{

			}
			System.err.println("第"+count+"个搜索结束~");
			count++;
		}

	}

	/**
	 * 
	 * 方法: getList
	 * 
	 * @param file
	 * @return 目录下文件的列表
	 */

	public static void getList(File file,List<File> list)
	{
		if (file.listFiles().length == 0)
		{
			System.err.println("目录为空！！！");
		}
		for (File e : file.listFiles())
		{
			if(e.isDirectory())
			{
				getList(e,list);
			}else 
			{
				list.add(e);
			}
		}
	}
}
