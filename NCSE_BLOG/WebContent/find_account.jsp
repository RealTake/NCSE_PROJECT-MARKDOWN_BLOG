<%@page import="com.member.dao.findAccount"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	out.print("hi");
	request.setCharacterEncoding("EUC-KR");
	
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String mode = request.getParameter("mode");
	
	out.print(name+" "+email);
	
	findAccount f = new findAccount(name, email, id, pw, mode);// 맨 마지막 매개변수는 꼭 id,pwd일것
%>
</body>
</html>