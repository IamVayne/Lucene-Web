/**
 * 作者 : vayne
 * 创建时间 : 2017年4月12日
 * 概述 :
 */
package edu.vayne.window;

import java.util.Map;

import edu.vayne.cfg.DefaultConfig;
import edu.vayne.rep.Rep;

/**
 * 类: test
 *
 */
public class test
{
	public static void main(String[] args)
	{
//		Fly fly = new Fly(DefaultConfig.getInstance());
//		List<String> list = fly.getFlywords();
		Rep rep = new Rep(DefaultConfig.getInstance());
		Map<String, String> map = rep.getMaps();
		System.out.println(map);
//		System.out.println(list);
	}
}
