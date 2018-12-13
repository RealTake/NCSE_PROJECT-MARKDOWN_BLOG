package com.main.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.command.BCommand;

/**
 * Servlet implementation class mainControl
 */
@WebServlet("*.do")
public class mainControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//actionDo(request, response);
		System.out.println("ihihih");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest req, HttpServletResponse res){
		res.setCharacterEncoding("EUC-KR");
		
		//String viewPage = null;
		//BCommand command = null;
		
		String uri = req.getRequestURI();
		System.out.println("요청uri: " + uri);
		String contextPath = res.getContentType();
		System.out.println("컨텍스트 패스: " + contextPath);
		String com = uri.substring(contextPath.length());
		System.out.println("요청 커맨드: " + com);
		
//		switch(com)
//		{
//		
//		case "/board.do" :
//			System.out.println("포론트 컨트롤러 작동");
//			command = new BoardCommand();
//			command.excute(req, res);
//			viewPage = "/board";
//			break;
//			
//		}
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		//dispatcher.forward(req, res);
		
	}
	
}
