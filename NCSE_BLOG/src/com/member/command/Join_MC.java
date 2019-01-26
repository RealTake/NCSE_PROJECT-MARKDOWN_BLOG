package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.memberDAO;
import com.member.dto.memberDTO;

public class Join_MC implements MCommand {
	
	public void excute(HttpServletRequest req, HttpServletResponse res )
	{
		
		final int MAX = 15, MIN = 4;
		
		memberDAO dao = memberDAO.getInstance();
		memberDTO dto = new memberDTO();
		
		Boolean check;
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String phone = req.getParameter("ph");
		String platform_link = req.getParameter("platform_link");
		String self_imp = req.getParameter("self_imp");

		if(id != null && pw != null && name != null && sex != null && nick != null && email != null && phone != null && platform_link != null && self_imp != null)
		{
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setSex(sex);
			dto.setNick(nick);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setPlatform_link(platform_link);
			dto.setSelf_imp(self_imp);
			
			check = dao.insertDB(dto);
			
			req.setAttribute("check", check);
			
			if(check)
			{
				System.out.println("삽입 프로세스성공");
			}
			else
			{
				System.out.println("삽입 프로세스 실패");
			}
		}
	}

}
