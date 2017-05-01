<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int count = 0;
	List<String> list = (List<String>) session.getAttribute("summaries");
	int i = 0;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<br>
<br>
<br>
<h5 align="center">搜索结果</h5>
<body>
	<table border="1" align="center">
		<tr>
			<th>题号</th>
			<th>题目摘要</th>
			<th>答案链接</th>
		</tr>
		<%for( i = 0;i<list.size();i++)
		{
			%>
		<tr>
			<td><%=i+1 %></td>
			<td><%=list.get(i).replace("$","")%></td>
			<td><a href="AnswerShow?question=<%=i %> ">查看答案</a></td>
		</tr>
		<%} %>

	</table>

</body>
</html>