package com.kim.mbtitest.dao;

import com.kim.mbtitest.dto.MbtiUserDto;

public interface iMbtiDao {
	
	public String join(MbtiUserDto mdto); // 회원가입
	public int nameCheck(String mNickname); // 닉네임 중복 체크
	public MbtiUserDto login(String mName); // 로그인
}
