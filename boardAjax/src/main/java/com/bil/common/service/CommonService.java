package com.bil.common.service;

import java.util.List;
import java.util.Map;

import com.bil.user.vo.UserVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface CommonService {

	List<EgovMap> selectCombo(Map<String, Object> inOutMap) throws Exception;

	public List<EgovMap> selectCode(String profit_cost);

	



}
