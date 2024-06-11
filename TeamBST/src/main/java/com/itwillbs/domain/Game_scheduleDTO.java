package com.itwillbs.domain;

import java.sql.Time;

import lombok.Data;

@Data
public class Game_scheduleDTO {
	
	private long game_id;
	private String game_date;
	private Time game_time;
	private String stad_id;
	private String home_team_id;
	private String away_team_id;
	

}
