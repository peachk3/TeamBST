<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation Page</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="/../../resources/css/index.css" rel="stylesheet">
</head>


<body>

	<style>
	
	.nav {
		text-align: left;
	
	}
	.info-panel {
		text-align: right;
	}
	
	</style>


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
    <h1>예매 </h1>

	<p>Stadium ID : ${stad_id }</p>
	<p>Game ID : ${game_id }</p>
	<p>Zone ID : ${stad_id}${zone_ty}</p>
	<p>Seat ID : ${seat_id }</p>

    <h1> 선택한 인원 수 </h1>
    
  	<label>성인: </label>
 	<form>
		<select name="adultNum" id="adultNum">
			<option value="">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select>
	</form>
    
    <label>청소년: </label>
     <form>
		<select name="childNum" id="childNum">
			<option value="">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select>
	</form>
	
	
    
    <form action="/payment" ><!-- method="post" -->
        <input type="hidden" name="schedule_id" value="${schedule.schedule_id}">
        <input type="hidden" name="seat_id" value="${seat.seat_id}">
        <button type="submit" id="payment"  onclick="location.href='/ticketing/payment'">결제하기</button>
    </form>
    
    <div id="selectedCounts">
        <p>선택한 성인 수: <span id="selectedAdult">0</span></p>
        <p>선택한 청소년 수: <span id="selectedChild">0</span></p>
    </div>
    
     <p>Your selected seats: <span th:text="${selectedSeats}"></span></p>
     
     
    <div class="info-panel">
        <h2>경기 정보</h2>
        <p>팀: <span id="team-name">팀 A vs 팀 B</span></p>
        <p>이용자명: <span id="user-name">홍길동</span></p>
        <p>일시: <span id="match-date">2023-07-15 18:00</span></p>
        <p>선택 좌석: <span id="selected-seats">없음</span></p>
        <p>티켓 금액: <span id="ticket-price">50000원</span></p>
        <p>예매 수수료: <span id="booking-fee">5000원</span></p>
        <p>결제 금액: <span id="total-amount">55000원</span></p>
    </div>
     
    <script type="text/javascript">
 	// 성인 수 선택 요소
    const adultSelect = document.getElementById('adultNum');
    // 청소년 수 선택 요소
    const childSelect = document.getElementById('childNum');

    // 선택된 성인 수를 화면에 업데이트하는 함수
    function updateSelectedAdultCount() {
        const selectedAdultCount = document.getElementById('selectedAdult');
        selectedAdultCount.textContent = adultSelect.value;
    }

    // 선택된 청소년 수를 화면에 업데이트하는 함수
    function updateSelectedChildCount() {
        const selectedChildCount = document.getElementById('selectedChild');
        selectedChildCount.textContent = childSelect.value;
    }

    // 성인 수 선택 요소의 변경 이벤트 리스너 추가
    adultSelect.addEventListener('change', updateSelectedAdultCount);
    // 청소년 수 선택 요소의 변경 이벤트 리스너 추가
    childSelect.addEventListener('change', updateSelectedChildCount);

    // 초기 페이지 로딩 시 선택된 값으로 업데이트
    updateSelectedAdultCount();
    updateSelectedChildCount();
    
    </script>
    
</body>
</html>