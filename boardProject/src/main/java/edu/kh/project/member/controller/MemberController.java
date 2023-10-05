package edu.kh.project.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@Controller  // 요청/응답 클래스 + bean 등록(Spring 관리하는 객체)
@RequestMapping("/member") // login 또는 logout 모두 공통된 주소를 가져오기 떄문에  class에서 RequestMapping 가능
@SessionAttributes({"loginMember"}) // Model의 이동(key)를 적으면 session으로 추가
public class MemberController {
	
	
	/*찐 로그인 필드*/
	
	// 등록된 Bean 중에서 필드와 타입이 일치하는 Bean을 주입
	// -> MemberService를 구현한 MemberServiceImpl의 Bean을 주입
	@Autowired
	private MemberService service; // 인터페이스로
	
	
	
	// 로그인 :  /member/login
	// 로그아웃 : /member/logout
	
	
	// /member/login , post 처리방식
	// @RequestMapping("/login") == get 방식
	
	
	//  @RequestMapping : 요청 주소에 맞는 클래스/메서드 연결
	//  @RequestMapping("요청주소") -> GET / POST 구분 X , 주소만 맞으면 연결하지만 GET 요청 시 사용 
	//  @RequestMapping(value = "/login", method = RequestMethod.GET/POST) -> GET/POST 방식을 구분 
	
//	1) @RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest req) {
		
		// 파라미터 전달 방법 1 : HttpServletRequest를 이용하는 방법
		// -> Controller 메서드에 매개변수로 HttpServletRequest를 작성
		
		// 매개변수에 적으면 사용 가능한 이유
		// Spring 제공하는 Argument Resolver(매개변수 해결사)가 자동으로 
		// 대입해주고 해결해줌. 
		
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println("inputEmail : " + inputEmail);
		System.out.println("inputPw : " + inputPw);
		
		
		// *******************!redirect 방법! *******************//
		// "redirect : 요청주소"
		
		
		return "redirect:/";
	}
	
	
//	2) Post 메핑  : @RequestMapping 자식으로 
//                  Post 방식 요청을 연결하는 어노테이션
//	@PostMapping("/login")
	public String login(/*@RequestParam("inputEmail")*/ String inputEmail,
						/*@RequestParam("inputPw")*/ String inputPw) {
		
		// 파라미터 전달 방법 2 : @RequestParam 어노테이션 이용 (+ 생략방법)
		
		// @RequestParam 어노테이션
		// - request객체를 이용한 파라미터 전달 어노테이션 
		// - 매개변수 앞에 해당 어노테이션 작성하면, 매개변수에 값이 주입됨.
		
		// ** 파라미터의 name 속성값과 
		//    매개변수명이 같으면 @RequestParam 생략 가능! **
		
		// @RequestParam(value="name", required="fasle", defaultValue="1")
		// [속성]
		// value : 전달 받은 input 태그의 name 속성값
		// ex) 아이디 저장  :: 값이 없을 수도 있는 것.
		
		// required : 입력된 name 속성값 파라미터 필수 여부 지정(기본값 true)
		// -> required = true인 파라미터가 존재하지 않는다면 400 Bad Request 에러 발생
		// -> required = true인 파라미터가 null인 경우에도 400 Bad Request

		// defaultValue : 파라미터 중 일치하는 name 속성 값이 없을 경우에 대입할 값 지정.
		// -> required = false인 경우 사용
		
		System.out.println("inputEmail : " + inputEmail);
		System.out.println("inputPw : " + inputPw);
		
		return "redirect:/";
	}
	
	
//	3) ModelAttribute
	
