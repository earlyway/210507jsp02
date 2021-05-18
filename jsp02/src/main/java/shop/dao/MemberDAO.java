package shop.dao;

import org.apache.ibatis.session.SqlSession;

import shop.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {
	public String loginCheck(MemberDTO dto) {
		SqlSession session=MybatisManager.getInstance().openSession();
		String name=session.selectOne("member.login_check", dto);
		session.close();
		return name;
	}

}
