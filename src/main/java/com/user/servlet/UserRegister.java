package com.user.servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import com.entity.User;
import com.db.DBconnect;
import com.dao.UserDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;




@WebServlet("/user_register")
public class UserRegister extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullName = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			
			User u = new User(fullName , email , password);
			
			UserDAO dao = new UserDAO(DBconnect.getConn());
			HttpSession session = req.getSession();
			
			boolean f = dao.userRegister(u);
			
			
			if(f) {
				
				session.setAttribute("sucMsg" , "Register Sucessfully");
				resp.sendRedirect("signup.jsp");
				
			}else {
				session.setAttribute("errorMsg" , "Something Went Wrong ");
				resp.sendRedirect("signup.jsp");
				
			}
			
			
			
			
			
			
		}catch(Exception e) {
			
		}
	}
	
	
	

}
