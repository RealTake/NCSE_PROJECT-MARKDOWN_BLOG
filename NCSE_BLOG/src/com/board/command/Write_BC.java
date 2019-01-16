package com.board.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class Write_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = new boardDTO();
		HttpSession session = req.getSession();
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String title = req.getParameter("title");
		String cate = req.getParameter("content");
		String id = (String)(session.getAttribute("user_id"));
		
		System.out.println("°á°ú: " + id);
		
		dto.setTitle(title);
		dto.setContent(cate);
		dto.setId(id);
		dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
		
		dao.write(dto, "PJ_board");
		
		req.setAttribute("view", dto);
	}
}
