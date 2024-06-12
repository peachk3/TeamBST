package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Question_boardDTO {
	
	private Integer quest_id;
	private String quest_writer_id;
	private String quest_sub;
	private Timestamp quest_cre_date;
	private int quest_cate;
	private String quest_public;
	private String photo_url;
	private int quest_view;
	private String quest_del_at;
	private Timestamp quest_del_date;
	
	
}
