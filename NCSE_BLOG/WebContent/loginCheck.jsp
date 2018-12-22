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
	boolean authority = (Boolean)(request.getAttribute("authority"));

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");		
	String login ="NewFile.html";
		
	if(id == null || id.equals("") || pw == null || pw.equals(""))
	{
		out.print("<script> alert('아이디 또는 패스워드를 입력해주세요.'); </script>");
		out.println("<script> location.href = '"+login+"'; </script>");
	}
	else if( ( id.length() > MAX || id.length() < MIN ) || ( pw.length() > MAX || pw.length() < MIN ))
	{
		out.println("<script> alert('로그인 정보를 3 ~ 14 안에 써주세요'); </script>");
		out.println("<script> location.href = '"+login+"'; </script>");
	}
	else
	{
		if(authority == true)
		{
			%>
				<jsp:forward page="setSession.jsp"></jsp:forward>
			<%
		}
		else
		{
			out.println("<script>alert('승인 되지않은 계정이거나 그런 계정이 존제하지 않습니다');</script>");
			out.print("<script> location.href = 'NewFile.html'; </script>");
		}
		out.println("이동성공");
		out.println(authority);
	}
%>




</body>
</html>