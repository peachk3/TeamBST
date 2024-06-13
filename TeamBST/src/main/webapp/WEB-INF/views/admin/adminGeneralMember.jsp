<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 홈페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="../resources/css/index.css" rel="stylesheet">
</head>
<body>
    <nav>
        <a href="/main/main"><h1>개바리조</h1></a>
        
        <h6>
          <a href="/login/login">로그인/</a>
          <a href="/mypage/mypage">마이페이지/</a>
          <a href="/admin/admin">관리자페이지/</a>
          로그인시간 ( 58: 00 ) 
        </h6>
      </nav>
  
      

      <ul class="nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/admin/adminSchedule">경기일정</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/admin/adminMember">회원</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/admin/adminNotice">고객센터</a>
        </li>
      </ul>
      
      <hr>




      <button type="button" class="community" onclick="location.href='/admin/adminMember'">회원현황</button> <br>
      <button type="button" class="community" onclick="location.href='/admin/adminGeneralMember'">일반회원</button> <br>
      <button type="button" class="community" onclick="location.href='/admin/adminWithdrawMember'">탈퇴회원</button>


<table border="1">
	<tr>
		<td>no</td>
		<td>아이디</td>
		<td>이름</td>
		<td>닉네임</td>
		<td>이메일</td>
		<td>전화번호</td>
		<td>가입일자</td>
	</tr>
	<c:forEach var="vo" items="${generalMemberList}">
		<tr>
			<td>넘버</td>
			<td>${vo.user_id }</td>
			<td>${vo.user_name }</td>
			<td>${vo.user_nick }</td>
			<td>${vo.user_phone }</td>
			<td>${vo.user_email }</td>
			<td>${vo.user_birth }</td>
		</tr>
	</c:forEach>
</table>










</body>
</html>