package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

// Service Interface 사용이유

// 1. 프로젝트에 규칙성을 부여하기 위해서
// 2. 클래스간의 결합도를 약화 시키기 위해서 (객체 지향적 설계)
// --> 유지보수성 향상
// 3. Spring AOP 사용을 위해서 
//     (AOP는 Spring-proxy를 이용해서 동작하는데, 이 떼 Service 인터페이스가 필요함)
//  MemberService service = new MemberServiceImpl()은 가능! ( 하지만, 인터페이스는 객체 불가.?) 


public interface MemberService  {

	/** 로그인 서비스
	 * @param inputMember (email,pw)
	 * @return email,pw가 일치하는 정보 또는 null
	 */
	Member login(Member inputMember);

	int signUp(Member inputMember);

	int update(Member loginMember);
	
}
