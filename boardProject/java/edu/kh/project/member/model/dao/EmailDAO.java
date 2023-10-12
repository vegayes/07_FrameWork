package edu.kh.project.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int updateAuthKey(Map<String, String> map) {

		System.out.println("DAO에서의 map : "+map);
		return sqlSession.update("emailMapper.updateAuthKey", map);
	}
	
	

	public int insertAuthKey(Map<String, String> map) {
		
		return sqlSession.insert("emailMapper.insertAuthKey", map);
		//  update, insert 함수 둘다 써도 됨. 
	}



	public int checkAuthKey(Map<String, Object> paramMap) {
		
		int result = sqlSession.selectOne("emailMapper.checkAuthKey", paramMap);
		
		System.out.println("DAO에서의 result :" + result);
		
		
		return result; 
	}
	
	
}
