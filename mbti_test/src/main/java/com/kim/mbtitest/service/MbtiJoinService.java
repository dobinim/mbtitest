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
		
		// form �� �Է°� ����
		String mName = request.getParameter("mName");
		String mPw = request.getParameter("mPw");
		String mbti = request.getParameter("mbti");
		String mNickname = request.getParameter("mNickname");
		
		
		// ��й�ȣ encoding
		String mPw_org = mPw;
		mPw = passwordEncoder.encode(mPw_org);
				
		MbtiUserDto mbtiUserDto = new MbtiUserDto(mName, mPw, mNickname, mbti, null);
		
		// dao
		MbtiDao mbtiDao = Constant.mbtiDao;
		System.out.println("mbtiDao : " + mbtiDao);
			// mbtiDao�� Ȯ���ϱ� ���� ��� -> null�� ����. 
		String result = mbtiDao.join(mbtiUserDto);
		
		request.setAttribute("result", result);

	}

}
