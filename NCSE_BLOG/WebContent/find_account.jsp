<%@page import="com.member.dao.findAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String mode = request.getParameter("mode");
	
	System.out.print("내용: " + id);
	
	if((name != null && !name.equals("")) && (email != null && !email.equals("")) && (id != null && !id.equals("")) && (pw != null && !pw.equals("")))
	{
		findAccount f = new findAccount(name, email, id, mode);// 맨 마지막 매개변수는 꼭 id,pwd일것
		out.print(f.inpuiryAccount());
	}
	else
		out.print("<script>alert('내용을 입력하세요')</script>");
%>
</body>
</html>