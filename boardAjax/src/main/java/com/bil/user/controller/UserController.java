package com.bil.user.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bil.user.service.UserService;
import com.bil.user.vo.UserVO;

@Controller
@RequestMapping("user/*")
public class UserController {

	@Autowired
	UserService userService;
	
	//회원가입 루트
	@RequestMapping(value="userInsert.do")
	public String userInsert() {
		return "/user/userInsert";
	}
	
	//아이디 중복체크 Ajax처리
	@RequestMapping(value = "idCkedAjax.do", method = RequestMethod.GET)
	@ResponseBody
	public String idCheck(@RequestParam("userid") String userid) {
		String data = userService.idCkedAjax(userid);
		System.out.println("결과값 :" +data);
		return data;
	}
	
	//회원가입
	@RequestMapping("userJoin.do")
	public String userJoin(UserVO vo){
		userService.userJoin(vo);
	    System.out.println(vo);
		return "redirect:/login/login.do";
	}
	
}
