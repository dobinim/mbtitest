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
	// ��ȣȭ Bean ����
	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		Constant.passwordEncoder = passwordEncoder;
	}
	
	private MbtiDao mbtiDao; 		
	// DAO ����
	@Autowired
	public void setMbtiDao(MbtiDao mbtiDao) {
		this.mbtiDao = mbtiDao;
		Constant.mbtiDao = mbtiDao;
	}

	
																/* ��� */
	// ȸ������ ����
	@RequestMapping(value = "/join", produces = "application/text;charset=UTF8")
	@ResponseBody
	public String join(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("ȸ�� ������ �����ϰڽ��ϴ�.");
		
		service = new MbtiJoinService();
		service.execute(request, model);
		
		String result = (String)request.getAttribute("result");
		System.out.println("����� : " + result);
		
		if(result.equals("����")) {
			return "join success";
		} else {
			return "join failed";
		}
	}
	
	// �г��� �ߺ�üũ
	@RequestMapping("/nameCheck")
	@ResponseBody
	public int nameCheck(@RequestParam("mNickname") String mNickname) {
		int cnt = mbtiDao.nameCheck(mNickname);
		return cnt;
	}
	
	
	// �α��� ���� ��
	@RequestMapping("/myPage")
	public String myPage(HttpServletRequest request, Model model, Authentication authentication) {
		System.out.println("�α��� ����");
		// �α��� ����� �޼��� 
		getUserName(authentication, request);
		String username = (String)request.getAttribute("username");
		String auth = (String)request.getAttribute("auth");
		return "my_page";
	}
	
	// �α��� ����� �ϴ� �޼���
	private void getUserName(Authentication authentication, HttpServletRequest request) {
		System.out.println("�α��� ó�� ����");
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		// getPrincipal() : �α��ο� ������ ������ ������ ���� ����.
		String username = userDetails.getUsername();
		System.out.println("username : " + username); // �α����� ���̵�
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String auth = authorities.toString();
		// role�� �� ���ڿ��� ��ȯ
		System.out.println("������ " + auth); // [ROLE_USER]�� ����
		
		request.setAttribute("username", username);
		request.setAttribute("auth", auth);
	}
	
	// �α��� ���� ��
	@RequestMapping("/processLogin")
	public ModelAndView processLogin(
			@RequestParam(value="log", required=false) String log,
			@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			HttpSession session, Model model
			) {
		System.out.println("�α��� ���� �߻�");
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

	
	
											/* ������ �̵� */
	
	// ���� ȭ������ �̵�
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		System.out.println("���� �������� ���ư��ϴ�.");
		return "home";
	}
	
	// ���� ������ �̵�
	@RequestMapping("/join_page")
	public String joinPage(HttpServletRequest request, Model model) {
		System.out.println("������������ �̵�");
		return "join_page";
	}
	
	// �α��� ������ �̵�
	@RequestMapping("/login_page")
	public String loginPage(HttpServletRequest request, Model model) {
		System.out.println("�α��� �������� �̵�");
		return "login_page";
	}
	
}
