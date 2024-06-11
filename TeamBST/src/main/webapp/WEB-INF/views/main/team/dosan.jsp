<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>두산 구단페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="../../resources/css/index.css" rel="stylesheet">
</head>

<body>
	<nav>
		<a href="/main/main"><h1>개바리조</h1></a>

		<h6>
			<a href="/login/login">로그인/</a> <a href="/mypage/mypage">마이페이지/</a> <a href="/admin/admin">관리자페이지/</a> 로그인시간 ( 58: 00 )
		</h6>
	</nav>


	<ul class="nav">
		<li class="nav-item"><a class="nav-link active" aria-current="page" href="/ticketing/ticketing">예매</a></li>
		<li class="nav-item"><a class="nav-link" href="/community/community">거래</a></li>
		<li class="nav-item"><a class="nav-link" href="/announcement/announcement">고객센터</a></li>
	</ul>

	<hr>



	<img src="../../resources/team/dosan.png" alt="dosan">
	<br>
	<img src="../../resources/stadium/jamsil.webp" alt="jamsil">




	<table border="1">
		<tr>
			<td>경기번호</td>
			<td>경기일자</td>
			<td>경기시작시간</td>
			<td>구장</td>
			<td>어웨이팀</td>
			<td>홈팀</td>
			<td>예매하기</td>
		</tr>

		<c:forEach var="vo" items="${GameScheduleList}">
			<tr>
				<td>${vo.game_id }</td>
				<td>${vo.game_date }</td>
				<td>${vo.game_time}</td>
				<td>${vo.stad_id }</td>
				<td>${vo.away_team_id}</td>
				<td>${vo.home_team_id }</td>
			<td><a href="#">예매하기</a></td>
			</tr>
		</c:forEach>

	</table>








	<script type="text/javascript" src="../resources/js/slide.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>