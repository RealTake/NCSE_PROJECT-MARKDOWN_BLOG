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
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String id = request.getParameter("id");
	findAccount f = new findAccount(name, email, id, "id");// �� ������ �Ű������� �� id,pwd�ϰ�
%>
</body>
</html>