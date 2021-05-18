package guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.GuestBookDAO;
import guestbook.dto.GuestBookDTO;

@WebServlet("/guestbook_servlet/*")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI(); //요청한 주소
		//String uri=request.getRequestURL().toString(); //full url
		GuestBookDAO dao=new GuestBookDAO();
		if(uri.indexOf("list.do")!=-1) {
			String searchkey="name"; //검색옵션
			String search=""; //검색키워드
			if(request.getParameter("searchkey")!=null) {
				searchkey=request.getParameter("searchkey");
			}
			if(request.getParameter("search")!=null) {
				search=request.getParameter("search");
			}
			List<GuestBookDTO> items=dao.getList(searchkey, search);
			//int count=dao.getCount(searchkey, search);
			int count=items.size();
			request.setAttribute("list", items);
			request.setAttribute("count", count);
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			String url="/guestbook/list.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else if(uri.indexOf("insert.do")!=-1){
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String passwd=request.getParameter("passwd");
			String content=request.getParameter("content");
			GuestBookDTO dto=new GuestBookDTO();
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			dao.gbInsert(dto);
			String url="/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath()+url);
		}else if(uri.indexOf("passwd_check.do")!=-1) {
			int idx=Integer.parseInt(request.getParameter("idx"));
			String passwd=request.getParameter("passwd");
			boolean result=dao.passwdCheck(idx, passwd);
			String url="";
			if(result) {
				url="/guestbook/edit.jsp";
				GuestBookDTO dto=dao.gbDetail(idx);
				request.setAttribute("dto", dto);
			}else {
				url="/guestbook_servlet/list.do";
			}
			RequestDispatcher rd=request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else if(uri.indexOf("update.do")!=-1) {
			int idx=Integer.parseInt(request.getParameter("idx"));
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String passwd=request.getParameter("passwd");
			String content=request.getParameter("content");
			GuestBookDTO dto=new GuestBookDTO();
			dto.setIdx(idx);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			dao.gbUpdate(dto);
			String url="/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath()+url);
		}else if(uri.indexOf("delete.do")!=-1) {
			int idx=Integer.parseInt(request.getParameter("idx"));
			dao.gbDelete(idx);
			String url="/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath()+url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
