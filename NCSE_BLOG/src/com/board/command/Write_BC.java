package com.board.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.boardDAO;
import com.board.dto.boardDTO;

public class Write_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = new boardDTO();
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String con = req.getParameter("content");
		String type = req.getParameter("type");
		String id;
		System.out.println("제목: " + title.length());
		if((session.getAttribute("user_id") != null) && (title != null && !title.equals("")))
		{
			id = (String)(session.getAttribute("user_id"));
			
			System.out.println("결과: " + id);
			
			dto.setType(type);
			dto.setTitle(title);
			dto.setContent(con);
			dto.setId(id);
			dto.setDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).toString());
			
			dao.write(dto);
		}
		req.setAttribute("type", type);
	}
}
