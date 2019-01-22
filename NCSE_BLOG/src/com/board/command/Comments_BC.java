package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borad.dao.boardDAO;

public class Comments_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res)
	{
		String id = (String)(req.getSession().getAttribute("user_id"));
		String bid = req.getParameter("bid");
		String comment = req.getParameter("comment");
		
		boardDAO dao = boardDAO.getInstance();
		dao.setComments(bid, id, comment);
		
		
	}
}