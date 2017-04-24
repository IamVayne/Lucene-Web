/**
 * 作者 : 黄斌
 * 创建时间 : 2017年4月15日
 * 概述 : 详细的某个搜索结果展示的servlet
 */
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MShow.util.Answer;

/**
 * 类: AnswerShow
 *
 */
public class AnswerShow extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 835704788988683217L;

	/**
	 * 处理Get请求
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String question = (String) request.getParameter("question");
		@SuppressWarnings("unchecked")
		List<String> paths = (List<String>) session.getAttribute("paths");
		Answer answer = new Answer();
		if (question.equals("0"))
		{
			String q = answer.getQuestion(paths.get(0));
			String a = answer.getAns(paths.get(0));
			request.setAttribute("q", q);
			request.setAttribute("a", a);
		} else if (question.equals("1"))
		{
			String q = answer.getQuestion(paths.get(1));
			String a = answer.getAns(paths.get(1));
			request.setAttribute("q", q);
			request.setAttribute("a", a);
		} else if (question.equals("2"))
		{
			String q = answer.getQuestion(paths.get(2));
			String a = answer.getAns(paths.get(2));
			request.setAttribute("q", q);
			request.setAttribute("a", a);
		} else if (question.equals("3"))
		{
			String q = answer.getQuestion(paths.get(3));
			String a = answer.getAns(paths.get(3));
			request.setAttribute("q", q);
			request.setAttribute("a", a);
		} else if (question.equals("4"))
		{
			String q = answer.getQuestion(paths.get(4));
			String a = answer.getAns(paths.get(4));
			request.setAttribute("q", q);
			request.setAttribute("a", a);
		}

		// response.sendRedirect("../answer.jsp");
		request.getRequestDispatcher("/answer.jsp").forward(request, response);

	}
}
