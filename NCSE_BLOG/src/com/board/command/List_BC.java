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
		
		System.out.println("������ȣ: " + dto.getbId());
		System.out.println("����: " + dto.getTitle());
		System.out.println("���̵�: " + dto.getId());
		System.out.println("�Ⱦ��: " + dto.getDisLike());
		System.out.println("���ƿ�: " + dto.getLike());
		System.out.println("��¥: " + dto.getDate());
		System.out.println("========================================\n");
		}
		
		req.setAttribute("list", dtos);
	}
}
