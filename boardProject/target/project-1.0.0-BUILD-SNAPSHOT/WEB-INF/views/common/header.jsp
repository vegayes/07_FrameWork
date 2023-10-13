<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/main-style.css">
<header>
      <!-- 클릭 시, 메인 페이지로 이동하는 로고 -->
   <section>
       <a href ="/">
           <img src = "/resources/images/logo.jpg" id = "homeLogo">
       </a>
   </section>

   <!-- 검색창 부분 -->
   <section>
       <section class = "search-area">
           <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
           <form action = "/search" name = "search-form" method="get">
               <!-- fieldset : form 내부에서 input을 종류별로 묶는 용도로 자주 사용 -->
               <fieldset>
                   <!--  search : (text기능과 동일함) / 브라우저에 의해 다르게 표현될 수  있음.  -->
                   <!--  autocomplete : HTML 기본 자동완성 사용 X  -->
                   <input type = "search" id = "query" name = "query"
                       autocomplete = "off" placeholder = "회원을 닉네임으로 검색해주세요.">

                   <button id = "searchBtn" class="fa-solid fa-magnifying-glass"></button>
               </fieldset>
           </form>
       </section>
   </section>

   <section>
       <!-- 정렬을 수월하게 하기 위해서 3개의 section나눔. -->
    </section>
</header>

     <!-- 보통은 nav를 header태그 안에서 만듦. (nav가 사이드에 있는경우 header에 사용 X) -->
<nav>
    <ul>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">자유게시판</a></li>
        <li><a href="#">질문게시판</a></li>
        <li><a href="#">FAQ</a></li>
        <li><a href="#">1:1문의</a></li>
    </ul>
</nav>
