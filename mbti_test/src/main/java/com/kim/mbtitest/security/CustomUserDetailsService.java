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
		System.out.println("로그인되는 username은 : " + username);
		MbtiDao mbtiDao = Constant.mbtiDao;
		System.out.println("이때 로그인되는 mbtiDao : " + mbtiDao);
		MbtiUserDto mbtiUserDto = mbtiDao.login(username);
		System.out.println("로그인되는 mbtiUserDto : " + mbtiUserDto);
		
		if(mbtiUserDto == null) {
			System.out.println("Security에서 MbtiUserDto가 null이라 로그인에 실패했습니다.");
			throw new UsernameNotFoundException("해당 username을 가진 유저를 찾을 수 없습니다.");
		}
		
		String pw = mbtiUserDto.getmPw();
		String auth = mbtiUserDto.getAuth();
		System.out.println("비밀번호는 : " + pw + ", 권한은 : " + auth);
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(auth));
		
		UserDetails user = new User(username, pw, roles);
		
		return user;
	}

}
