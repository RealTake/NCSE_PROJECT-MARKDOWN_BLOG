package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.boardDAO;
import com.board.dto.boardDTO;

public class Modify_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		boardDTO dto = new boardDTO();
		Object id =req.getSession().getAttribute("user_id");
		if(id != null)
		{
			dto.setbId(req.getParameter("bid"));
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			dto.setType(req.getParameter("type"));
			dto.setId((String)id);
			
			dao.modify(dto);
		}
	
	}
}
