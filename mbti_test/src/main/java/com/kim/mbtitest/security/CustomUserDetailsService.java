package com.kim.mbtitest.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kim.mbtitest.constant.Constant;
import com.kim.mbtitest.dao.MbtiDao;
import com.kim.mbtitest.dto.MbtiUserDto;

public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("�α��εǴ� username�� : " + username);
		MbtiDao mbtiDao = Constant.mbtiDao;
		System.out.println("�̶� �α��εǴ� mbtiDao : " + mbtiDao);
		MbtiUserDto mbtiUserDto = mbtiDao.login(username);
		System.out.println("�α��εǴ� mbtiUserDto : " + mbtiUserDto);
		
		if(mbtiUserDto == null) {
			System.out.println("Security���� MbtiUserDto�� null�̶� �α��ο� �����߽��ϴ�.");
			throw new UsernameNotFoundException("�ش� username�� ���� ������ ã�� �� �����ϴ�.");
		}
		
		String pw = mbtiUserDto.getmPw();
		String auth = mbtiUserDto.getAuth();
		System.out.println("��й�ȣ�� : " + pw + ", ������ : " + auth);
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(auth));
		
		UserDetails user = new User(username, pw, roles);
		
		return user;
	}

}
