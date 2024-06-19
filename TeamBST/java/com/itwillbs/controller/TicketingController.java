package com.itwillbs.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itwillbs.domain.Game_scheduleDTO;
import com.itwillbs.domain.SeatDTO;
import com.itwillbs.domain.ZoneDTO;
import com.itwillbs.service.ScheduleService;
import com.itwillbs.service.StadiumService;

@Controller
@RequestMapping(value="/ticketing/*")
public class TicketingController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);
	
	@Inject
	private ScheduleService sService;
	
	
    @RequestMapping(value="/ticketing", method=RequestMethod.GET)
    public String MainTicketing(@RequestParam("stad_id") String stad_id, Model model) {
        logger.debug("티켓팅 홈 페이지");
        logger.debug("stad_id : " + stad_id);

        List<Game_scheduleDTO> TeamScheduleList = sService.stadScheduleList(stad_id);
        model.addAttribute("TeamScheduleList", TeamScheduleList);
        model.addAttribute("selectedStadium", stad_id); // 선택한 stadium id를 모델에 추가

        return "ticketing/ticketing"; // 실제 JSP 페이지 이름으로 변경하세요
    }
	
	
	
	@RequestMapping(value="/ticketing",method=RequestMethod.POST)
	@ResponseBody
	 public String TeamTicketing(@RequestParam("stad_id") String stad_id, Model model) throws Exception{
        logger.debug("티켓팅 홈 페이지");
        logger.debug("stad_id : " + stad_id);

        List<Game_scheduleDTO> TeamScheduleList = sService.stadScheduleList(stad_id);
        model.addAttribute("TeamScheduleList", TeamScheduleList);
        model.addAttribute("selectedStadium", stad_id); // 선택한 stadium id를 모델에 추가
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Java 8 날짜 타입 지원
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // 타임스탬프로 변환하지 않음
        // 원하는 날짜 포맷 설정 (예: "yyyy-MM-dd")
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        String jsonTeamScheduleList = mapper.writeValueAsString(TeamScheduleList);
        logger.debug("jsonTeamScheduleList : " + jsonTeamScheduleList);

        return jsonTeamScheduleList;

//        return "redirect:/ticketing/ticketing?stad_id=" + stad_id; // 선택한 stadium id와 함께 GET 요청으로 리다이렉트
    }
	
	
	
	// 경기 정보 페이지
	@RequestMapping(value="/gameInfo",method=RequestMethod.GET)
	public String gameInfo(@RequestParam("game_id") String game_id, Model model) {
		// gameId를 사용하여 필요한 로직을 수행합니다.
        // 예: 게임 정보를 조회하고 모델에 추가
		
		logger.debug("게임 정보 페이지 호출");
		List<Game_scheduleDTO> homeTeamInfoList = sService.homeTeamInfoList(game_id);
		List<Game_scheduleDTO> awayTeamInfoList = sService.awayTeamInfoList(game_id);
		
		model.addAttribute("homeTeamInfoList", homeTeamInfoList);
		model.addAttribute("awayTeamInfoList", awayTeamInfoList);
		
		 
		// 새로운 JSP 페이지로 이동
		return "/ticketing/gameInfo"; // 반환할 JSP 페이지 이름
	}
	
	
	
	@Inject
	private StadiumService stadService;
	
	// 구장 배치도 (구역 선택)
 	@RequestMapping(value="/stadium", method = RequestMethod.GET)
	public String goStadiumGET(HttpSession session,@RequestParam("stad_id") String stad_id, Model model) {
        String user_id = (String) session.getAttribute("user_id");
        
        logger.debug("user_id : "+ user_id);
 		
        if(user_id != null) {
        	model.addAttribute("stad_id",stad_id);
        	logger.debug("goStadiumGET() 호출");
        
        	return "/ticketing/stadium";
        } else {
        	
        	logger.debug("로그인을 해야 예매하기를 할 수 있습니다");
        	
        	return "/login/loginPage";
        }
        
	}
 	
	
	@GetMapping("/stadium/{stad_id}/{game_id}")
	public String goStadium(HttpSession session,@PathVariable("stad_id") String stad_id, @PathVariable("game_id") String game_id, Model model) {
		String user_id = (String) session.getAttribute("user_id");
	        
        logger.debug("user_id : "+ user_id);

		List<ZoneDTO> zones = stadService.getZonesByStadiumId(stad_id);
		if(user_id != null) {
        	model.addAttribute("stad_id", stad_id);
        	model.addAttribute("game_id", game_id);
        	model.addAttribute("zones", zones);
        	
        	return "/ticketing/stadium";
        } else {
        	
        	logger.debug("로그인을 해야 예매하기를 할 수 있습니다");
        	
        	return "redirect:/login/loginPage";
        }
	}
	
	
	@GetMapping("/displaySeats/{stad_id}/{game_id}/{zone_ty}/{zone_id}")
	public String goZone(@PathVariable("stad_id") String stad_id, @PathVariable("game_id") String game_id, @PathVariable("zone_ty") String zone_ty, @PathVariable("zone_id") String zone_id, Model model) {
		List<SeatDTO> seats = stadService.getSeatsByZone(zone_ty, zone_id);
		
		model.addAttribute("stad_id", stad_id);
		model.addAttribute("game_id", game_id);
		model.addAttribute("zone_ty", zone_ty);
//		model.addAttribute("zone_id", zone_id);
		model.addAttribute("seats", seats);
		return "/ticketing/displaySeats";
	}	
	
	@GetMapping(value="/reservation/{stad_id}/{game_id}/{zone_ty}/{seat_row}/{seat_num}/{seat_id}")
	public String bookTicket(@PathVariable("stad_id") String stad_id, @PathVariable("game_id") String game_id, @PathVariable("zone_ty") String zone_ty, @PathVariable("seat_id") String seat_id, Model model) {
		List<SeatDTO> seats = stadService.getSeatsId(seat_id);
	
		model.addAttribute("stad_id", stad_id);
		model.addAttribute("game_id", game_id);
		model.addAttribute("zone_ty", zone_ty);
//		model.addAttribute("zone_id", zone_id);
		// model.addAttribute("seat_id", seat_id);
		model.addAttribute("seats", seats);
		
		logger.debug("bookTicket() 호출 ");
		
		return "/ticketing/reservation";
	}
	
	// 여기까지 성공
