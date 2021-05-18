package memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {
	public List<MemoDTO> listMemo(String searchkey, String search){
		//mybatis의 SqlSession 객체 생성
		SqlSession session=MybatisManager.getInstance().openSession();
		// selectList("네임스페이스.sql의id") 리스트가 리턴됨
		List<MemoDTO> list=null;
		if(searchkey.equals("writer_memo")) { //이름+메모로 검색할 경우
			list=session.selectList("memo.listAll", search);
		}else { //이름 또는 메모로 검색할 경우
			Map<String,Object> map=new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			//파라미터는 1개만 전달할 수 있으므로 해시맵으로 묶어서 전달
			list=session.selectList("memo.list", map);
		}
		// 리스트를 리턴
		return list;
	}
	public void insertMemo(MemoDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.insert("memo.insert", dto);
		session.commit(); //수동 커밋
		session.close(); //세션 종료
	}
	public MemoDTO viewMemo(int idx) {
		SqlSession session=MybatisManager.getInstance().openSession();
		//결과 레코드가 1개인 경우 selectOne, 여러개인 경우 selectList
		MemoDTO dto=session.selectOne("memo.view", idx);
		session.close();
		return dto;
	}
	public void updateMemo(MemoDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.update("memo.update", dto);
		session.commit();
		session.close();
	}
	public void deleteMemo(int idx) {
		SqlSession session=MybatisManager.getInstance().openSession();
		session.delete("memo.delete", idx);
		session.commit();
		session.close();
	}
	public int countMemo(String searchkey, String search) {
		int count=0;
		SqlSession session=MybatisManager.getInstance().openSession();
		if(searchkey.equals("writer_memo")) {
			count=session.selectOne("memo.countAll", search);
		}else {
			Map<String,Object> map=new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			count=session.selectOne("memo.count", map);
		}
		return count;
	}
}













