package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.memberDAO;
import com.member.dto.memberDTO;

public class Private_MC implements MCommand 
{
	public void excute(HttpServletRequest req, HttpServletResponse res)
	{
		memberDAO dao = memberDAO.getInstance();
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		System.out.println("!!!!!!!!!!!!!");
		req.setAttribute("imp", dao.getPersonal_imp(id, pw));
	}

}