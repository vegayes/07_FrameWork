package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

public interface AjaxService {

	/*
	 * */
	String selectMemberTel(String nickname);

	Member selectMember(String email);

	String emailCheck(String emailCheck);

	String nicknameCheck(String nickname); 
	
}

