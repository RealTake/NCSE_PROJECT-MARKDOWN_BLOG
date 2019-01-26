package com.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.boardDAO;
import com.board.dto.boardDTO;

public class List_BC implements BCommand{

	public void excute(HttpServletRequest req, HttpServletResponse res){
		boardDAO dao = boardDAO.getInstance();
		String type = req.getParameter("type");
		ArrayList<boardDTO> dtos = dao.list(type);
		
//		for(int i = 0; i < dtos.size() ; i++)
//		{
//		boardDTO dto = dtos.get(i);
//		
//		System.out.println("������ȣ: " + dto.getbId());
//		System.out.println("����: " + dto.getTitle());
//		System.out.println("���̵�: " + dto.getId());
//		System.out.println("�Ⱦ��: " + dto.getDisLike());
//		System.out.println("���ƿ�: " + dto.getLike());
//		System.out.println("��¥: " + dto.getDate());
//		System.out.println("========================================\n");
//		}
		
		req.setAttribute("type", type);
		req.setAttribute("list", dtos);
	}
}
