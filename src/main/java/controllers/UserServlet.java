package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDAO;
import entities.User;
import utils.EncryptUtil;

@WebServlet({ "/users/index", "/users/create", "/users/store", "/users/edit", "/users/update", "/users/delete" })
	public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public UserServlet() {
		super();
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("users/index")) {
			this.index(request, response);
		} else if (uri.contains("/users/create")) {
			this.create(request, response);
		} else if (uri.contains("/users/edit")) {
			this.edit(request, response);
		} else if (uri.contains("/users/delete")) {
			this.delete(request, response);
		} else {
			System.out.println("loi 404");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("/users/store")) {
			this.store(request, response);
		} else if (uri.contains("/users/update")) {
			this.update(request, response);
		} else {
			System.out.println("loi 404 do post");
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date now = new Date();
		List<User> ds = this.userDAO.all();
		request.setAttribute("ds", ds);
		request.setAttribute("now", now);
		request.setAttribute("views", "/views/admin/users/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("views", "/views/admin/users/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			User entity = new User();
			BeanUtils.populate(entity,
				request.getParameterMap());
			String encrypted = EncryptUtil.encrypt(
				request.getParameter("password")
			);
			entity.setPassword(encrypted);
			this.userDAO.create(entity);
			
			session.setAttribute("message",
				"Thêm mới thành công");
			response.sendRedirect("/Assignment"
				+ "/users/index");
		} catch (Exception e) {
			session.setAttribute("error", "add fail");
			e.printStackTrace();
			response.sendRedirect("/Assignment" + "/users/create");
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.userDAO.findById(id);
		request.setAttribute("user", entity);
		request.setAttribute("views", "/views/admin/users/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.userDAO.findById(id);
		try {
			this.userDAO.delete(entity);
			// TODO: Thông báo thành công
		} catch (Exception e) {
			// TODO: Thông báo lỗi
			e.printStackTrace();
		}

		response.sendRedirect("/Assignment" + "/users/index");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		try {
			int id = Integer.parseInt(idStr);
			User oldValue = this.userDAO.findById(id);
			User newValue = new User();
			BeanUtils.populate(newValue, request.getParameterMap());
			newValue.setPassword(oldValue.getPassword());
			this.userDAO.update(newValue);
			// TODO: Thông báo thành công
			response.sendRedirect("/Assignment" + "/users/index");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: Thông báo lỗi
			response.sendRedirect("/Assignment" + "/users/edit?id=" + idStr);
		}
	}
}
