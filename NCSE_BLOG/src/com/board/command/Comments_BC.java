package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class Comments_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res)
	{
		boardDAO dao = boardDAO.getInstance();
		
		
	}
}