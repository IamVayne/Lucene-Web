<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ans =(String)request.getAttribute("a");
	if(ans.trim()==null)
	{
		ans = "对不起，该题目没有答案";
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div align="center">
<body>
	<font color="red">题目：</font>
	<%=((String)request.getAttribute("q")).replace("$","")%>
	
	<br>
	<font color="red">答案:</font><br>
	<%=ans.replace("$","")%>
</body>
</div>
</html>