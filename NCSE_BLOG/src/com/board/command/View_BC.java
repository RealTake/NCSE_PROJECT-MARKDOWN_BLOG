package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class View_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		String bid = req.getParameter("bId");
		String cate = req.getParameter("cate");
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = dao.viewContent(bid, cate);
		
		req.setAttribute("view", dto);
	
		
		
	}
}
