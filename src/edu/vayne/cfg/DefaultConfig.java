/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月12日
 * 概述 : 配置类的一个默认实现
 */
package edu.vayne.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

/**
 * 类: DefaultConfig
 *
 */
public class DefaultConfig implements Configuration
{

	private static final String FLY_NAME = "fly_dic";

	private static final String REP_NAME = "rep_dic";

	private static final String SOURCE_NAME = "core.xml";

	private Properties props;

	public static DefaultConfig getInstance()
	{
		return new DefaultConfig();
	}
	/**
	 * 默认构造器
	 */

	private DefaultConfig()
	{
		props = new Properties();

		InputStream input = this.getClass().getClassLoader().getResourceAsStream(SOURCE_NAME);

		if (input != null)
		{
			try
			{
				props.loadFromXML(input);
			} catch (InvalidPropertiesFormatException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Properties getProps()
	{
		return props;
	}

	public void setProps(Properties props)
	{
		this.props = props;
	}

	public static String getFlyName()
	{
		return FLY_NAME;
	}

	public static String getRepName()
	{
		return REP_NAME;
	}

	public static String getSourceName()
	{
		return SOURCE_NAME;
	}

	/**
	 * 从xml文件得到词典文件列表
	 */
	@Override
	public List<String> getFlyDictionaries()
	{
		// TODO Auto-generated method stub
		List<String> fly_dics = new ArrayList<>(2);

		String temp = props.getProperty(FLY_NAME);
		System.out.println(temp);
		if (temp != null)
		{
			String[] file_paths = temp.split(";");
			if (file_paths != null)
			{
				for (String file_path : file_paths)
				{
					if (file_path != null && !"".equals(file_path.trim()))
					{
						fly_dics.add(file_path);
					}
				}
			}
		}
		
		System.out.println(fly_dics);

		return fly_dics;
	}

	/**
	 * 从xml文件得到词典文件列表
	 */
	@Override
	public List<String> getWordMapDictionaries()
	{
		// TODO Auto-generated method stub
		List<String> rep_dics = new ArrayList<>(2);

		String temp = props.getProperty(REP_NAME);
		if (temp != null)
		{
			String[] file_paths = temp.split(";");
			if (file_paths != null)
			{
				for (String file_path : file_paths)
				{
					if (file_path != null && !"".equals(file_path.trim()))
					{
						rep_dics.add(file_path);
					}
				}
			}
		}

		return rep_dics;
	}

}
