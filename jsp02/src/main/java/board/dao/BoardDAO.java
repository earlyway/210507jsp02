package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import sqlmap.MybatisManager;

public class BoardDAO {
	private static BoardDAO instance;//싱글톤 인스턴스
	private BoardDAO() {//외부에서 호출할 수 없는 생성자
		
	}
	public static BoardDAO getInstance() {
		if(instance == null) {//null 이면
			instance= new BoardDAO();//인스턴스 생성
		}
		return instance;
	}
	
	public int count(String search_option, String keyword) {
		int result = 0;
		try (SqlSession session = 
				MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("search_option", search_option);
			map.put("keyword", keyword);
			result = session.selectOne( "board.search_count", map );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	public List<BoardDTO> searchList(String searchOption, String
			keyword, int start, int end){
		List<BoardDTO> list=null;
		try (SqlSession session= 
				MybatisManager.getInstance().openSession()) {
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("search_option", searchOption);
			map.put("keyword", keyword);
			map.put("start", start);
			map.put("end", end);
			list= session.selectList("board.searchList",map);
			for(BoardDTO dto : list) {
				String writer= dto.getWriter();
				String subject= dto.getSubject();
				switch (searchOption) {
				case "all":
					writer= writer.replace(keyword, 
				"<span style='color:red'>" +keyword + "</span>");
					subject= subject.replace(keyword, 
				"<span style='color:red'>" +keyword + "</span>");
					break;
				case "writer":
					writer= writer.replace(keyword, 
				"<span style='color:red'>" +keyword + "</span>");
					break;
				case "subject":
					subject= subject.replace(keyword, 
				"<span style='color:red'>" +keyword + "</span>");
					break;
				}
				dto.setWriter(writer);
				dto.setSubject(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
			
	}
	
	
	public void reply(BoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("board.reply", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
	}
	
	
	
	public void updateStep(int ref, int re_step) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("ref", ref);
			map.put("re_step", re_step);
			session.update("board.updateStep",map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
	}
	
	
	
	public String getFileName(int num) {
		String result=null;
		SqlSession session= null;
		try {
			session=MybatisManager.getInstance().openSession();
			//게시물번호로 첨부파일 이름 조회
			result= session.selectOne("board.getFileName", num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();//mybatis 세션종료
		}
		return result;
	}
	
	
	
	
	public void plusDown(int num) {
		SqlSession session = null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("board.plusDown",num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( session != null) session.close();
		}
	}
	
	
	
	public void commentAdd(BoardCommentDTO dto) {
		SqlSession session= null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("board.commentAdd",dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!= null) session.close();
		}
	}
	
	
	
	
	public List<BoardCommentDTO> commentList(int num){
		List<BoardCommentDTO> list=null;
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			list=session.selectList("board.commentList",num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
		return list;
	}
	
	
	
	
	public String passwdCheck(int num,String passwd) {
		String result=null;
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("num", num);//글번호
			map.put("passwd", passwd);//사용자가 입력한 비밀번호
			result=session.selectOne("board.pass_check",map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
		return result;
	}
	
	
	
	public void update(BoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.update("board.update",dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
	}
	
	
	public void delete(int num) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.delete("board.delete",num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null) session.close();
		}
	}
	
	
	public List<BoardDTO> list(int pageStart, int pageEnd){
		List<BoardDTO> list=null;
		SqlSession session=null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object>map = new HashMap<String,Object>();
			map.put("start", pageStart);
			map.put("end", pageEnd);
			list=session.selectList("board.list",map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return list;
	}
	
	
	
	
	
	
	public int count() {
		int result=0;
		//괄호를 바꾸었기때문에 finally절이 필요없음.
		try (SqlSession session=MybatisManager.getInstance().openSession()){
		result=session.selectOne("board.count");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	public void insert(BoardDTO dto) {
		SqlSession session=null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("board.insert", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null)
				session.close();
		}
	}
	
	
	
	
	
		public void plusReadCount(int num, HttpSession count_session) {
			SqlSession session =null;
			try {
				long read_time=0;
				if(count_session.getAttribute("read_time_" +num) !=null) {
					read_time = (long)count_session.getAttribute("read_time_"+num);
				}
				long current_time=System.currentTimeMillis();
				session= MybatisManager.getInstance().openSession();
				if(current_time - read_time > 5*1000) {
					session.update("board.plusReadCount", num);
					session.commit();
					count_session.setAttribute("read_time_"+num, current_time);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(session !=null) session.close();
			}
		}
		
		
		
		
		
		
		public BoardDTO view(int num) {
			BoardDTO dto=null;
			SqlSession session=null;
			try {
				session=MybatisManager.getInstance().openSession();
				dto = session.selectOne("board.view",num);
				/* dto = checkArticle(dto); */
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(session !=null) session.close();
			}
			return dto;
		}
	}

	


