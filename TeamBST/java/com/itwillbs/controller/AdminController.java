package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.AdminDTO;
import com.itwillbs.domain.Game_scheduleDTO;
import com.itwillbs.domain.Notice_boardDTO;
import com.itwillbs.domain.UserDTO;
import com.itwillbs.service.AdminService;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// 서비스 객체 주입
	@Inject
	private AdminService aService;

	//http://localhost:8088/admin/admin
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public void adminMain_GET() {
		logger.debug("관리자 페이지 호출");

	}
	
	
	
	@RequestMapping(value="/adminNotice",method=RequestMethod.GET)
	public String adminNotice_GET(Model model) throws Exception{
		logger.debug("관리자 공지사항 리스트 호출");
		
		// 서비스 -> DB의 정보를 가져오기
		List<Notice_boardDTO> nBoardList = aService.NoticeList();
		logger.debug("size : "+ nBoardList.size());
		
		// 연결된 뷰페이지로 정보 전달
		model.addAttribute("nBoardList", nBoardList);
		
		return "/admin/adminNotice";

	}
	
	@RequestMapping(value="/adminNoticeWrite",method=RequestMethod.GET)
	public void adminNoticeWrite_GET() {
		logger.debug("관리자 공지사항 글쓰기 호출");
		logger.debug(" /adminNoticeWrite -> adminNoticeWrite_GET() 호출");
	}	
	
	@RequestMapping(value="/adminNoticeWrite",method=RequestMethod.POST)
	public String adminNoticeWrite_POST(Notice_boardDTO dto) {
		logger.debug("관리자 공지사항 글쓰기 호출");
		logger.debug(" /adminNoticeWrite -> adminNoticenWrite_POST() 호출");
		
		logger.debug("dto : "+ dto);
		
		aService.NoticeJoin(dto);
		
		return "redirect:/admin/adminNotice";
	}	
	
	
	
	@RequestMapping(value="/adminMember",method=RequestMethod.GET)
	public void adminMember_GET() {
		logger.debug("관리자 회원현황 호출");

	}
	
	@RequestMapping(value="/adminGeneralMember",method=RequestMethod.GET)
	public String adminGeneralUserList_GET(Model model) throws Exception {
		logger.debug("관리자 일반회원 호출");
		
		List<UserDTO> generalMemberList = aService.generalMemberList();
		
		model.addAttribute("generalMemberList", generalMemberList);
		
		return "/admin/adminGeneralMember";
		
	}	
	
	@RequestMapping(value="/adminWithdrawMember",method=RequestMethod.GET)
	public String adminWithdrawMember_GET(Model model) throws Exception {
		logger.debug("관리자 탈퇴회원 호출");
		
		List<UserDTO> withdrawMemberList = aService.withdrawMemberList();
		

		logger.debug("dd"+withdrawMemberList);
		
		model.addAttribute("withdrawMemberList", withdrawMemberList);
		
		return "/admin/adminWithdrawMember";
		
		
	}

	
	
	@RequestMapping(value="/adminSchedule",method=RequestMethod.GET)
	public void adminSchedule_GET(Model model) throws Exception {
		logger.debug("관리자 경기일정 호출");

		// 서비스 -> DB의 정보를 가져오기
		List<Game_scheduleDTO> gScheduleList = aService.ScheduleList();
		logger.debug("size : "+ gScheduleList.size());
		
		// 연결된 뷰페이지로 정보 전달
		model.addAttribute("gScheduleList", gScheduleList);

	}	
	
	@RequestMapping(value="/adminScheduleUpload",method=RequestMethod.GET)
	public void adminScheduleUpload_GET() {
		logger.debug("관리자 경기일정 업로드 호출");
		logger.debug(" /adminScheduleUpload -> adminScheduleUpload_GET() 호출");
		
	}
	
	@RequestMapping(value="/adminScheduleUpload",method=RequestMethod.POST)
	public String adminScheduleUpload_POST(Game_scheduleDTO dto) {
		logger.debug("관리자 경기일정 업로드 호출");
		logger.debug(" /adminScheduleUpload -> adminScheduleUpload_POST() 호출");
		
		logger.debug("dto : "+ dto);
		
		aService.ScheduleJoin(dto);
		
		return "redirect:/admin/adminSchedule";
		
	}
	
	// 경기 일정 수정 GET / 페이지 호출
	@RequestMapping(value="/adminScheduleUpdate",method=RequestMethod.GET)
	public String  adminScheduleUpdate_GET(@RequestParam("game_id") String game_id, Model model) {
		logger.debug(" /adminScheduleUpdate -> adminScheduleUpdate_GET() 호출");
		logger.debug(" @@@@@@@@@@@@@@@@@@@@@@@ "+ game_id);

		// 서비스 -> DB의 정보를 가져오기
		Game_scheduleDTO gScheduleOne = aService.ScheduleOne(game_id);
		
		logger.debug("gScheduleOne : "+ gScheduleOne);
		
		// 연결된 뷰페이지로 정보 전달
		model.addAttribute("gScheduleOne", gScheduleOne);
	
	    return "admin/adminScheduleUpdate"; 
	}
	
	
	@RequestMapping(value="/adminScheduleUpdate", method=RequestMethod.POST)
	public String adminScheduleUpdate_POST(Game_scheduleDTO dto) {
	    logger.debug(" /adminScheduleUpdate -> adminScheduleUpdate_POST() 호출");
	    logger.debug("dto : " + dto);
	    
	    aService.updateSchedule(dto);
	    
	    return "redirect:/admin/adminSchedule";
	}
	
	
	
	@RequestMapping(value="/adminBulletin",method=RequestMethod.GET)
	public void adminWithdrawBulletin_GET() {
		logger.debug("관리자 문의 게시판 호출");

	}
	
	@RequestMapping(value="/adminBulletinWrite",method=RequestMethod.GET)
	public void adminBulletinWrite_GET() {
		logger.debug("관리자 문의 게시판 답변 글쓰기 호출");
	}	
	

	
	// 일반회원의 예매 내역 조회
	
	@GetMapping(value="/adminMemberTicket")
	public void adminMemberticketing(@RequestParam("user_id") String user_id, Model model) throws Exception {
		logger.debug("관리자 - 일반회원의 예매 내역 리스트 조회");
		
		logger.debug("user_id = "+user_id);
		
		List<Game_scheduleDTO> memberTicketingList = aService.memberTicketingList(user_id);
		
		// 연결된 뷰페이지로 정보 전달
		model.addAttribute("memberTicketingList", memberTicketingList);
		
	}
	
	
	
	
	
	
	
	
}
