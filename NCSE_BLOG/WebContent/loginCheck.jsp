<%@page import="com.DB.control.loginCheck_DB"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%!
final int MAX = 15, MIN = 4;
%>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	String login ="NewFile.html";
	loginCheck_DB check;
	
	if(id == null || pw == null)
	{
		out.print("<script> alert('���̵� �Ǵ� �н����带 �Է����ּ���.'); </script>");
		out.println("<script> location.href = '"+login+"'; </script>");
	}
	else if( ( id.length() > MAX || id.length() < MIN ) || ( pw.length() > MAX || pw.length() < MIN ))
	{
		out.println("<script> alert('�α��� ������ 3 ~ 14 �ȿ� ���ּ���'); </script>");
		out.println("<script> location.href = '"+login+"'; </script>");
	}
	else
	{
		check = new loginCheck_DB(id, pw);
		
		if(check.getValidity() == true)
		{
			out.print("<script> alert('����'); </script>");
			%>
			
			<jsp:forward page="setSession.jsp">
    			<jsp:param name="id" value="<%= id %>" />
			</jsp:forward>
			
			<%
		}
		else
			response.sendRedirect(login);
	}
		
%>




</body>
</html>