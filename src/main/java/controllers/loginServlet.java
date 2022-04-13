package controllers;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

import entities.User;
import utils.EncryptUtil;



@WebServlet("/login")
public class loginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public loginServlet() {
        super();
        this.userDAO = new UserDAO();
    }

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		request.setAttribute("views",
				"/views/login.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
				.forward(request, response);
		request.getRequestDispatcher("/views/login.jsp")
			.forward(request, response);
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email"),
			pwd = request.getParameter("password");
		
		User user = this.userDAO.findByEmail(email);
		
		boolean check = EncryptUtil.check(pwd, user.getPassword());
		
		if (check == true) {
			session.setAttribute("user", user);
			System.out.println("dang nhap thanh cong");
		} else {
			System.out.println("dang nhap that bai");
		}
		
		response.sendRedirect("/Assignment"
				+ "/users/index");
	}

}
