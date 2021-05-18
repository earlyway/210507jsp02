package page;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/page_servlet/*")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url=request.getRequestURL().toString();//요청한 주소
	EmpDAO dao=new EmpDAO();
	if(url.indexOf("list.do") != -1) {
		int count=dao.empCount();//전체 레코드 개수
		int curPage= 1;//현재 페이지 번호
		if(request.getParameter("curPage") != null) {
			curPage=
					Integer.parseInt(request.getParameter("curPage"));
		}
		System.out.println("현재 페이지:"+curPage);
		Pager pager = new Pager(count, curPage);
		int start=pager.getPageBegin();//레코드 시작번호
		int end=pager.getPageEnd();//레코드 끝번호
		List<EmpDTO> list=dao.empList(start, end);//페이지의 레코드리스트
		request.setAttribute("list", list);
		request.setAttribute("page", pager);
		//출력 페이지로 포워드
		String page="/page/list.jsp";
		RequestDispatcher rd=
				request.getRequestDispatcher(page);
		rd.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



