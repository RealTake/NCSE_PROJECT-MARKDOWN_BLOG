<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	String login ="login";
	
	if(id == null || id.equals("") || pw == null || pw.equals(""))
	{
		out.print("<script> alert('아이디 또는 패스워드를 입력해주세요.'); </script>");
		out.print("<script> location.href = '"+login+"'; </script>");
	}
	else if( ( id.length() > MAX || id.length() < MIN ) || ( pw.length() > MAX || pw.length() < MIN ))
	{
		out.print("<script> alert('로그인 정보를 3 ~ 14 안에 써주세요'); </script>");
		out.print("<script> location.href = '"+login+"'; </script>");
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