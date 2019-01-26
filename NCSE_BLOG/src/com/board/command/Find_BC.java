package com.board.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.boardDAO;

public class Find_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		String searched = req.getParameter("find");
		System.out.println(searched);
		
		req.setAttribute("PJ", dao.find(searched,"PJ_board"));
		req.setAttribute("ST", dao.find(searched,"ST_board"));
		req.setAttribute("FR", dao.find(searched,"FR_board"));
		req.setAttribute("ITnews", dao.find(searched,"ITnews_board"));
		
	}
}
