package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.service.EmailService;

@Controller
@RequestMapping("/sendEmail")
public class EmailController {
	@Autowired
	private EmailService service;
	
	@GetMapping("/signUp")
	@ResponseBody
	public int signUp(String email) {
		
		System.out.println("회원가입 인증번호 받기 controller");
		return service.signUp(email, "회원가입");
	}
	
	
	@GetMapping("/checkAuthKey")
	@ResponseBody
	public int checkAuthKey(String checkAuthKey, ) {
		
		System.out.println("query :"+ checkAuthKey);
		
		return service.checkAuthKey(checkAuthKey);
	}
	
	
	
	

}
