package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String context = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		if (url.indexOf("list.do") != -1) {
			Map<String, Object> map = new HashMap<>();
			List<MemberDTO> list = dao.memberList();
			map.put("list", list);
			map.put("count", list.size());
			request.setAttribute("map", map);
			String page = "/ch06/member_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("join.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberDTO dto = new MemberDTO(userid, passwd, name, address, tel);
			dao.insert(dto);
		} else if (url.indexOf("view.do") != -1) {
			// 사용자 아이디
			String userid = request.getParameter("userid");
			// 아이디에 해당하는 레코드를 dto에 저장
			MemberDTO dto = dao.memberDetail(userid);
			// 출력페이지에서 사용할 수 있도록 저장
			request.setAttribute("dto", dto);
			// 출력페이지로 포워드
			String page = "/ch06/member_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("update.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberDTO dto = new MemberDTO(userid, passwd, name, address, tel);
			dao.update(dto);
			response.sendRedirect(context + "/ch06/member.jsp");
		} else if (url.indexOf("delete.do") != -1) {
			String userid = request.getParameter("userid");
			dao.delete(userid);
			response.sendRedirect(context + "/ch06/member.jsp");
		} else if (url.indexOf("login.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String result = dao.loginCheck(dto);
			request.setAttribute("result", result);
			String page = "/ch06/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("join_oracle_secret.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			dto.setName(name);
			dao.insertCrypt(dto);
		} else if (url.indexOf("login_oracle_secret.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberDTO dto = new MemberDTO();
			dto.setUserid(userid);
			dto.setPasswd(passwd);
			String result = dao.loginCheckCrypt(dto);
			request.setAttribute("result", result);
			String page = "/ch06/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
