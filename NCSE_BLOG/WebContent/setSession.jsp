<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>set_Session</title>
</head>
<body>
<%
	String id = request.getParameter("id");

	session.setAttribute("user_id", id);
	response.sendRedirect("Main.html");
%>
</body>
</html>