
const loginFrm = document.getElementById("loginFrm");

const memberEmail = document.querySelector("#loginFrm input[name='memberEmail']");
const memberPw = document.querySelector("#loginFrm input[name='memberPw']");

if(loginFrm != null){
    // 로그인 시도를 할 때
    loginFrm.addEventListener("submit", e => {

        // 이메일이 입력되지 않은 경우
        // 문자열.trim() : 문자열 좌우 공백 제거
        if(memberEmail.value.trim().length == 0){
            alert("이메일을 입력해주세요.");

            memberEmail.value = ""; // 잘못 입력된 값(공백) 제거
            memberEmail.focus(); // 이메일 input태그에 초점을 맞춤

            e.preventDefault(); // (기본이벤트 제거 : 제출 못하게하기)
            return; 
        }


        // 비밀번호가 입력되지 않은 경우
        if(memberPw.value.trim().length == 0){
            alert("비밀번호를 입력해주세요.");

            memberPw.value = ""; // 잘못 입력된 값(공백) 제거
            memberPw.focus(); // 이메일 input태그에 초점을 맞춤

            e.preventDefault(); // 제출 못하게하기
            return; 
        }


    });
}


//-------------------------------------------------------
    // fetch API : 웹 브라우저에서 서버로 HTTP 요청을 하게해주는 최신 인터페이스

/*
    fetch(url)
    .then(response => response.json() / response.text())
    .then(data => console.log(data))
    .catch(error => console.log(error));


     첫 번쨰 then()함수는 서버 요청에 대한 응답이 왔을때 실행됨
     - 응답받은 데이터가 반환되는 값이 단순 자료형 1개면 text()
     - 객체(Map)이면 json()으로 파싱한 후 다음 then() 함수로 넘겨줌. (파싱)

     두 번째 then()함수는 response.json()/text()으로 상황에 맞게 
     데이터가 파싱 완료되면 실행
     파싱된 데이터가 전달되면, 이 값을 로직에 맞게 가공함. (가공)

     세 번째 에러 발생 

 */

// 닉네임이 일치하는 회원의 전화번호 조회
const inputNickname = document.getElementById("inputNickname");
const btn1 = document.getElementById("btn1");
const result1 = document.getElementById("result1");

btn1.addEventListener("click", () => {

    // fetch API를 이용해서 ajax

    // GET 방식 요청 (파라미터를 쿼리스트링으로 추가)

    // Promise : 비동기 함수 호출 또는 연산이 완료되었을 때 
    //           이후에 처리할 함수나 에러를 처리하기 위한 
    //           함수를 설정하는 모듈
    //           --> 비동기 연산의 최종 결과 객체 


    fetch("/selectMemberTel?nickName=" + inputNickname.value )
    .then(resp => resp.text())  // 응답 객체(자료형 1일때)를 문자열 형식으로 파싱
    .then(data => { 
        // 데이터 가공
        console.log(data);
        result1.innerText = data;
    })
    .catch(err => console.log(err)); // 에러 발생 시, console에 에러 내용 출력

});

















































