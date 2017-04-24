/**
 * 作者 : 黄斌
 * 创建时间 : 2017年3月27日13:57:30
 * 概述  :  与索引构建层的接口
 */
package com.MBackData.window;

import java.io.File;
import java.util.List;

/**
 * 
 * 类: ContextOut 与索引构建层交互接口
 */
public interface ContextOut
{
	/**
	 * 
	 * @param file
	 * @return 返回经过处理之后file的名字、路径和内容组成的list
	 */
	public List<String> getBaseString(File file);

}
