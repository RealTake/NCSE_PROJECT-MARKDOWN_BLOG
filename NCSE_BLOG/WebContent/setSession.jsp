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
	boolean authority = (Boolean)(request.getAttribute("authority"));
	
	if(session.getAttribute("user_id") != null)
		response.sendRedirect("Main.html");
	else
	{
		if(id != null && authority == true)
		{
			session.setAttribute("user_id", id);
			response.sendRedirect("Main.html");
	
		}
		else
		{
			out.println("<script>alert('���� �������� �����̰ų� �׷� ������ �������� �ʽ��ϴ�');</script>");
			out.println("<script> location.href = 'NewFile.html'; </script>");
		}
	}
%>
</body>
</html>