package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.Game_scheduleDTO;
import com.itwillbs.domain.Post_boardDTO;
import com.itwillbs.domain.Question_boardDTO;
import com.itwillbs.domain.UserDTO;
import com.itwillbs.persistence.MypageDAO;

@Service
public class MypageServiceImpl implements MypageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MypageServiceImpl.class);

    @Inject
    private MypageDAO mypageDAO;
    

    @Override
    public UserDTO getMember(String user_id) {
        return mypageDAO.getMember(user_id);
    }

    @Override
    public void updateMember(UserDTO udto) {
        mypageDAO.updateMember(udto);
    }

    @Override
    public boolean deleteMember(UserDTO ddto) {
        int result =  mypageDAO.deleteMember(ddto);
        
        return result > 0;
       
    }

    @Override
    public List<Post_boardDTO> postBoardList(String user_id) throws Exception {
        return mypageDAO.postBoardList(user_id);
    }
    
    @Override
	public void pbUpdateReadCnt(int post_id) throws Exception {
		mypageDAO.pbUpdateReadCnt(post_id);
	}
    
    @Override
    public Post_boardDTO pGetBoard(int post_id) throws Exception {
    	logger.debug(" pGetBoard(int post_id) 실행 ");
    	return mypageDAO.pGetBoard(post_id);
    }

	@Override
    public List<Question_boardDTO> questionBoardList(String user_id) throws Exception {
        return mypageDAO.questionBoardList(user_id);
    }
    
    @Override
    public void qbUpdateReadCnt(int quest_id) throws Exception {
    	mypageDAO.qbUpdateReadCnt(quest_id);
    }
    
    @Override
    public Question_boardDTO qGetBoard(int quest_id) throws Exception {
    	logger.debug( "qGetBoard(int quest_id) 실행" );
    	return mypageDAO.qGetBoard(quest_id);
    }

	@Override
	public List<Game_scheduleDTO> previousMatchList(String user_id) throws Exception {
		return mypageDAO.previousMatchList(user_id);
	}

	@Override
	public List<Game_scheduleDTO> openMatchList(String user_id) throws Exception {
		return mypageDAO.openMatchList(user_id);
	}
    
    
}