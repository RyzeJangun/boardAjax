package com.bil.user.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bil.user.vo.UserVO;

@Service
public class UserService {
	  
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//회원가입
	public void userJoin(UserVO vo) {
		sqlSessionTemplate.insert("userJoin", vo);
	}
	
	//아이디 중복체크
	public String idCkedAjax(String userid) {
		return sqlSessionTemplate.selectOne("idCkedAjax",userid);
		
	}

	//로그인 기능
	public String login_check(UserVO vo) {
		return sqlSessionTemplate.selectOne("login_check",vo);
	}

	public int idCheck(UserVO vo) {
		return sqlSessionTemplate.selectOne("idCheck", vo);
	}

	
	
}
