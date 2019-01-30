package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.memberDAO;

public class Login_MC implements MCommand 
{
	public void excute(HttpServletRequest req, HttpServletResponse res)
	{
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		HttpSession session = req.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		System.out.println("ID: " + id + "\n" + "PW: " + pw);
		
		Boolean authority = dao.userCheck(id, pw);//Boolean 객체로 해준이유는 jsp 뷰단에서 object를 boolean으로 cast를 해야 오류가 나지않는다.
		
		System.out.println("Login_MC: " + authority);
		
		if(id != null && authority == true)
		{
			session.setAttribute("user_id", id);
		}
		req.setAttribute("autho", authority);
	}

}
