package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.boardDAO;
import com.board.dto.boardDTO;

public class Delete_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = new boardDTO();
		
		dto.setbId(req.getParameter("bid"));
		dto.setType(req.getParameter("type"));
		
		if( req.getSession().getAttribute("user_id") != null)
			dao.delete(dto, (String)(req.getSession().getAttribute("user_id")));
	}
}
