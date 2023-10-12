package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service  // 비즈니스 로직(데이터 가공, DAO 호출, 트랜잭션 제어)처리하는 클래스 명시 
		  // + Bean으로 등록하는 어노테이션
public class MemberServiceImpl implements MemberService{
//	인터페이스를 상속 받아 사용 할 class
	
	//@Autowired : 작성된 필드와 Bean으로 등록된 객체 중 타입이 일치하는 Bean을 
	// 				해당 필드에 자동 주입(Injection)하는 어노테이션
	// == DI(Dependecy Injection, 의존성 주입)
	// -> 객체를 직접 만들지 않고, Spring이 만든걸 주입함(Spring에 의존)
	
	
	
//	private MemberDAO dao = new MemberDAO();  // >> Spring이 객체로 만들었기 때문에 또 다시 객체로 만들 필요 없음.
//	의존성 주입 DI
	@Autowired
	private MemberDAO dao;
	
	@Autowired  // bean으로 등록된 객체 중 타입이 일치하는 객체 DI 함. 
	private BCryptPasswordEncoder bcrypt;
	// 암호화는 로그인 , 회원가입 시 진행해야 함. 
	
	
	@Override
	public Member login(Member inputMember) {
		
		
//		System.out.println("암호화 확인 1  : "  + bcrypt.encode(inputMember.getMemberPw()));
//		System.out.println("암호화 확인 2  : "  + bcrypt.encode(inputMember.getMemberPw()));
//		System.out.println("암호화 확인 3  : "  + bcrypt.encode(inputMember.getMemberPw()));
//		System.out.println("암호화 확인 4  : "  + bcrypt.encode(inputMember.getMemberPw()));
//		System.out.println("암호화 확인 5  : "  + bcrypt.encode(inputMember.getMemberPw()));
		
		
		// dao 메서드 호출
		Member member = dao.login(inputMember);
		
		if(member != null) { // 아이디가 일치하는 회원이 조회된 경우
			
			// 입력한 pw, 암호화된 pw 같은지 확인
			if(bcrypt.matches(inputMember.getMemberPw(), member.getMemberPw() )) { // 같은 경우
											// 입력한 PW , DB의 PW 
				
				//비밀번호를 유지하지 안힉 위해서 로그인 정보에서 제거
				
				member.setMemberPw(null);
								
			}else { // 다를 경우
				
				// 로그인 실패처럼 만들기
				member = null;
			}
		}
		
		
		
		return member;
	}


	// 회원가입 서비스
	@Transactional
	@Override
	public int signUp(Member inputMember) {
		
		// 비밀번호 암호화(Bcrypt)
		String encodePw = bcrypt.encode(inputMember.getMemberPw());
		
		inputMember.setMemberPw(encodePw);
		
		int result = dao.singUp(inputMember);
		return result;
	}
	
	

	// 수정 서비스
	@Transactional
	@Override
	public int update(Member updateMember) {

		System.out.println("수정하기 Service updateMember" + updateMember);
		
		int result = dao.update(updateMember);
		
		
		
		
		return result;
	}
	
	

}
