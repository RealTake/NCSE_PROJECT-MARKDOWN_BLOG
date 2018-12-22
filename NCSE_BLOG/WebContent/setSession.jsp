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
	String s_id = session.getAttribute("user_id").toString();
	String id = request.getParameter("id");
	
	if(s_id != null)
		response.sendRedirect("Main.html");
	else if(s_id == null)
	{
		if(id != null)
		{
			session.setAttribute("user_id", id);
			response.sendRedirect("Main.html");
	
		}
	}
%>
</body>
</html>