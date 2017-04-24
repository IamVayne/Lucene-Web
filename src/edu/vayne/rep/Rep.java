/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月12日
 * 概述 : 替换词语类
 */
package edu.vayne.rep;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import edu.vayne.cfg.Configuration;

/**
 * 类: Rep
 *
 */
public class Rep
{
	private Configuration configuration;
	
	public Map<String, String> map ;
	
	
	public Rep(Configuration configuration)
	{
		super();
		this.configuration = configuration;
	}


	/**
	 * 
	 * 方法: getMaps
	 * @return map映射
	 * 从词典文件中得到替换对
	 */
	public Map<String,String> getMaps()
	{
		List<String> rep_dics = configuration.getWordMapDictionaries();
		if (rep_dics!=null)
		{
			InputStream is = null;
			Properties properties = new Properties();
			for(String rep_dic : rep_dics)
			{
				System.out.println("加载替换词典 ："+rep_dic);
				is = this.getClass().getClassLoader().getResourceAsStream(rep_dic);
				try
				{
					properties.load(is);
					Set<Object> keyset = properties.keySet();
					for(Object e : keyset)
					{
						if (map==null)
						{
							map = new HashMap<>();
						}
						map.put((String)e, properties.getProperty((String)e));
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return map;
	}

}