//	@PostMapping("/login")
	public String login(@ModelAttribute Member inputMember) {
		
		// 파라미터 전달 방법 3 : @ModelAttribute 이용한 방법
		
		// - DTO(또는 VO)와 같이 사용하는 어노테이션
		
		// - 전달 받은 파라미터의 name 속성 값이 같이 사용되는 DTO의 필드명과 같다면
		//   자동으로 setter를 호출해서 필드에 값을 세팅
		
		System.out.println(inputMember);
		// Member [memberEmail = user123, memberPw = pass123..]
		
		// ** @ModelAttribute 사용 시 주의 사항 **
		// - DTO에 기본 생성자가 필수로 존재해야 한다.
		// - DTO에 setter가 필수로 존재해야 한다.
		
		// ** @ModelAttribute 어노테이션은 생략이 가능하다! ** 
		/*
		 * public String login(Member inputMember) 
		 */
		
		// ** @ModelAttribute를 이용해 값이 필드에 세팅된 객체를 
		// 커맨드 객체라고 부름.
		
		
		
		return "redirect:/";
	}
	
	
	/* 찐 로그인 메서드*/
		@PostMapping("/login")
		public String login(Member inputMember, Model model,
							@RequestHeader("referer") String referer,
							RedirectAttributes ra,
							@RequestParam(value = "saveId", required = false) String saveId,
							HttpServletResponse resp
				) { 
			
		// @ReqyestHeader(value = "referer") String referer	
		// -> 요청 HTTP header에서 "referer" ( 이전 주소 ) 값을 얻어와
		// 		매개변수 String referer에 저장 
		// ex) 글쓰기 -> 로그인 안되면 로그인 페이지 -> 로그인하면 다시 글쓰기로 돌아가야 할 때 사용!
		
		
		// Model : 데이터 전달용 객체
		// -> 데이터를 K : V 형식으로 담아 전달
		// -> 기본적으로 request scope
		// -> @SessionAttributes 어노테이션과 함께 사용 시, sessionScope로 바꿀 수 있음.
		
		
		//@RequestParam(value = "saveId", required = false) String saveId
		// -> name 속성값이 saveId인 파라미터를 전달받아서 저장
		//  required 미작성 시, 기본값 true임.
		// null값이 들어와도 괜찮다는 것을 표시한 것.
		// required= false  :: 필수 아님 ( null 허용 ) 
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		// DB 조회 결과 확인
//		System.out.println(loginMember);
		
		
		//  로그인 결과에 따라 리다이렉트 경로를 다르게 지정
		String path = "redirect:";
		
		if(loginMember != null) {// 로그인 성공시
			
			path += "/"; // 메인페이지로 리다이렉트
			
			
			//session loginMember 추가
			
			// Session에 로그인한 회원 정보 추가
			// Servlet에서 HttpSession.setAttribute(key, value)
			// Spring에서는 Model + @SessionAttributes 
			
			
			// 1) model에 로그인한 회원 정보 추가
			model.addAttribute("loginMember", loginMember);
			// -> 현재는 request scope
			
			// 2) 클래스 위에 @SessionAttributes 추가
			// -> 이제 session scope
			
			
			
			// ---------------------------
			// 아이디 저장 ( Cookie)
			/* Cookie란?
			 *  - 클라이언트 측(브라우저)에서 관리하는 파일
			 *  
			 *  - 쿠키파일에 등록된 주소 요청 시 마다
			 *     자동으로 요청에 첨부되어 서버로 전달됨.
			 *     
			 *  - 서버로 전달된 쿠키에 값 추가, 수정, 삭제 등을 진행한 후 
			 *     다시 클라이언트에게 반환함.
			 *   
			 */
			
			// Cookie 생성 (해당 쿠키에 담을 데이처를 k : v로 지정) 
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { //체크가 되었을 때 
				
				// 한 달 (30일) 동안 유지되는 쿠키 생성 
				cookie.setMaxAge(60*60*24*30); // 초단위 지정
				
				
				
			}else { // 체크가 안되었을 때 
				
				// 0초 동안 유지되는 쿠키 생성
				// => 기존에 쿠키가 있었을 수 있으므로 해당 쿠키를 삭제해야 함
				// ex) 내가 아이디 저장 누르고 로그인한 후에 로그아웃 후 아이디저장 체크 해제하고 로그인한 경우 이미 쿠키가 설정되어있는 것을 삭제함. 
				cookie.setMaxAge(0); // 초단위 지정
				
			}
			
			// 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 경로(주소)를 지정해야 함. 
			cookie.setPath("/"); // localhost :/ 이하 모든 주소
								// ex) / , /member/login, /member/logout 등 
								// 모든 요청에 쿠키를 첨부 
			// 쿠키는 클라이언트가 관리하는 객체
			// 응답객체(HttpServletResponse)를 이용해서 
			// 만들어진 쿠키를 클라이언트에게 전달
			resp.addCookie(cookie);
			
		}else { // 로그인 실패
		
			path += referer;
			
			// message 추가 ( 아이디 또는 비밀번호 불일치 )
			// 리다이렉트 시, 유지안되기 떄문에 session에 저장했었음.
			
			/* redirect(재요청) 시
			 * 기존 요청(request)이 사라지고
			 * 새로운 요청(request)을 만들게 되어
			 * redirect된 페이지에서는 이전 요청이 유지되지 않는 문제 발생
			 * 그러므로 유지하기 위해서는 session scope를 사용했었음.
			 * 
			 * 
			 * RedirectAttibutes를 스프링에서 제공
			 * - 리다이렉트 시 데이터를 request scope로 전달할 수 있게하는 객체
			 * 
			 * 응답 전 : request scope
			 * 
			 * 응답 중 : session scope로 잠시 이동 (페이지 넘어갈 때 !) 
			 * 
			 * 응답 후 : request scope로 복귀
			 * 
			 */
			
			// addFlashAttribute : 잠시 session에 추가
			ra.addFlashAttribute("message", "아이디 또는 비밀번호 불일치");
			
		}
		
		return path;
	}
	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		
//		session.invalidate(); // 세션 무효화
//		
//		return "redirect:/";
//	}
//	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:/";
	}
	
	
	
	/*
	 * 스프링 예외 처리 방법 (3종류, 중복 사용 가능) 
	 * 
	 * 1순위) 메서드 단위로 처리
	 * 		-> try - catch / throws 
	 * 
	 * 2순위) 클래스 단위로 처리
	 * 		-> @ExceptionHandler
	 * 
	 * 3순위) 프로그램 단위(전역) 처리
	 * 		-> @ControllerAdvice

	 */
	
	// 현재 클래스에서 발생하는 모든 예외를 모아서 처리 
//	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
									// 아규먼트에 의해서 매개변수 필요한거 가져다가 쓸 수 있음.
		
		// Exception e : 예외 정보를 담고있는 객체
		// Model model : 데이터 전달용 객체 ( request scope 기본 ) 
		e.printStackTrace(); // 예외 내용/발생 메서드 확인
		
		model.addAttribute("e", e); // 예외 발생 시 forward되는 페이지로 e를 전달함. (request scope도 가능함.) 
		
		// View Resolver의 prefix, suffix를 붙여서 JSP 경로를 만듦.
		return "common/error";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

