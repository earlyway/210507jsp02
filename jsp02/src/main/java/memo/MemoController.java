package memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.dao.MemoDAO;
import memo.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemoDAO dao=new MemoDAO();
		String url=request.getRequestURL().toString();
		if(url.indexOf("list.do")!=-1) {
			String searchkey=request.getParameter("searchkey");//검색옵션
			//null 또는 빈값이면
			if(searchkey==null || searchkey.equals(""))
				searchkey="writer"; //기본값
			String search=request.getParameter("search");//검색키워드
			if(search==null) search="";
			List<MemoDTO> list=dao.listMemo(searchkey,search);
			request.setAttribute("list", list);
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			String page="/memo/memo_list.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(url.indexOf("insert.do")!=-1){
			String writer=request.getParameter("writer");
			String memo=request.getParameter("memo");
			dao.insertMemo(new MemoDTO(writer, memo));
		}else if(url.indexOf("view.do")!=-1) { //메모 상세
			int idx=Integer.parseInt(request.getParameter("idx")); //글번호
			MemoDTO dto=dao.viewMemo(idx);
			request.setAttribute("dto", dto);
			String page="/memo/memo_view.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(url.indexOf("del.do")!=-1) { //개별 삭제
			int idx=Integer.parseInt(request.getParameter("idx")); //삭제할 번호
			dao.deleteMemo(idx);
			//메모 리스트로 이동
			response.sendRedirect(request.getContextPath()+"/memo/memo.jsp");
		}else if(url.indexOf("delete_all.do")!=-1) { //선택 삭제
			//파라미터 배열
			String[] idx=request.getParameterValues("idx");
			if(idx!=null) { //체크된 값이 없으면 null
				for(int i=0; i<idx.length; i++) {
					//배열을 순회하면서 삭제 처리
					dao.deleteMemo(Integer.parseInt(idx[i]));
				}
			}
			//메모 리스트로 이동
			response.sendRedirect(request.getContextPath()+"/memo/memo.jsp");
		}else if(url.indexOf("update.do")!=-1) {
			//수정할 내용들을 dto에 저장
			int idx=Integer.parseInt(request.getParameter("idx"));
			String writer=request.getParameter("writer");
			String memo=request.getParameter("memo");
			MemoDTO dto=new MemoDTO();
			dto.setIdx(idx);
			dto.setWriter(writer);
			dto.setMemo(memo);
			//레코드 수정
			dao.updateMemo(dto);
			//메모 리스트로 이동
			response.sendRedirect(request.getContextPath()+"/memo/memo.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
