package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDAO;
import shop.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url= request.getRequestURI();
	ProductDAO dao = new ProductDAO();
	if(url.indexOf("list.do") != -1) {
		List<ProductDTO> items = dao.listProduct();
		request.setAttribute("list", items);
		RequestDispatcher rd=
				request.getRequestDispatcher("/shop/product_list.jsp");
		rd.forward(request, response);
	}else if(url.indexOf("detail.do") != -1) {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		ProductDTO dto = dao.detailProduct(product_id);
		request.setAttribute("dto", dto);
		RequestDispatcher rd=
				request.getRequestDispatcher("/shop/product_detail.jsp");
		rd.forward(request, response);
	}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
