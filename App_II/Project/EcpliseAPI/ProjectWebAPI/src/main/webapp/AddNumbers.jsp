<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%
	if(session.getAttribute("Numbers") == null)
	{
		session.setAttribute("Numbers", new ArrayList<String>());
	}
	
	ArrayList<String> numbers = (ArrayList<String>) session.getAttribute("Numbers");
	
	if (request.getParameter("btnOK") != null)
	{
		String var1 = request.getParameter("txtVar1");
		String var2 = request.getParameter("txtVar2");
		if (var1 != null && !var1.equals("") && var2 != null && !var2.equals(""))
	}
	
%>
</body>
</html>