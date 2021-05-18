package pcomment.dao;

import org.apache.ibatis.session.SqlSession;

import pcomment.dto.PcommentDTO;
import sqlmap.MybatisManager;

public class PcommentDAO {
	private static PcommentDAO instance;//싱글톤 인스턴스
	private PcommentDAO() {//외부에서 호출할 수 없는 생성자
		
	}
	public static PcommentDAO getInstance() {
		if(instance == null) {//null 이면
			instance= new PcommentDAO();//인스턴스 생성
		}
		return instance;
	}
	public void commentAdd(PcommentDTO dto) {
		SqlSession session= null;
		try {
			session=MybatisManager.getInstance().openSession();
			session.insert("pcomment.comment_Add",dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!= null) session.close();
		}
	}

}
