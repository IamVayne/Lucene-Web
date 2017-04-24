/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月14日
 * 概述 : 进行搜索结果展示的servlet
 */
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.DealResult;

/**
 * 类: Search
 *
 */
public class Search extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理Post请求
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userinput = request.getParameter("question");
		System.out.println("这是用户输入：" + new String(userinput.getBytes("ISO-8859-1"), "UTF-8"));
		List<String> summaries = DealResult.getHighlightString(userinput);
		List<String> paths = DealResult.getPaths();
		HttpSession session = request.getSession();
		session.setAttribute("summaries", summaries);
		session.setAttribute("paths", paths);
		request.getRequestDispatcher("/result.jsp").forward(request, response);

	}

}
