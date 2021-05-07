package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url=request.getRequestURI();
	System.out.println(url);
	String context = request.getContextPath();
	MemberDAO dao = new MemberDAO();
	System.out.println(url.indexOf("list.do"));;
	
	if(url.indexOf("list.do") != -1){
		Map<String, Object> map = new HashMap<String, Object>();
		List<MemberDTO> list = dao.memberList();
		map.put("list", list);
		map.put("count", list.size());
		request.setAttribute("map", map);
		System.out.println(map);
		String page = "/ch06/member_list.jsp";
		RequestDispatcher rd=
				request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("join.do") != -1) {
		String userid=request.getParameter("userid");
		String passwd=request.getParameter("passwd");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String tel=request.getParameter("tel");
		MemberDTO dto=new MemberDTO(userid, passwd, name, address, tel);
		dao.insert(dto);
	}else if(url.indexOf("view.do") != -1) {
		String userid=request.getParameter("userid");
		System.out.println("view.do, userid:"+userid);
		MemberDTO dto=dao.memberDetail(userid);
		request.setAttribute("dto", dto);
		String page="/ch06/member_view.jsp";
		RequestDispatcher rd=request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("update.do") != -1) {
		String userid= request.getParameter("userid");
		String passwd= request.getParameter("passwd");
		String name= request.getParameter("name");
		String address= request.getParameter("address");
		String tel= request.getParameter("tel");
		MemberDTO dto=new MemberDTO(userid,passwd,name,address,tel);
		System.out.println(dto);
		dao.update(dto);
		response.sendRedirect(context + "/ch06/member.jsp");
	}else if(url.indexOf("delete.do") != -1) {
		String userid = request.getParameter("userid");
		dao.delete(userid);
		response.sendRedirect(context + "/ch06/member.jsp");
	}else if(url.indexOf("login.do") != -1) {
		String userid=request.getParameter("userid");
		String passwd=request.getParameter("passwd");
		System.out.println("아이디 :" +userid);
		System.out.println("비번 :"+passwd);
		MemberDTO dto=new MemberDTO();
		dto.setUserid(userid);
		dto.setPasswd(passwd);
		System.out.println(dto);
		String result=dao.loginCheck(dto);
		System.out.println(result);
		request.setAttribute("result", result);
		String page="/ch06/login_result.jsp";
		RequestDispatcher rd=request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("join_oracle_secret.do") != -1) {
		String userid=request.getParameter("userid");
		String passwd=request.getParameter("passwd");
		String name=request.getParameter("name");
		MemberDTO dto=new MemberDTO();
		dto.setUserid(userid);
		dto.setPasswd(passwd);
		dto.setName(name);
		System.out.println(dto);
		dao.insertCrypt(dto);
	}else if(url.indexOf("login_oracle_secret.do") != -1) {
		String userid= request.getParameter("userid");
		String passwd= request.getParameter("passwd");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
