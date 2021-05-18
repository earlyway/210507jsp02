
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test/login.do")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("get 방식 호출");
//		response.sendRedirect("/jsp02/test/login.jsp");
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request,response);
//		System.out.println("post 방식 호출");
//		String id=request.getParameter("id");
//		String pwd=request.getParameter("pwd");
//		System.out.println("id:"+id);
//		System.out.println("pwd:"+pwd);
	}

}





