<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>고유번호</td>
			<td>제목</td>
			<td>아이디</td>
			<td>날짜</td>
			<td>좋아요</td>
			<td>싫어요</td>
		</tr>
		<c:forEach items="${requestScope.list}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.title}</td>
			<td>
				<a>${dto.id}</a></td>
			<td>${dto.date}</td>
			<td>${dto.like}</td>
			<td>${dto.disLike}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view.do">글작성</a> </td>
		</tr>
	</table>

</body>
</html>