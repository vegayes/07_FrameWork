package edu.kh.project.member.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.EmailDAO;

@Service
public class EmailServiceImpl implements EmailService  {

	@Autowired
	private EmailDAO dao;
	
	// 자바메일Sender Bean 객체 가져오기
	@Autowired
	private JavaMailSender mailSender;

	
	private String fromEmail = "yueumseo123@gmail.com";
	private String fromUsername = "Board_Project";
	
	
    public String createAuthKey() {
        String key = "";
        for(int i=0 ; i< 6 ; i++) {
            
            int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
            
            if(sel1 == 0) {
                
                int num = (int)(Math.random() * 10); // 0~9
                key += num;
                
            }else {
                
                char ch = (char)(Math.random() * 26 + 65); // A~Z
                
                int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
                
                if(sel2 == 0) {
                    ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
                }
                
                key += ch;
            }
            
        }
        return key;
    }

    @Transactional
	@Override
	public int signUp(String email, String title) {
									// 회원가입
		// 6자리 난수 인증번호 생성
		//난수 생성
		String authKey = createAuthKey();
		
		// 1) 메일로 난수 보내기
        try {

            //인증메일 보내기 (API)
            MimeMessage mail = mailSender.createMimeMessage();
            
            // 제목
            String subject = "[Board Project]"+title+" 인증코드";
            
            // 문자 인코딩
            String charset = "UTF-8";
            
            // 메일 내용
            String mailContent 
                = "<p>Board Project "+title+" 인증코드입니다.</p>"
                + "<h3 style='color:blue'>" + authKey + "</h3>";
            
            
            
            // 송신자(보내는 사람) 지정
            mail.setFrom(new InternetAddress(fromEmail, fromUsername));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            
            // 수신자(받는사람) 지정
            
            // 이메일 제목 세팅
            mail.setSubject(subject, charset);
            
            // 내용 세팅
            mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
            
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("authKey", authKey);
        map.put("email", email);
        
        System.out.println(map);
		// 2)DB로 보내기        
        
        int result = dao.updateAuthKey(map);
        
        System.out.println("DB로 업데이트 하기 : " + result);
        
        if(result == 0) {
        	// 한번도 인증받지 못한 이메일인지? 
        	result = dao.insertAuthKey(map);
        }
        

        return result;
	}


	@Override
	public int checkAuthKey(String checkAuthKey) {

		System.out.println("checkAuthKey 서비스 진입");
		
		
		System.out.println(checkAuthKey);
		
		int result = dao.checkAuthKey(checkAuthKey);
		
		System.out.println(result);
		
		return result;
	}
	

	
	
	
	
	
	
	
}
