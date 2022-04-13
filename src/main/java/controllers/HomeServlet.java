package controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet({"/HomeServlet","/dangXuat"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		if (uri.contains("/HomeServlet")) {
			request.setAttribute("views",
					"/views/Home.jsp");
			request.getRequestDispatcher("/views/layout.jsp")
					.forward(request, response);
		}else if (uri.contains("/dangXuat")) {
			this.dangXuat(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void dangXuat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession http = request.getSession();
		http.removeAttribute("user");
		request.getRequestDispatcher("/login")
		.forward(request, response);
	}
}