package com.kim.mbtitest.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kim.mbtitest.dto.MbtiUserDto;

public class MbtiDao implements iMbtiDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 회원 가입
	@Override
	public String join(MbtiUserDto mdto) {
		String result = null;
		try {
			int res = sqlSession.insert("join", mdto);
			if (res > 0) {
				result = "성공";
			} else {
				result = "실패";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("회원 가입 결과는 : " + result + " 입니다.");
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int nameCheck(String mNickname) {
		int cnt = sqlSession.selectOne("nameCheck", mNickname);
		System.out.println("아이디 중복 체크 결과는 : " + cnt);
		return cnt;
	}
	
	// 로그인
	@Override
	public MbtiUserDto login(String mName) {
		System.out.println("맵핑으로 로그인 처리되는 아이디는 : " + mName);
		MbtiUserDto result = sqlSession.selectOne("login", mName);
		return result;
	}
	
}
