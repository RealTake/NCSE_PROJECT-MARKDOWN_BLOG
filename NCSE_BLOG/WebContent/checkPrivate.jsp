<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
test<br/>
<%
	out.print(session.getAttribute("user_id"));
%>
<form method="post" action="private.do">
<input type="text" name="id" value="${user_id}" >
<input type="password" name="pw" >
<input type="submit" value="전송">
</form>

</body>
</html>