package com.main.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

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
	
		String viewPage = null;
		boolean mode = true;
		MCommand M = null;
		BCommand B = null;
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
			viewPage = "login?check=" + req.getAttribute("autho");
			break;
			
		case "/join.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			M = new Join_MC();
			M.excute(req, res);
			if((Boolean)req.getAttribute("check") == false)
			{
				viewPage = "join.jsp?check=false";
			}
			else
				viewPage = "login";
			break;
			
		case "/board.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new List_BC();
			B.excute(req, res);
			mode = false;
			viewPage = "list";
			break;
			
		case "/content_view.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new View_BC();
			B.excute(req, res);
			mode = false;
			viewPage = "view";
			break;
		
		case "/write.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Write_BC();
			B.excute(req, res);
			viewPage = "content_view.do?bid=" + req.getAttribute("bid");
			break;
			
		case "/private.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			M = new Private_MC();
			mode = false;
			M.excute(req, res);
			viewPage = "viewPrivate";
			break;
			
		case "/find.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Find_BC();
			B.excute(req, res);
			mode = false;
			viewPage = "list_F";
			break;
			
		case "/comment.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Comments_BC();
			B.excute(req, res);
			viewPage = "content_view.do?bid="+req.getParameter("bid");
			break;
			
		case "/modify.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Modify_BC();
			B.excute(req, res);
			viewPage = "content_view.do?bid="+req.getParameter("bid");
			break;
			
		case "/delete.do" :
			System.out.println(com + " �۵�");
			System.out.println("\n\n\n");
			B = new Delete_BC();
			B.excute(req, res);
			viewPage = "board.do?type=" + req.getParameter("type");
			break;
			
		}
		
		if(mode)
		{
			try {
				res.sendRedirect(viewPage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			//���������� ������ ������� �̵�
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			try 
			{
				dispatcher.forward(req, res);
			} 
			catch (ServletException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
}
