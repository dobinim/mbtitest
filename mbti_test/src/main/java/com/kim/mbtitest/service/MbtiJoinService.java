package com.kim.mbtitest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import com.kim.mbtitest.constant.Constant;
import com.kim.mbtitest.dao.MbtiDao;
import com.kim.mbtitest.dto.MbtiUserDto;

public class MbtiJoinService implements MbtiService {

	@Override
	public void execute(HttpServletRequest request, Model model) {

		BCryptPasswordEncoder passwordEncoder = Constant.passwordEncoder;
		
		// form 의 입력값 추출
		String mName = request.getParameter("mName");
		String mPw = request.getParameter("mPw");
		String mbti = request.getParameter("mbti");
		String mNickname = request.getParameter("mNickname");
		
		
		// 비밀번호 encoding
		String mPw_org = mPw;
		mPw = passwordEncoder.encode(mPw_org);
				
		MbtiUserDto mbtiUserDto = new MbtiUserDto(mName, mPw, mNickname, mbti, null);
		
		// dao
		MbtiDao mbtiDao = Constant.mbtiDao;
		System.out.println("mbtiDao : " + mbtiDao);
			// mbtiDao를 확인하기 위한 출력 -> null로 나옴. 
		String result = mbtiDao.join(mbtiUserDto);
		
		request.setAttribute("result", result);

	}

}
