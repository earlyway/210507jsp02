package pcomment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pcomment.dto.PcommentDTO;

@WebServlet("/pcomment_sevlet/.*")
public class PcommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(url.indexOf("comment_add.do")!=-1) {
		PcommentDTO dto=new PcommentDTO();
		String pcode=request.getParameter("pcode"));
		String pwriter=request.getParameter("pwriter");
		String pcontent=request.getParameter("pcontent");
		dto.setPcode(pcode);
		dto.setPcontent(pcontent);
		dto.setPwriter(pwriter);
		dao.commentAdd(dto);//댓글저장
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
