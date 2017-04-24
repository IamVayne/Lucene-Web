/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月12日
 * 概述 : 词语剔除类
 */
package edu.vayne.fly;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.vayne.cfg.Configuration;

/**
 * 类: Fly
 *
 */
public class Fly
{
	private Configuration config;

	public Fly(Configuration config)
	{
		super();
		this.config = config;
	}

	/**
	 * 
	 * 方法: getFlywords
	 * 
	 * @return 词语列表 从加载的词典得到词语列表
	 */
	public List<String> getFlywords()
	{
		List<String> fly_dics = config.getFlyDictionaries();
		List<String> fly_words = new ArrayList<>();
		if (fly_dics != null)
		{
			InputStream is = null;
			for (String fly_dic : fly_dics)
			{
				System.out.println("加载剔除词典 ：" + fly_dic);
				is = this.getClass().getClassLoader().getResourceAsStream(fly_dic);
				if (is == null)
				{
					continue;
				}
				try
				{
					BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
					String word = null;
					do
					{
						word = buffer.readLine();
						if (word != null && !"".equals(word.trim()))
						{
							fly_words.add(word);
						}

					} while (word != null);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}

		return fly_words;

	}

}
