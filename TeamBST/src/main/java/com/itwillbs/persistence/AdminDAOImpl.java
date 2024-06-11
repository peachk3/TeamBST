package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.Notice_boardDTO;

@Repository
public class AdminDAOImpl implements AdminDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	@Inject
	public SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.adminMapper.";

	@Override
	public void insertNotice(Notice_boardDTO dto) {
		
		logger.debug("DAO : insertNotice(dto) 호출");
		
		sqlSession.insert(NAMESPACE + "noticeJoin", dto);
		
		logger.debug("DAO : 공지사항 작성 완료");
	}
	

}
