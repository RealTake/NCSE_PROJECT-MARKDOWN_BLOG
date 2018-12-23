<%@page import="com.dto.memberDTO"%>
<%@page import="com.DB.control.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

ID: ${imp.id}</br>
PW: ${imp.pw}</br>
NICK_NAME: ${imp.nick}</br>
NAME: ${imp.name }</br>
SEX: ${imp.sex }</br>
EMAIL: ${imp.email }</br>
PHONE: ${imp.phone }</br>
권한: ${imp.user_authority }</br>
SNS: ${imp.platform_link }</br>
자기소개: ${imp.self_imp }</br>
</body>
</html>