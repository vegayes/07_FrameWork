package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository // Persistence Layer, 영속성 관련 클래스 
			// (DB관련된 클래스) + Bean 등록 (== Spring 객체화) 
public class MemberDAO {

	// DAO는 DB랑 연결하기 위한 Connection이 공통적으로 필요함.
	// + Mybatis 영속성 프레임워크를 이용하라면 Connection을 이용해서 만들어진 객체
	// SqlSessionTemplate을 사용
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	/** 로그인 DAO
	 * @param inputMember
	 * @return 회원정보  또는 null
	 */
	public Member login(Member inputMember) {
		
		// 마이바티스를 이용해서 1행 조회 (selectOne)
		
		// sqlSession.selectOne("namespace값.id값", 전달할 값);
		// -> namespace가 일치하는 Mapper에서 
		// 		id가 일치하는 SQL 구문을 수행 후 결과를 1행 반환 
		
		return sqlSession.selectOne("memberMapper.login", inputMember);
	}


	public int singUp(Member inputMember) {
		
		return sqlSession.insert("memberMapper.signUp", inputMember);
	}


	public int update(Member updateMember) {
		
		System.out.println("수정하기 DAO 진입");

		return sqlSession.update("memberMapper.update", updateMember);
	}



	
	
	
	

	
	
}
