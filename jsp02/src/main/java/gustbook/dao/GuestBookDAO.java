package gustbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.dto.GuestBookDTO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
	public List<GuestBookDTO> getList(String searchkey, String search){
		SqlSession session=
				MybatisManager.getInstance().openSession();
		List<GuestBookDTO> list=null;
		if(searchkey.equals("name_content")) {
			list=session.selectList("gbListAll", search);
		}else {
			Map<String, Object> map=new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list= session.selectList("gbList", map);
		}
		session.close();
		return list;
	}
	
	
	
	public void gbInsert(GuestBookDTO dto) {
		SqlSession session =
				MybatisManager.getInstance().openSession();
		String content=dto.getContent();
		//태그 문자가 인식되지 않도록 처리
		content=content.replace("<", "&lt;");
		content=content.replace(">", "&gt;");
		//줄바꿈 문자 처리
		content=content.replace("\n", "<br>");
		//공백 문자 처리
		content=content.replace(" ", "&nbsp;&nbsp;");
		session.insert("gbInsert", dto);
		session.commit();//insert,update,delete 명령어는 커밋 반드시 필요.
		session.close();
	}
	
	
	public boolean passwdCheck(int idx, String passwd) {
		boolean result=false;
		SqlSession session=
				MybatisManager.getInstance().openSession();
		GuestBookDTO dto= new GuestBookDTO();
		dto.setIdx(idx);
		dto.setPasswd(passwd);
		//비밀번호가 맞으면1, 틀리면0
		int count=session.selectOne("passwdCheck", dto);
		result = count==1 ? true : false;
		session.close();
		return result;
	}
	
	
	public GuestBookDTO gbDetail(int idx) {
		GuestBookDTO dto= new GuestBookDTO();
		SqlSession session=
				MybatisManager.getInstance().openSession();
		dto= session.selectOne("gbDetail",idx);
		session.close();
		return dto;
	}
	
	
	public void gbUpdate(GuestBookDTO dto) {
		SqlSession session=
				MybatisManager.getInstance().openSession();
		session.update("gbUpdate", dto);
		session.commit();
		session.close();
	}
	
	public void gbDelete(int idx) {
		SqlSession session=
				MybatisManager.getInstance().openSession();
		session.delete("gbDelete", idx);
		session.commit();
		session.close();
	}
	
	
	public int getCount(String searchkey, String search) {
		int count = 0;
		SqlSession session=
				MybatisManager.getInstance().openSession();
		if(searchkey.equals("name_content")) {
			count= session.selectOne("gbCountAll", search);
		}else {
			Map<String, Object> map= new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			count=session.selectOne("gbCount", map);
		}
		session.close();
		return count;
	}
		
		
		
		
		
		
		
		
	

}
