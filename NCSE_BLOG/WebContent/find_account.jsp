<%@page import="com.DB.control.findAccount"%>
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
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	findAccount f = new findAccount(name, email, id, pw, "id");// �� ������ �Ű������� �� id,pwd�ϰ�
%>
</body>
</html>