package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService{
	private final UserDao uDao;
	private final PasswordEncoder PasswordEncoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserVO> userVO=uDao.findByUsername(username);
		log.debug("!!! uservO "+userVO);
		
		//Optional<VO> 형식의 데이터에서 VO를 추출하기 위해서는 .get() methdo를 실행해준다
		Collection<GrantedAuthority> authorities=getUserAuthority(userVO.get().getUserRoles());
		return null;
	}

	/*
	 * DB에 문자열로 저장되어있는 권한 정보를 GrantedAuthority 형식으로 변환하는 method
	 */
	private Collection<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles){
		List<GrantedAuthority> authortities=new ArrayList<>();
		for(UserRole uRole:userRoles) {
			authortities.add(new SimpleGrantedAuthority(uRole.getRolename()));
		}
		return authortities;
	}
}
