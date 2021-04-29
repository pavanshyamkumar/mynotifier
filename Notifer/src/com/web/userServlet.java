package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.xadmin.usermanagement.bean.Loginbeam;
import com.xadmin.usermanagement.bean.Userbeam;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserDao userdao;
   

	
	public void init() throws ServletException {
	
		userdao=new UserDao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action=request.getServletPath();
			switch(action)
			{
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				try {
					insertUserForm(request,response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/delete":
				try {
					del(request,response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/edit":
				try {
					edit(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/update":
				
				try {
					updateUser(request,response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/List":
				try {
					listUser(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			}
			
	}
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("user-form.jsp");
		dis.forward(request, response);
		
		}
		
		
		
		private void insertUserForm(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String country=request.getParameter("country");
			Userbeam newUser=new Userbeam(name,email,country);
			HttpSession sessi = request.getSession();
			String username = (String) sessi.getAttribute("username");
			userdao.insertuser(newUser,username);
			response.sendRedirect("List");
		}
		
		
		private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			HttpSession sessi = request.getSession();
			String username = (String) sessi.getAttribute("username");
			int id=Integer.parseInt(request.getParameter("id"));
			userdao.delUser(id,username);
			response.sendRedirect("List");
			
		}
		
		private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
			HttpSession sessi = request.getSession();
			String username = (String) sessi.getAttribute("username");
			int id=Integer.parseInt(request.getParameter("id"));
			Userbeam ex;
			ex=userdao.selectUser(id,username);
			RequestDispatcher dis=request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("user", ex);
			dis.forward(request, response);
		}
		
		
		private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			HttpSession sessi = request.getSession();
			String username = (String) sessi.getAttribute("username");
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String country=request.getParameter("country");
			Userbeam user = new Userbeam(id,name,email,country);
			userdao.updateUser(user,username);
			response.sendRedirect("List");
			
		}
		private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
			HttpSession sessi = request.getSession();
			String username = (String) sessi.getAttribute("username");
			List<Userbeam> listUser=userdao.selectAll(username);
			request.setAttribute("listUser", listUser);
			RequestDispatcher dis=request.getRequestDispatcher("user-list.jsp");
			dis.forward(request, response);
			
		}
		
		private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
			System.out.print("not applicable");
			RequestDispatcher dis=request.getRequestDispatcher("err.jsp");
			dis.forward(request, response);
		}
		
		
		
		
		
		
		

}
