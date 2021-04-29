package com.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;

import com.xadmin.usermanagement.bean.Loginbeam;

import com.dao.LoginDao;

import com.xadmin.usermanagement.bean.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		LoginDao loginDao = new LoginDao();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Loginbeam loginBean = new Loginbeam();
		
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		HttpSession sessi = request.getSession();
		sessi.setAttribute("username", username);
		
		if (loginDao.validate(loginBean))
		{
			System.out.print("sucess");
			HttpSession session = request.getSession();
			session.setAttribute("loginname", username);
			
			response.sendRedirect("user-list.jsp");
			
		}
		else 
		{
			//HttpSession session = request.getSession();
			response.sendRedirect("login.jsp");
			
		}
	}
	}


