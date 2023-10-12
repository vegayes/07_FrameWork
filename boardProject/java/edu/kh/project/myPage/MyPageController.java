package edu.kh.project.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@RequestMapping("/myPage")
@Controller
@SessionAttributes({"loginMember"})
// 1) Model에 세팅된 값의 key와 {} 작성된 값이 일치하면 Session scope로 등록
// 2) Session으로 올려둔 값을 해당 클래스에서 얻어와 사용 가능하게함.
// ===> @SessionAttribute(key)로 사용
public class MyPageController {
	
		@Autowired
		private MemberService service;
	

		// 내 정보 페이지로 이동
		@GetMapping("/info")
		public String info() {
			return "myPage/myPage-info";
		}
		
		//프로필 페이지 이동
		@GetMapping("/profile")
		public String profile() {
			return "myPage/myPage-profile";
		}
	
		
		// 비밀번호 변경 페이지 이동
		@GetMapping("/changePw")
		public String changePw() {
			return "myPage/myPage-changePw";
		}
		
		
		// 탈퇴 페이지 이동
		@GetMapping("/secession")
		public String secession() {
			return "myPage/myPage-secession";
		}
		
		
		// 회원 정보 수정
		@PostMapping("/info")
		public String updateInfo(@SessionAttribute("loginMember") Member loginMember,
							// SessionAttribute  :: 세선에서 얻어옴
								Member updateMember, 
								String[] memberAddress,
								RedirectAttributes ra) {
					
			String message = null;
			
			/*
			 * @SessionAttribute("loginMember") Member loginMember
			 * :: Session에서 얻어온 "loginMember"에 해당하는 객체를
			 *    매개변수 Member loginMember에 저장
			 *    
			 *  Member updateMember
			 *  : 수정할 닉네임, 전화번호 담긴 커멘드 객체 
			 *  
			 *  
			 *  Sring[] memberAddress
			 *  : name ="memberAddress"인 input 3개의 값(주소)
			 *
			 * 
			 * RedirectAttributes ra : 리다이렉트 시 값 전달용 객체 (메시지 작성) 
			 */
			
			System.out.println("수정하기 loginMember" + loginMember);
			System.out.println("수정하기 updateMember" + updateMember);
			System.out.println("수정하기 memberAddress" + memberAddress);
			System.out.println("수정하기 ra" + ra);
			
			// 1) 주소 하나로 합치기 (a^^^b^^^c)
			if(updateMember.getMemberAddress().equals(",,")) {
				updateMember.setMemberAddress(null);
			}else {
				
				String addr = String.join("^^^", memberAddress);

				// 2) updateMember에 주소문자열 세팅
				updateMember.setMemberAddress(addr);
			}
			
			System.out.println("수정된 update" + updateMember);
			
			
			// 3) 로그인한 회원 번호를 updateMember에 세팅
			updateMember.setMemberNo(loginMember.getMemberNo());
			
			
			
			// 4) DB 회원 정보 수정(update) 서비스 호출
			int updateResult = service.update(updateMember);
			
			// 5) 결과값으로 성공 , 실패에 따른 처리
			// 성공 시, Session에 로그인된 회원 정보도 수정(동기화)
			// 실패에 따른 처리
			
			if(updateResult == 1) {
				System.out.println("수정 성공");
				loginMember.setMemberNickname(updateMember.getMemberNickname());
				loginMember.setMemberTel(updateMember.getMemberTel());
				loginMember.setMemberAddress(updateMember.getMemberAddress());
				
				message = "수정 성공";
				
				ra.addFlashAttribute("message", message);
				
			}else {
				System.out.println("수정 실패");
				
				message = "수정 실패";
				
				ra.addFlashAttribute("message", message);
			}
			
			
			
			
			return "redirect:info"; // 상대 경로 (절대경로 ::  /myPage/info)
		}
}
