<%@ page language="java" contentType="text/html; charset=EUC-KR" 
	import = "com.DB.control.joinDB"
 %>
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
	 String joind = "가입uri";
	 joinDB join;
	 
	 out.print("<script>alert('"+name+"');</script>");
	 if(id == null || pw == null)
	 {
		 out.print("<script>alert('아이디, 패스워드를 입력해주세요.');</script>");
		 out.print("<script> location.href = '"+joind+"'; </script>");
	 }
	 else if( ( id.length() > MAX || id.length() < MIN ) || ( pw.length() > MAX || pw.length() < MIN ))
	 	out.print("<script>alert('8~15안에서 입력해주세요');</script>");
	 else if(pw.equals(pwCheck))
	 {
		join = new joinDB(id, pw, name, nick, sex, email, ph, platform_link, self_imp);
		
		if(join.getValidity() == true)
		{
			 out.print("<script>alert('회원가입이 완료 되었습니다.');</script>");
			 out.print("<script> location.href = 'login.html'; </script>");
		}
	 }	
	
	 %>
</body>
</html>