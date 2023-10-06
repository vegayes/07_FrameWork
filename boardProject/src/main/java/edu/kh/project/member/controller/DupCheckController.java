package edu.kh.project.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.service.AjaxService;

@Controller
@RequestMapping("/dupCehck")
public class DupCheckController {
	
	@Autowired
	private AjaxService service;
	
	// ----------------------------------------------------
	// 이메일 검사 
	@PostMapping("/email")
	@ResponseBody
	public String emailCheck(@RequestBody Map<String ,Object> paramMap) {
		System.out.println("찾았어!");
		
		System.out.println("paramMap :: " + paramMap); // {email = user01@test...}
		
		String emailCheck =  (String) paramMap.get("emailCheck"); // user01@test
		
		String result = service.emailCheck(emailCheck);
		
		System.out.println("result : " + result);
		
		return result;
	}
	
	@GetMapping("/nickname")
	@ResponseBody
	public String nickname(String nickname) {
		System.out.println("입력한 nickname : " + nickname);
		
		String nickRs = service.nicknameCheck(nickname);
		
		System.out.println(nickRs);
		return nickRs;
	}
	

}
