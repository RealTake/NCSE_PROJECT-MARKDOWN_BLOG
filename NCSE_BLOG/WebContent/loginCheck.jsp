<%@page import="com.member.dao.loginCheck_DB"%>
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
	
	if(id == null || id.equals("") || pw == null || pw.equals(""))
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
			
		%>
			<jsp:forward page="login.do"></jsp:forward>
		<%
	}
%>




</body>
</html>