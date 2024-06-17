

package com.itwillbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.Game_scheduleDTO;
import com.itwillbs.domain.SeatDTO;
import com.itwillbs.domain.ZoneDTO;

@Service
public interface StadiumService {

	
//	public Game_scheduleDTO getStadiumById(String stad_id);
	
//	public ZoneDTO getZonesByStadiumId(String Zone_id);
	
//	public List<ZoneDTO> getZonesByStadiumId(String stad_id);
	
//	public List<SeatDTO> getSeatsByZoneId(String zone_id);
	
//	public SeatDTO getSeatById(String seat_id);

	public List<ZoneDTO> getZonesByStadiumId(String stad_id);

	public List<SeatDTO> getSeatsByZone(String zone_ty, String zone_id);
	
	public List<SeatDTO> getSeatsId(String seat_id);
	
}