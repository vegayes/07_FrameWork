package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository // DB 연결 의미 + bean으로 등록
public class AjaxDAO {
	
	@Autowired // bean중에서 타입이 같은 객체를 DI 함.
	private SqlSessionTemplate sqlSession;
	

	public String selectMemberTel(String nickname) {
		
		return sqlSession.selectOne("ajaxMapper.selectMemberTel", nickname);
	}


}
