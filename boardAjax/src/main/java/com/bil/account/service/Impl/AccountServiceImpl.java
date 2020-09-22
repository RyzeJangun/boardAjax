package com.bil.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bil.account.service.AccountService;
import com.bil.account.vo.AccountVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Resource(name="accountDAO")
	private AccountDAO accountDAO;
	
	public List<AccountVO> accountList(int start,int end){
		return accountDAO.accountList(start,end);
	}
	
	public void accountJoin(AccountVO vo) {
		accountDAO.accountJoin(vo);
	}
	
	public AccountVO accountDetail(int account_seq) {
		return accountDAO.accountDetail(account_seq);
	}
	
	public void accountUpdate(AccountVO vo) {
		accountDAO.accountUpdate(vo);
	}
	
	public int countArticle() {
		return accountDAO.countArticle();
	}






}
