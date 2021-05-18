package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class EmpDAO {
	public List<EmpDTO> empList(int start, int end){
		List<EmpDTO> items = new ArrayList<EmpDTO>();
		SqlSession session=
				MybatisManager.getInstance().openSession();
		Map<String, Object>map= new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		items=session.selectList("emp.empList",map);
		return items;
	}
	public int empCount() {
		int count=0;
		SqlSession session=MybatisManager.getInstance().openSession();
		count=session.selectOne("emp.empCount");
		session.close();
		return count;
	}
}
