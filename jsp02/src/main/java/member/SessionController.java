package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/session_servlet/*")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url=request.getRequestURI(); //사용자가 요청한 url
		MemberDAO dao=new MemberDAO();
		if(url.indexOf("login.do")!=-1) {
			String userid=request.getParameter("userid");
			String passwd=request.getParameter("passwd");
			MemberDTO dto=new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String result=dao.loginCheckCrypt(dto);
			String page="/ch07/session_login.jsp";
			if(!result.equals("로그인 실패")) {
				//세션 객체 생성
				HttpSession session=request.getSession();
				//세션 변수 저장
				session.setAttribute("userid", userid);
				session.setAttribute("message", result);
				page="/ch07/main.jsp";
			}
			//페이지 이동
			response.sendRedirect(request.getContextPath()+page);
		}else if(url.indexOf("logout.do")!=-1) {
			HttpSession session=request.getSession();
			session.invalidate(); //세션 초기화
			String page=request.getContextPath()+"/ch07/session_login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