//	public String goSeat(@PathVariable)
	
	
//	@GetMapping(value="/displaySeats")
//	public void goZoneGET(/*@RequestParam("zone_id") String zone_id*/) {
//		
//		logger.debug("goZoneGET() 호출");
//	}
//	@GetMapping(value="/displaySeats/{stad_id}")
//	public void goZoneGET(@PathVariable String stad_id, @RequestParam String game_id, Model model) {
//		List<ZoneDTO> zones = stadService.get
// 		logger.debug("goZoneGET() 호출");
//	}
//	
	
	

	@PostMapping(value="/payment")
	public void payTicket() {
		logger.debug("payTicket()");
	}
	
//	@GetMapping(value="/displaySeats")
//	public void goZoneGET(@RequestParam("zone_id") String zone_id, Model model) {
//		List<SeatDTO> seats = stadService.getSeatsByZoneId(zone_id);
//		model.addAttribute("seats", seats);
//		model.addAttribute("zone_id", zone_id );
//		
//		logger.debug(" goZoneGET() 호출");
//	}
//	
	
//	@GetMapping(value="/seats")
//	public String goZoneGET(@RequestParam("zone_id") String zone_id, Model model) {
//		List<SeatDTO> seats = stadService.getSeatsByZoneId(zone_id);
//		model.addAttribute("seats", seats);
//		model.addAttribute("zone_id", zone_id); // zone_id 전달
//		logger.debug(" goZoneGET 호출 ");
//		
//		return "seats";
//	}
//	
	
//	 @GetMapping(value = "/ticketing/zones/{stad_id}")
//	    public String zonesPage(@PathVariable String stad_id, @RequestParam int schedule_id, Model model) {
//	        List<ZoneDTO> zones = stadService.getZonesByStadiumId(stad_id);
//	        model.addAttribute("zones", zones);
//	        model.addAttribute("stad_id", stad_id); // 추가된 부분: stad_id 전달
//	        model.addAttribute("schedule_id", schedule_id); // 추가된 부분: schedule_id 전달
//	        return "zones";
//	    }
//
//	    @GetMapping("/ticketing/seats/{zone_id}")
//	    public String seatsPage(@PathVariable String zone_id, @RequestParam int schedule_id, Model model) {
//	        List<SeatDTO> seats = stadService.getSeatsByZoneId(zone_id);
//	        model.addAttribute("seats", seats);
//	        model.addAttribute("zone_id", zone_id); // 추가된 부분: zone_id 전달
//	        model.addAttribute("schedule_id", schedule_id); // 추가된 부분: schedule_id 전달
//	        return "seats";
//	    }

//	    @GetMapping("/reservation")
//	    public String reservationPage(@RequestParam int schedule_id, @RequestParam int seat_id, Model model) {
////	        ScheduleDTO schedule = sService.getScheduleById(schedule_id);
////	        SeatDTO seat =  sService.getSeatById(seat_id);
//	        model.addAttribute("schedule", schedule);
//	        model.addAttribute("seat", seat);
//	        return "reservation";
//	    }

	

//	
//	// 구역 -> 좌석 선택
//	@RequestMapping(value="/displaySeats", method = RequestMethod.GET)
//	public void selectSeat(@RequestParam("zone_id") String zone_id, Model model) {
//		
//		model.addAttribute("zone_id", zone_id);
//		
//		logger.debug("selectSeat() 호출");
//	}
//	
	
//	@RequestMapping(value="/stadium", method = RequestMethod.POST)
//	public String getStadium(@RequestParam("stad_id") String stad_id, Model model) {
//		logger.debug("구단 좌석 배치도 페이지 호출");
//		
//		Game_scheduleDTO stadium = stadService.getStadiumById(stad_id);
////		List<ZoneDTO> zones = stadService.getZonesByStadiumId(stad_id);
//		model.addAttribute("stadium", stadium);
////		model.addAttribute("zones", zones);
//		
//		return "/ticketing/stadium";
//	}
	
	
}
