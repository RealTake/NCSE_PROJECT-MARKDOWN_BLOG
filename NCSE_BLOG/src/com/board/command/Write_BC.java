package com.board.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class Write_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = new boardDTO();
		
//		String cate = req.getParameter("cate");
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		
//		dto.setTitle(title);
//		dto.setContent(content);
		dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
		
		dao.write(dto, "PJ_board");
		
		req.setAttribute("view", dto);
	}
}
