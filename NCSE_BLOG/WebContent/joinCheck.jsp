<%@ page language="java" contentType="text/html; charset=EUC-KR" 
	import = "com.DB.control.joinDB"
 %>
 <jsp:useBean id="member" class="com.dto.memberDTO" scope="page"/>
 <jsp:setProperty name="member" property="id" />
 <jsp:setProperty name="member" property="pw" />
 <jsp:setProperty name="member" property="pwCheck" />
 <jsp:setProperty name="member" property="name" />
 <jsp:setProperty name="member" property="nick" />
 <jsp:setProperty name="member" property="email" />
 <jsp:setProperty name="member" property="ph" />
 <jsp:setProperty name="member" property="platform_link" />
 <jsp:setProperty name="member" property="self_imp" />
 <jsp:setProperty name="member" property="sex" />
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	 final int MAX = 15, MIN = 4;
	 String id = request.getParameter("id");
	 String pw = request.getParameter("pw");
	 String pwCheck = request.getParameter("pwCheck");
	 String name = request.getParameter("name");
	 String nick = request.getParameter("nick");
	 String email = request.getParameter("email");
	 String ph = request.getParameter("ph");
	 //String address = request.getParameter("address");
	 String platform_link = request.getParameter("platform_link");
	 String self_imp = request.getParameter("self_imp");
	 String sex  = request.getParameter("sex");
	 String joind = "����uri";
	 joinDB join;
	 
	 out.print("<script>alert('"+name+"');</script>");
	 if(id == null || pw == null)
	 {
		 out.print("<script>alert('���̵�, �н����带 �Է����ּ���.');</script>");
		 out.print("<script> location.href = '"+joind+"'; </script>");
	 }
	 else if( ( id.length() > MAX || id.length() < MIN ) || ( pw.length() > MAX || pw.length() < MIN ))
	 	out.print("<script>alert('8~15�ȿ��� �Է����ּ���');</script>");
	 else if(pw.equals(pwCheck))
	 {
		join = new joinDB(id, pw, name, nick, sex, email, ph, platform_link, self_imp);
		
		if(join.getValidity() == true)
		{
			 out.print("<script>alert('ȸ�������� �Ϸ� �Ǿ����ϴ�.');</script>");
			 out.print("<script> location.href = 'join.html'; </script>");
		}
	 }	
	
	 %>
</body>
</html>