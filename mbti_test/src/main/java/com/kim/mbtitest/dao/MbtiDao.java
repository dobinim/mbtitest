package com.kim.mbtitest.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kim.mbtitest.dto.MbtiUserDto;

public class MbtiDao implements iMbtiDao {

	@Autowired
	private SqlSession sqlSession;
	
	// ȸ�� ����
	@Override
	public String join(MbtiUserDto mdto) {
		String result = null;
		try {
			int res = sqlSession.insert("join", mdto);
			if (res > 0) {
				result = "����";
			} else {
				result = "����";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("ȸ�� ���� ����� : " + result + " �Դϴ�.");
		return result;
	}
	
	// ���̵� �ߺ� üũ
	@Override
	public int nameCheck(String mNickname) {
		int cnt = sqlSession.selectOne("nameCheck", mNickname);
		System.out.println("���̵� �ߺ� üũ ����� : " + cnt);
		return cnt;
	}
	
	// �α���
	@Override
	public MbtiUserDto login(String mName) {
		System.out.println("�������� �α��� ó���Ǵ� ���̵�� : " + mName);
		MbtiUserDto result = sqlSession.selectOne("login", mName);
		return result;
	}
	
}
