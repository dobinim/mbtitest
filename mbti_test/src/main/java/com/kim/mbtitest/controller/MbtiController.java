package com.kim.mbtitest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kim.mbtitest.constant.Constant;
import com.kim.mbtitest.dao.MbtiDao;
import com.kim.mbtitest.service.MbtiJoinService;
import com.kim.mbtitest.service.MbtiService;

@Controller
public class MbtiController {

	private MbtiService service;

	private BCryptPasswordEncoder passwordEncoder; 	
	// 암호화 Bean 주입
	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		Constant.passwordEncoder = passwordEncoder;
	}
	
	private MbtiDao mbtiDao; 		
	// DAO 주입
	@Autowired
	public void setMbtiDao(MbtiDao mbtiDao) {
		this.mbtiDao = mbtiDao;
		Constant.mbtiDao = mbtiDao;
	}

	
																/* 기능 */
	// 회원가입 실행
	@RequestMapping(value = "/join", produces = "application/text;charset=UTF8")
	@ResponseBody
	public String join(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("회원 가입을 진행하겠습니다.");
		
		service = new MbtiJoinService();
		service.execute(request, model);
		
		String result = (String)request.getAttribute("result");
		System.out.println("결과는 : " + result);
		
		if(result.equals("성공")) {
			return "join success";
		} else {
			return "join failed";
		}
	}
	
	// 닉네임 중복체크
	@RequestMapping("/nameCheck")
	@ResponseBody
	public int nameCheck(@RequestParam("mNickname") String mNickname) {
		int cnt = mbtiDao.nameCheck(mNickname);
		return cnt;
	}
	
	
	// 로그인 성공 시
	@RequestMapping("/myPage")
	public String myPage(HttpServletRequest request, Model model, Authentication authentication) {
		System.out.println("로그인 실행");
		// 로그인 기능할 메서드 
		getUserName(authentication, request);
		String username = (String)request.getAttribute("username");
		String auth = (String)request.getAttribute("auth");
		return "my_page";
	}
	
	// 로그인 기능을 하는 메서드
	private void getUserName(Authentication authentication, HttpServletRequest request) {
		System.out.println("로그인 처리 시작");
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		// getPrincipal() : 로그인에 성공한 유저의 정보를 갖고 있음.
		String username = userDetails.getUsername();
		System.out.println("username : " + username); // 로그인한 아이디
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String auth = authorities.toString();
		// role을 얻어서 문자열로 변환
		System.out.println("권한은 " + auth); // [ROLE_USER]의 형태
		
		request.setAttribute("username", username);
		request.setAttribute("auth", auth);
	}
	
	// 로그인 실패 시
	@RequestMapping("/processLogin")
	public ModelAndView processLogin(
			@RequestParam(value="log", required=false) String log,
			@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			HttpSession session, Model model
			) {
		System.out.println("로그인 에러 발생");
		ModelAndView mModel = new ModelAndView();
		
		if(log != null && log != "") {
			mModel.addObject("log", "before login!");
		}
		if(error != null && error != "") {
			mModel.addObject("error", "Invalid username or password");
		}
		if(logout != null && logout != "") {
			mModel.addObject("logout", "You've been logged out successfully");
		}
		
		mModel.setViewName("login_page");
		return mModel;
	}

	
	
											/* 페이지 이동 */
	
	// 메인 화면으로 이동
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		System.out.println("메인 페이지로 돌아갑니다.");
		return "home";
	}
	
	// 가입 페이지 이동
	@RequestMapping("/join_page")
	public String joinPage(HttpServletRequest request, Model model) {
		System.out.println("가입페이지로 이동");
		return "join_page";
	}
	
	// 로그인 페이지 이동
	@RequestMapping("/login_page")
	public String loginPage(HttpServletRequest request, Model model) {
		System.out.println("로그인 페이지로 이동");
		return "login_page";
	}
	
}
