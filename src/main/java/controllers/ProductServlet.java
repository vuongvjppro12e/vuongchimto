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
import dao.ProductDAO;
import entities.Category;
import entities.Product;


@WebServlet({ "/product/index", "/product/create", "/product/store", "/product/edit", "/product/update",
		"/product/delete" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDao;
	private CategoryDAO cateDAO;

	public ProductServlet() {
		super();
		this.proDao = new ProductDAO();
		this.cateDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("product/index")) {
			this.index(request, response);
		} else if (uri.contains("product/create")) {
			this.create(request, response);
		} else if (uri.contains("product/edit")) {
			this.edit(request, response);
		} else if (uri.contains("product/delete")) {
			this.delete(request, response);
		} else {
			System.out.println("loi 404");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Product entity = this.proDao.findById(id);
		try {
			this.proDao.delete(entity);
			// TODO: Thông báo thành công
		} catch (Exception e) {
			// TODO: Thông báo lỗi
			e.printStackTrace();
		}

		response.sendRedirect("/Assignment" + "/product/index");
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Product entity = this.proDao.findById(id);
		request.setAttribute("listProduct", proDao.all());
		request.setAttribute("listCategory", cateDAO.all());
		request.setAttribute("product", entity);
		request.setAttribute("views", "/views/admin/products/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> dsCategory = this.cateDAO.all();
		request.setAttribute("dsCategory", dsCategory);
		request.setAttribute("views", "/views/admin/products/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> ds = this.proDao.all();
		request.setAttribute("ds", ds);
		request.setAttribute("views", "/views/admin/products/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		if (uri.contains("product/store")) {
			this.store(request, response);
		} else if (uri.contains("product/update")) {
			this.update(request, response);
		} else {
			System.out.println("loi 404 do post");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idStr = request.getParameter("id");
		try {
			
			Product newValue = new Product();
			BeanUtils.populate(newValue, request.getParameterMap());
			this.proDao.update(newValue);
			session.setAttribute("message", "update success");
			response.sendRedirect("/Assignment" + "/product/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "update fail");
			response.sendRedirect("/Assignment" + "/product/edit?id=" + idStr);
		}

	}

	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Product entity = new Product();
			entity.setTen(request.getParameter("ten"));
			entity.setSoLuong(Integer.parseInt(request.getParameter("soLuong")));
			entity.setDonGia(Integer.parseInt(request.getParameter("donGia")));
			entity.setMauSac(request.getParameter("mauSac"));
			entity.setKichThuoc(request.getParameter("kichThuoc"));
			int id = Integer.parseInt(request.getParameter("category"));
			Category category = cateDAO.findById(id);
			entity.setCategory(category);

//			Product entity = new Product();
//			BeanUtils.populate(entity, request.getParameterMap());

			this.proDao.create(entity);
			session.setAttribute("message", "add success");
			response.sendRedirect("/Assignment" + "/product/index");
		} catch (Exception e) {
			session.setAttribute("error", "add fail");
			e.printStackTrace();
			response.sendRedirect("/Assignment" + "/product/create");
		}

	}
}
