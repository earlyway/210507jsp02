package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.MemberDAO;
import shop.dto.MemberDTO;

@WebServlet("/login_servlet/*")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url= request.getRequestURI();
	System.out.println(url);
	String path = request.getContextPath();
	MemberDAO dao = new MemberDAO();
	System.out.println(url.indexOf("list.do"));
	if(url.indexOf("login.do") != -1) {
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		MemberDTO dto = new MemberDTO();
		dto.setUserid(userid);
		dto.setPasswd(passwd);
		String name = dao.loginCheck(dto);
		if(name == null) {
			String page= path + "/shop/login.jsp?message=error";
			response.sendRedirect(page);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("name", name);
			request.setAttribute("result", name+"님 환영합니다");
			String page= "/shop/login_result.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}else if(url.indexOf("logout.do") != -1) {//로그아웃처리
		HttpSession session = request.getSession();
		session.invalidate();//세션 초기화
		String page = path +"/shop/login.jsp?message=logout";
		response.sendRedirect(page);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
