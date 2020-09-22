package com.bil.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bil.common.service.CommonService;
import com.bil.user.service.UserService;
import com.bil.user.vo.UserVO;
import com.bil.util.CommUtils;


@Controller
@RequestMapping("login/*")
public class LoginController {


	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name="commonService")
	private CommonService commonService;

	@Resource(name="userService")
	private UserService userService;

	//로그인 화면 이동
	@RequestMapping("login.do")
	public String loginview() {
		return "/login/login";
	}

	//로그인 기능
	@RequestMapping("login_check.do")
	public ModelAndView login_check(UserVO vo,HttpSession session,ModelAndView mav) {
		String result = userService.login_check(vo);
		int idCheck = userService.idCheck(vo);
		if(result.equals(vo.getPwd()) && idCheck == 1) {//equals () 는 메소드 입니다. 객체끼리 내용을 비교할 수 있도록 합니다. == 은 비교를 위한 연산자 입니다.
          //출처: https://kmj1107.tistory.com/entry/JAVA-문자열string-비교-equals와-의-차이점-equals의-반대 [토순이네집]
			session.setAttribute("userid",vo.getUserid());
			session.getAttribute("user_name");
			session.setAttribute("user_name", vo.getUser_name());
			System.out.println("idCheck : " +idCheck);
			System.out.println("로그인 성공");
			mav.setViewName("redirect:/account/accountList.do");
		}else if(!result.equals(vo.getPwd()) || idCheck == 0){
			System.out.println("idCheck : " +idCheck);
			System.out.println("로그인 실패");
			mav.setViewName("/login/login");
			mav.addObject("error","error");
		}
		return mav;
	}
	
    //로그아웃 기능(세션값 삭제)
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		session.invalidate();
		mav.setViewName("redirect:/login/login.do");
		return mav;
	}
	

	/*@RequestMapping(value="idCkedAjax.do")
	public ModelAndView idCkedAjax(HttpServletRequest request) throws Exception {
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);



		return new ModelAndView(jsonView, inOutMap);
	}*/



}// end of class
