package guestbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.dto.GuestBookDTO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
	public List<GuestBookDTO> getList(String searchkey, String search){
		//sql 실행 객체 생성
		SqlSession session=MybatisManager.getInstance().openSession();
		List<GuestBookDTO> list=null;
		if(searchkey.equals("name_content")) { //이름+내용 검색
			list=session.selectList("gbListAll", search);
		}else {
			//파라미터를 1개로 만들기 위해 map에 저장
			Map<String,Object> map=new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list=session.selectList("gbList", map);
		}
		session.close(); //sql session을 닫음
		return list;
	}
	public void gbInsert(GuestBookDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		String content=dto.getContent();
		//태그 문자가 인식되지 않도록 처리
		content=content.replace("<", "&lt;");
		content=content.replace(">", "&gt;");
//		//줄바꿈 문자 처리
		content=content.replace("\n", "<br>");
//		//공백 문자 처리
		content=content.replace("  ", "&nbsp;&nbsp;");
		dto.setContent(content);
		session.insert("gbInsert", dto);
		session.commit(); //insert,update,delete 명령어는 커밋을 해야 함
		session.close();
	}
	public boolean passwdCheck(int idx, String passwd) {
		boolean result=false;
		SqlSession session=MybatisManager.getInstance().openSession();
		GuestBookDTO dto=new GuestBookDTO();
		dto.setIdx(idx);
		dto.setPasswd(passwd);
		//비밀번호가 맞으면 1, 틀리면 0
		int count=session.selectOne("passwdCheck", dto);
		result=count==1 ? true : false;
		session.close();
		return result;
	}
	public GuestBookDTO gbDetail(int idx) {
		GuestBookDTO dto=new GuestBookDTO();
		SqlSession session=MybatisManager.getInstance().openSession();
		dto=session.selectOne("gbDetail", idx);
		session.close();
		return dto;
	}
	public void gbUpdate(GuestBookDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.update("gbUpdate", dto);
		session.commit();
		session.close();
	}
	public void gbDelete(int idx) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.delete("gbDelete", idx);
		session.commit();
		session.close();
	}	
	public int getCount(String searchkey, String search) {
		int count=0;
		SqlSession session=MybatisManager.getInstance().openSession();
		if(searchkey.equals("name_content")) {
			count=session.selectOne("gbCountAll", search);
		}else {
			Map<String,Object> map=new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			count=session.selectOne("gbCountAll", map);
		}
		session.close();
		return count;
	}
}












