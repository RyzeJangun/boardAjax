package com.bil.account.service;

import java.util.List;

import com.bil.account.vo.AccountVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface AccountService {

	public List<AccountVO> accountList(int start,int end);
	public void accountJoin(AccountVO vo);
	public AccountVO accountDetail(int account_seq);
	public void accountUpdate(AccountVO vo);
	public int countArticle();
	



}
