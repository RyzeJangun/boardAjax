package com.bil.account.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bil.account.vo.AccountVO;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("accountDAO")
public class AccountDAO extends EgovAbstractMapper{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<AccountVO> accountList(int start,int end) {
		Map<String,Object> map=new HashMap<>();
		map.put("start", start);//맵에 자료저장
		map.put("end", end);
		return sqlSessionTemplate.selectList("Common.accountList",map);
	}
	
	public int countArticle() {
		return sqlSessionTemplate.selectOne("Common.countArticle");
		
	}

	public void accountJoin(AccountVO vo) {		
		sqlSessionTemplate.insert("Common.accountJoin",vo);
	}

	public AccountVO accountDetail(int account_seq) {
		return sqlSessionTemplate.selectOne("Common.accountDetail", account_seq);
	}

	public void accountUpdate(AccountVO vo) {
		sqlSessionTemplate.update("Common.accountUpdate",vo);
	}



}
