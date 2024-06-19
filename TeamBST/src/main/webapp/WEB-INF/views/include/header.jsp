<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>개바리조</title>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js'
			integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=='
			crossorigin='anonymous'></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="../resources/css/index.css" rel="stylesheet">
    <style>
        .logout-button {
            background: none;
            border: none;
            color: inherit;
            font: inherit;
            cursor: pointer;
            text-decoration: none;
            padding: 0;
    </style>
</head>
<body>
<nav>
		<a href="/main/main"><h1>개바리조</h1></a>
		<h6>
			<c:choose>
				<c:when test="${empty sessionScope.user_id}">
					<!-- 로그인하지 않은 경우 -->
					<a href="/login/loginPage">로그인</a>
				</c:when>
				<c:when test="${sessionScope.user_id == 'admin'}">
					<!-- 관리자일 경우 -->
					<a href="/admin/admin">관리자페이지</a>
					<form id="logoutForm" action="${pageContext.request.contextPath}/login/logout" method="post" style="display: inline;">
                    <button type="submit" class="logout-button">로그아웃</button>
                    </form>
				</c:when>
				<c:otherwise>
					<!-- 일반사용자일 경우 -->
					<a href="/mypage/mypage">마이페이지</a>
					<form id="logoutForm" action="${pageContext.request.contextPath}/login/logout" method="post" style="display: inline;">
                    <button type="submit" class="logout-button">로그아웃</button>
                    </form>
				</c:otherwise>
			</c:choose>
			로그인시간 ( 58: 00 )
		</h6>
		<ul class="nav">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/ticketing/ticketing?stad_id=All">예매</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/community/community">거래</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/announcement/announcement">고객센터</a>
        </li>
    </ul>
<div class="hamburger-menu">
        <button id="hamburger-button" class="hamburger-button">
            ☰
        </button>
        <div id="hamburger-dropdown" class="hamburger-dropdown">
            <div class="menu-columns">
                <div class="menu-column">
					<p><a href="/main/main#stadium">구장</a></p>
                    <a href="#" data-stadium-id="GC">고척 스카이돔</a>
                    <a href="#" data-stadium-id="GJ">광주 챔피언스 필드</a>
                    <a href="#" data-stadium-id="DG">대구 라이온즈파크</a>
                    <a href="#" data-stadium-id="DJ">대전 이글스파크</a>
                    <a href="#" data-stadium-id="SJ">부산 사직야구장</a>
                    <a href="#" data-stadium-id="JS">서울 잠실야구장</a>
                    <a href="#" data-stadium-id="SW">수원 KT위즈파크</a>
                    <a href="#" data-stadium-id="IC">인천 랜더스필드</a>
                    <a href="#" data-stadium-id="CW">창원 NC파크</a>
                </div>
                <div class="menu-column">
                    <p><a href="/main/team">구단</a></p>
                    <a href="/main/teamPage?team_id=kia">KIA</a>
                    <a href="/main/teamPage?team_id=lg">LG</a>
                    <a href="/main/teamPage?team_id=samsung">삼성</a>
                    <a href="/main/teamPage?team_id=dosan">두산</a>
                    <a href="/main/teamPage?team_id=ssg">SSG</a>
                    <a href="/main/teamPage?team_id=kt">KT</a>
                    <a href="/main/teamPage?team_id=hanhwa">한화</a>
                    <a href="/main/teamPage?team_id=lotte">롯데</a>
                    <a href="/main/teamPage?team_id=kiwoom">키움</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<hr>

<!-- Hidden form for POST request -->
<form id="stadiumForm" action="/ticketing/ticketing" method="post" style="display: none;">
    <input type="hidden" name="stad_id" id="stadiumInput">
</form>


<script>
    document.getElementById('hamburger-button').onclick = function(event) {
        event.stopPropagation();
        var dropdown = document.getElementById('hamburger-dropdown');
        dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
    };

    window.onclick = function(event) {
        var dropdown = document.getElementById('hamburger-dropdown');
        if (dropdown.style.display === 'block' && !event.target.closest('.hamburger-menu')) {
            dropdown.style.display = 'none';
        }
    };

    document.querySelectorAll('.menu-column a[data-stadium-id]').forEach(anchor => {
        anchor.addEventListener('click', function(event) {
            event.preventDefault();
            const stadiumId = this.getAttribute('data-stadium-id');
            document.getElementById('stadiumInput').value = stadiumId;
            document.getElementById('stadiumForm').submit();
        });
    });
    
    
    document.querySelectorAll('.container a').forEach(anchor => {
        anchor.addEventListener('click', function(event) {
            event.preventDefault();
            const stadiumId = this.getAttribute('data-stadium-id');
            document.getElementById('stadiumInput').value = stadiumId;
            document.getElementById('stadiumForm').submit();
        });
    });

    // Optional: Smooth scrolling
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });
    
    
</script>