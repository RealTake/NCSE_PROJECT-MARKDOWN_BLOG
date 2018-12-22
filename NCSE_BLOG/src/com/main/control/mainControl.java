package com.main.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.command.*;


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
		actionDo(request, response, "get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response, "post");
	}

	private void actionDo(HttpServletRequest req, HttpServletResponse res, String method){
		res.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = req.getRequestURI();
		System.out.println("요청uri: " + uri);
		String contextPath = req.getContextPath();
		System.out.println("컨텍스트 패스: " + contextPath);
		String com = uri.substring(contextPath.length());
		System.out.println("요청 커맨드: " + com);
		
		switch(com)
		{
		
		case "/login.do" :// 나중에 post에서만 동작하게 하자
			System.out.println(com + " 작동");
			command = new Login_BC();
			command.excute(req, res);
			viewPage = "loginCheck.jsp";
			break;
			
		case "/join.do" :
			System.out.println(com + " 작동");
			command = new Join_BC();
			command.excute(req, res);
			viewPage = "NewFile.html";
			break;
			
		case "/board.do" :
			System.out.println(com + " 작동");
			command = new Board_BC();
			command.excute(req, res);
			viewPage = "/board";
			break;
		
		case "/private.do" :
			System.out.println(com + " 작동");
			command = new private_BC();
			command.excute(req, res);
			viewPage = "viewPrivate.jsp";
			break;
			
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
