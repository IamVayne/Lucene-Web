/**
 * 作者 : vayne
 * 创建时间 : 2017年4月12日
 * 概述 :
 */
package edu.vayne.window;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.vayne.cfg.Configuration;
import edu.vayne.cfg.DefaultConfig;
import edu.vayne.fly.Fly;
import edu.vayne.rep.Rep;

/**
 * 类: SLBFilter
 *
 */
public class SLBFilter
{
	/**
	 * 
	 * 方法: main
	 * 
	 * @param args
	 *            测试主方法
	 */
	public static void main(String[] args)
	{
		String in = "what is your name,nerd and txt?";
		new SLBFilter();
		System.out.println(SLBFilter.remAndrepWords(in));

	}

	private static Configuration configuration = DefaultConfig.getInstance();

	/**
	 * 
	 * 方法: removeWord
	 * 
	 * @param input
	 *            用户输入
	 * @return 处理之后的字符串 对用户输入字符串进行词语移除操作
	 */
	public static String removeWord(String input)
	{
		Fly fly = new Fly(configuration);
		List<String> words = fly.getFlywords();

		for (String word : words)
		{
			input = input.replace(word, "");
		}

		return input;
	}

	/**
	 * 
	 * 方法: repWord
	 * 
	 * @param input
	 *            用户输入
	 * @return 处理之后的字符串 对用户输入字符串进行替换操作
	 */
	public static String repWord(String input)
	{
		Rep rep = new Rep(configuration);
		Map<String, String> map = rep.getMaps();
		Set<String> keySet = map.keySet();
		for (String key : keySet)
		{
			input = input.replace(key, map.get(key));
		}

		return input;
	}

	/**
	 * 
	 * 方法: remAndrepWords
	 * 
	 * @param input
	 *            用户输入
	 * @return 处理之后的字符串 对用户输入字符串进行先移除再替换的操作
	 */

	public static String remAndrepWords(String input)
	{
		input = removeWord(input);
		input = repWord(input);

		return input;

	}

}
