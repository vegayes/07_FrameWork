package edu.kh.project.common;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외 처리용 컨트롤러(프로젝트 전역)
@ControllerAdvice
public class ExceptionController {
	
	// Exception 예외
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
									
		// Exception e : 예외 정보를 담고있는 객체
		// Model model : 데이터 전달용 객체 ( request scope 기본 ) 
		e.printStackTrace(); // 예외 내용/발생 메서드 확인
		
		model.addAttribute("e", e); // 예외 발생 시 forward되는 페이지로 e를 전달함. (request scope도 가능함.) 
		
		// View Resolver의 prefix, suffix를 붙여서 JSP 경로를 만듦.
		return "common/error";
	}
	
	
	// SQL 예외 
//	@ExceptionHandler(SQLException.class)
	public String exceptionHandler(SQLException e, Model model) {
									
		// Exception e : 예외 정보를 담고있는 객체
		// Model model : 데이터 전달용 객체 ( request scope 기본 ) 
		e.printStackTrace(); // 예외 내용/발생 메서드 확인
		
		model.addAttribute("e", e); // 예외 발생 시 forward되는 페이지로 e를 전달함. (request scope도 가능함.) 
		
		// View Resolver의 prefix, suffix를 붙여서 JSP 경로를 만듦.
		return "common/error";
	}	

}
