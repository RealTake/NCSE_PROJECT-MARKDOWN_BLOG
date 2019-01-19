package com.main.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.command.*;
import com.member.command.*;


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
		MCommand M = null;
		BCommand B = null;
		
		String uri = req.getRequestURI();
		System.out.println("��ûuri: " + uri);
		String contextPath = req.getContextPath();
		System.out.println("���ؽ�Ʈ �н�: " + contextPath);
		String com = uri.substring(contextPath.length());
		System.out.println("��û Ŀ�ǵ�: " + com);
		
		//��û Ŀ�ǵ�� �ο�
		switch(com)
		{
		
		case "/login.do" :// ���߿� post������ �����ϰ� ����
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			M = new Login_MC();
			M.excute(req, res);
			viewPage = "setSession.jsp";
			break;
			
		case "/join.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			M = new Join_MC();
			M.excute(req, res);
			viewPage = "NewFile.html";
			break;
			
		case "/board.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new List_BC();
			B.excute(req, res);
			viewPage = "list.jsp";
			break;
			
		case "/content_view.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new View_BC();
			B.excute(req, res);
			viewPage = "view.jsp";
			break;
		
		case "/write.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Write_BC();
			B.excute(req, res);
			viewPage = "view.jsp";
			break;
			
		case "/private.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			M = new Private_MC();
			M.excute(req, res);
			viewPage = "viewPrivate.jsp";
			break;
			
		case "/find.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Find_BC();
			B.excute(req, res);
			viewPage = "list.jsp";
			break;
			
		case "/comment.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Comments_BC();
			B.excute(req, res);
			viewPage = "view.jsp";
			break;
			
		}
		
		//���������� ������ ������� �̵�
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
