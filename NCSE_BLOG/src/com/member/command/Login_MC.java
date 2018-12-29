package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.memberDAO;

public class Login_MC implements MCommand 
{
	public void excute(HttpServletRequest req, HttpServletResponse res)
	{
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		memberDAO dao = memberDAO.getInstance();
		
		System.out.println("ID: " + id + "\n" + "PW: " + pw);
		
		Boolean authority = dao.userCheck(id, pw);//Boolean ��ü�� ���������� jsp ��ܿ��� object�� boolean���� cast�� �ؾ� ������ �����ʴ´�.
		
		System.out.println("Login_BC: " + authority);
		
		req.setAttribute("authority", authority);
	}

}
