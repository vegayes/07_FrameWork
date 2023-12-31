package edu.kh.project.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.AjaxService;

@Controller // 요청/응답 제어 + bean 등록
public class AjaxController {
	
	@Autowired
	private AjaxService service;

	@GetMapping("/selectMemberTel")
	@ResponseBody
	public String selectMemberTel(String nickName){
								// 쿼리스트링에 담겨있는 파라미터
		// 요청을 보낸 아이한테 값을 리턴함.
		
		// 기존에는 return 리다이렉트 / 포워드 -> 새로운 화면 보임 ( 동기식 ) 
		// return 데이터 -> 데이터를 요청한 곳으로 반환 ( 비동기식 ) 
		
		//@ResponseBody
		// -> Controller의 결과로 데이터를 반환할 때 사용하는 어노테이션
		System.out.println(nickName);
		return service.selectMemberTel(nickName);
	}
	
	//----------------------------------------------------------
	// 이메일로 회원정보 조회
	@PostMapping("/selectMember")
	@ResponseBody // 비동기 요청한 곳으로 응답 + JAVA 데이터를 JSON, TEXT로 변환 
	public Member selectMember(@RequestBody Map<String ,Object> paramMap) {
		
		//@RequstBody Map<String, Object> paramMap
		// -> 요청된 HTTP Body에 담긴 모든 데이터를 자바 객체인 Map으로 반환
		
		System.out.println("paramMap :: " + paramMap); // {email = user01@test...}
		
		String email = (String) paramMap.get("email"); // user01@test
		
		return service.selectMember(email);
	}
	
	

	
	
	
	
	
}
