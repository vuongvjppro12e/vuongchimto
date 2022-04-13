package controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDAO;
import dao.UserDAO;

import entities.Category;

import entities.User;

@WebServlet({"/category/create","/category/index","/category/store","/category/edit","/category/update","/category/delete"})
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private CategoryDAO cateDAO;
    public CategoryServlet() {
        super();
        this.userDAO = new UserDAO();
        this.cateDAO = new CategoryDAO();
    }

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("category/index")) {
			this.index(request, response);
		}else if (uri.contains("category/create")) {
			this.create(request, response);
		}	else if (uri.contains("category/edit")) {
			this.edit(request, response);
		}else if (uri.contains("category/update")) {
			this.update(request, response);
		}else if (uri.contains("category/delete")) {
			this.delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Category entity = this.cateDAO.findById(id);
		try {
			this.cateDAO.delete(entity);
			// TODO: Thông báo thành công
		} catch (Exception e) {
			// TODO: Thông báo lỗi
			e.printStackTrace();
		}

		response.sendRedirect("/Assignment" + "/category/index");
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Category entity = this.cateDAO.findById(id);
		request.setAttribute("dsUser", userDAO.all());
		request.setAttribute("category", entity);
		request.setAttribute("views", "/views/admin/categories/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("category/store")) {
			this.store(request, response);
		}else if (uri.contains("category/update")) {
			this.update(request, response);
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			
			Category newValue = new CategoryDAO().findById(Integer.parseInt(idStr));
//			newValue.setTen(request.getParameter("ten"));
			BeanUtils.populate(newValue, request.getParameterMap());
			this.cateDAO.update(newValue);
			session.setAttribute("message", "update success");
			response.sendRedirect("/Assignment" + "/category/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "update fail");
			response.sendRedirect("/Assignment" + "/category/edit?id=" + idStr);
		}
		
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> ds = this.cateDAO.all();
		request.setAttribute("ds", ds);
		request.setAttribute("views", "/views/admin/categories/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> dsUser = this.userDAO.all();
		request.setAttribute("dsUser", dsUser);
		request.setAttribute("views", "/views/admin/categories/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category cate = new Category();
		String ten = request.getParameter("ten");
		int id = Integer.parseInt(
			request.getParameter("user_id")
		);
		User user = this.userDAO.findById(id);
		cate.setTen(ten);
		cate.setUser(user);
		try {
			this.cateDAO.create(cate);
			response.sendRedirect("/Assignment" + "/category/index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
