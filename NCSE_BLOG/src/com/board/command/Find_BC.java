package com.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class Find_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		
		boardDAO dao = boardDAO.getInstance();
		String searched = req.getParameter("find");
		ArrayList<boardDTO> dtos = dao.find(searched);

		req.setAttribute("list", dtos);
		
	}
}
