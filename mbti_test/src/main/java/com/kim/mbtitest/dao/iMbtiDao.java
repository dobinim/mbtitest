package com.kim.mbtitest.dao;

import com.kim.mbtitest.dto.MbtiUserDto;

public interface iMbtiDao {
	
	public String join(MbtiUserDto mdto); // ȸ������
	public int nameCheck(String mNickname); // �г��� �ߺ� üũ
	public MbtiUserDto login(String mName); // �α���
}
