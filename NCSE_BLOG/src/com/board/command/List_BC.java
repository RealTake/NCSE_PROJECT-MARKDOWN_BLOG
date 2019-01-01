package com.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dto.boardDTO;
import com.borad.dao.boardDAO;

public class List_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		ArrayList<boardDTO> dtos = dao.list("PJ_board");
		
		for(int i = 0; i < dtos.size() ; i++)
		{
		boardDTO dto = dtos.get(i);
		
		System.out.println("고유번호: " + dto.getbId());
		System.out.println("제목: " + dto.getTitle());
		System.out.println("아이디: " + dto.getId());
		System.out.println("싫어요: " + dto.getDisLike());
		System.out.println("좋아요: " + dto.getLike());
		System.out.println("날짜: " + dto.getDate());
		System.out.println("========================================\n");
		}
		
		req.setAttribute("list", dtos);
	}
}
