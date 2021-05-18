package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import common.Constants;
import page.Pager;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url=request.getRequestURL().toString();
	String contextPath=request.getContextPath();
	BoardDAO dao=BoardDAO.getInstance();
	if(url.indexOf("list.do") !=-1) {
		int count=dao.count();//레코드 개수 계산
		//페이지 번호
		int curPage=1;
		if(request.getParameter("curPage") !=null) {
			curPage=
					Integer.parseInt(request.getParameter("curPage"));
		}
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();//레코드 시작번호
		int end=pager.getPageEnd();
		List<BoardDTO> list=dao.list(start, end);//페이지 내용 리스트
		System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("page", pager);
		String page="/board/list.jsp";
		RequestDispatcher rd=
				request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("insert.do") !=-1) {
		BoardDTO dto= new BoardDTO();
		
		File uploadDir = new File(Constants.UPLOAD_PATH);
		if(!uploadDir.exists()) {//디렉토리가 없으면
			uploadDir.mkdir();//생성
		}
		MultipartRequest multi= new MultipartRequest(request,
				Constants.UPLOAD_PATH, Constants.MAX_UPLOAD,
				"utf-8", new DefaultFileRenamePolicy());
		String filename=" ";
		int filesize=0;
		try {
			Enumeration files= multi.getFileNames();
			while( files.hasMoreElements()) {//다음 요소가 있으면
				String file1= (String) files.nextElement();
				filename= multi.getFilesystemName(file1);
				File f1= multi.getFile(file1);
				if( f1 !=null) {
					filesize= (int) f1.length();
					if( filename != null) {
						int start = filename.lastIndexOf(".") + 1;
						if(start != -1) {
							String ext=filename.substring(start,
									filename.length());
							System.out.println(ext);
							if(ext.equals("jsp")|| ext.equals("exe")) {
								try {
									System.out.println("금지된 파일입니다.");
									f1.delete();
								} catch (Exception e) {
									e.printStackTrace();
								}
								System.out.println("삭제됨");
								response.sendRedirect(
										request.getContextPath()+"/board/write.jsp?message=error");
								return;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String writer= multi.getParameter("writer");
		String subject= multi.getParameter("subject");
		String content= multi.getParameter("content");
		String passwd= multi.getParameter("passwd");
		String ip= request.getRemoteAddr(); //ip주소
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setPasswd(passwd);
		dto.setIp(ip);
		if(filename ==null ||filename.trim().equals(""))
			filename = "-";
		
		dto.setFilename(filename);
		dto.setFilesize(filesize);
		
		String[] bad_ips= { "192.168.0.5", "192.168.0.6" };
		for (String str : bad_ips) {
			if(dto.getIp().equals(str)) {
				response.sendRedirect("이동할 페이지");
			}
		}
		
		dao.insert(dto);
		String page= contextPath +"/board_servlet/list.do";
		response.sendRedirect(page);
	}else if(url.indexOf("view.do") !=-1) {
		//게시물 번호
		int num=Integer.parseInt(request.getParameter("num"));
		HttpSession session= request.getSession();
		//조회수 증가 처리
		dao.plusReadCount(num, session);
		BoardDTO dto= dao.view(num);
		request.setAttribute("dto", dto);
		String page="/board/view.jsp";
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("download.do") !=-1) {
		int num=Integer.parseInt(request.getParameter("num"));
		String filename= dao.getFileName(num);
		System.out.println(filename);
		String path=Constants.UPLOAD_PATH + filename;
		byte b[]= new byte[4096];
		FileInputStream fis= new FileInputStream(path);//스트림을 생성
		String mimeType=getServletContext().getMimeType(path);
		System.out.println(mimeType);
		if(mimeType==null) {
			mimeType="application/octet-stream;charset=UTF-8";//범용 마임타입
		}
		filename= new String(filename.getBytes("utf-8"),"8859_1");
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		ServletOutputStream out=response.getOutputStream();
		int numRead;
		while(true) {
			numRead=fis.read(b,0,b.length);//서버에서 읽어서
			if(numRead == -1) break;//내용이 없으면 종료
			out.write(b,0,numRead);//클라이언트에 업로드
		}
		out.flush();
		out.close();
		fis.close();
		dao.plusDown(num);//다운로드 횟수 증가처리
	}else if(url.indexOf("comment_add.do")!=-1) {
		BoardCommentDTO dto=new BoardCommentDTO();
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		dto.setBoard_num(board_num);
		dto.setWriter(writer);
		dto.setContent(content);
		dao.commentAdd(dto);//댓글저장
	}else if(url.indexOf("commentList.do")!= -1) {
		//글번호
		int num=Integer.parseInt(request.getParameter("num"));
		//댓글리스트
		List<BoardCommentDTO>list = dao.commentList(num);
		//저장
		request.setAttribute("list", list);
		//출력페이지로 이동
		String page="/board/comment_list.jsp";
		RequestDispatcher rd=
				request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("pass_check.do") != -1) {
		//글번호
		int num=Integer.parseInt(request.getParameter("num"));
		//비밀번호
		String passwd=request.getParameter("passwd");
		//비밀번호 체크
		String result=dao.passwdCheck(num, passwd);
		String page="";
		if(result!=null) {//비번이 맞으면 수정페이지로 이동
			page="/board/edit.jsp";
			request.setAttribute("dto", dao.view(num));
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request,response);
		}else {//비번이 틀리면 되돌아감
			page=contextPath+"/board_servlet/view.do?num="+num
					+"&message=error";
			response.sendRedirect(page);
		}
		
		
	}else if(url.indexOf("update.do") != -1) {
		BoardDTO dto= new BoardDTO();
		
		MultipartRequest multi= new MultipartRequest(request,
				Constants.UPLOAD_PATH, Constants.MAX_UPLOAD,
				"utf-8", new DefaultFileRenamePolicy());
		String filename=" ";
		int filesize=0;
		try {
			Enumeration files= multi.getFileNames();
			while( files.hasMoreElements()) {//다음 요소가 있으면
				String file1= (String) files.nextElement();
				filename= multi.getFilesystemName(file1);
				File f1= multi.getFile(file1);
				if( f1 !=null) {
					filesize= (int) f1.length();
					if( filename != null) {
						int start = filename.lastIndexOf(".") + 1;
						if(start != -1) {
							String ext=filename.substring(start,
									filename.length());
							System.out.println(ext);
							if(ext.equals("jsp")|| ext.equals("exe")) {
								try {
									System.out.println("금지된 파일입니다.");
									f1.delete();
								} catch (Exception e) {
									e.printStackTrace();
								}
								System.out.println("삭제됨");
								response.sendRedirect(
										request.getContextPath()+"/board/write.jsp?message=error");
								return;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String writer= multi.getParameter("writer");
		String subject= multi.getParameter("subject");
		String content= multi.getParameter("content");
		String passwd= multi.getParameter("passwd");
		String ip= request.getRemoteAddr(); //ip주소
		int num=Integer.parseInt(multi.getParameter("num"));
		
		String fileDel=multi.getParameter("fileDel");
		if(fileDel !=null && fileDel.equals("on")) {
			String fileName=dao.getFileName(num);
			File f= new File(Constants.UPLOAD_PATH + fileName);
			f.delete();
			dto.setNum(num);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			dto.setIp(ip);
			dto.setFilename("-");
			dto.setFilesize(0);
			dto.setDown(0);
			dao.update(dto);
		}
		
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setPasswd(passwd);
		dto.setIp(ip);
		if(filename==null ||filename.trim().equals("")) {//첨부파일이 없을때
			BoardDTO dto2= dao.view(num);
			String fName= dto2.getFilename();
			int fSize=dto2.getFilesize();
			int fDown=dto2.getDown();
			dto.setFilename(fName);
			dto.setFilesize(fSize);
			dto.setDown(fDown);
		}else {//첨부파일이 있을때
			dto.setFilename(filename);
			dto.setFilesize(filesize);
		}
		String result=dao.passwdCheck(num, passwd);
		if(result!=null) {//비번이 일치하면
			dao.update(dto);
			String page = contextPath+"/board_servlet/list.do";
			response.sendRedirect(page);
		}else {//비번이 틀리면
			request.setAttribute("dto", dto);
			String page="/board/edit.jsp?pwd_error=y";
			RequestDispatcher rd=
					request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		
	}else if(url.indexOf("delete.do")!= -1) {
		MultipartRequest multi=new MultipartRequest(request,
				Constants.UPLOAD_PATH, Constants.MAX_UPLOAD,
				"utf-8",new DefaultFileRenamePolicy());
		int num=Integer.parseInt(multi.getParameter("num"));
		String passwd=multi.getParameter("passwd");
		String result=dao.passwdCheck(num, passwd);
		if(result!= null) {
			dao.delete(num);
			String page=contextPath+"/board_servlet/list.do";
			response.sendRedirect(page);
		}else {
			request.setAttribute("dto", dao.view(num));
			String page="/board/edit.jsp?pwd_error=y";
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}else if(url.indexOf("reply.do") != -1) {
		int num=Integer.parseInt(request.getParameter("num"));
		BoardDTO dto=dao.view(num);
		dto.setContent("===게시물 내용===\n"+dto.getContent());
		request.setAttribute("dto", dto);
		String page="/board/reply.jsp";
		RequestDispatcher rd=request.getRequestDispatcher(page);
		rd.forward(request, response);
	}else if(url.indexOf("insertReply.do")!=-1) {
		request.setCharacterEncoding("utf-8");
		int num=0;
		if(request.getParameter("num") !=null) {
			num=Integer.parseInt(request.getParameter("num"));
		}
		System.out.println(num);
		BoardDTO dto=dao.view(num);
		int ref=dto.getRef();
		int re_step=dto.getRe_step()+1;
		int re_level=dto.getRe_level()+1;
		String writer=request.getParameter("writer");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String passwd=request.getParameter("passwd");
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setPasswd(passwd);
		dto.setRef(ref);
		dto.setRe_level(re_level);
		dto.setRe_step(re_step);
		dto.setFilename("-");
		dto.setFilesize(0);
		dto.setDown(0);
		dao.updateStep(ref, re_step);
		dao.reply(dto);
		String page="/board_servlet/list.do";
		response.sendRedirect(request.getContextPath()+page);
	}else if(url.indexOf("search.do") != -1) {
		String search_option =
				request.getParameter("search_option");//검색 옵션
		String keyword= request.getParameter("keyword");//검색 키워드
		int count = dao.count(search_option, keyword);//레코드 개수
		int curPage = 1;//페이지 번호
		if(request.getParameter("curPage") != null) {
			curPage =
					Integer.parseInt(request.getParameter("curPage"));
		}
		Pager pageDao = new Pager(count, curPage);
		int start= pageDao.getPageBegin();
		int end = pageDao.getPageEnd();
		
		List<BoardDTO> list= dao.searchList(search_option, keyword, start, end);
		request.setAttribute("list", list);
		request.setAttribute("search_option", search_option);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", pageDao);
		String page = "/board/search.jsp";//출력 페이지
		RequestDispatcher rd=
				request.getRequestDispatcher(page);
		rd.forward(request, response);
	 }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
