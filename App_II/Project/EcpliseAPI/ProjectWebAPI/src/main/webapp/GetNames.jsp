<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
		<title>Insert title here</title>
		<style>
		td {color:blue; padding:10px;}
		</style>
	</head>
<body style="font-family:Arial">
<%@ page import="java.util.ArrayList" %>
<%
	if(session.getAttribute("Names") == null)
	{
	session.setAttribute("Names", new ArrayList<String>());
	}
	ArrayList<String> names = (ArrayList<String>)session.getAttribute("Names");
	if(request.getParameter("btnOK") != null)
	{
	String firstName = request.getParameter("txtFirstName");
	String lastName = request.getParameter("txtLastName");
	if(firstName != null && !firstName.equals("")
	&& lastName != null && !lastName.equals(""))
	{
	names.add(firstName + " " + lastName);
	}
	}
%>
<form method="GET" action="GetNames.jsp" >
Fornavn: &nbsp;<input type="text" name="txtFirstName" />&nbsp;
Efternavn: &nbsp;<input type="text" name="txtLastName" />&nbsp;
<input type="submit" name="btnOK" value="OK" style="width:80px" />&nbsp;
<input type="reset" name="btnCancel" value="Cancel" style="width:80px"/>
</form>
<br />
Udskriv alle navne:
<table border="1">
<%
for(int i = 0; i < names.size(); i++ )
{%>
<tr>
<td><%=names.get(i)%></td>
</tr>
<%}%>
</table>
</body>
</html>