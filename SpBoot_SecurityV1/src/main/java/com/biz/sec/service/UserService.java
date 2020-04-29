package com.biz.sec.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	private final UserDao uDao;
	private final PasswordEncoder encPassword;
	
	public UserVO insert(Optional<UserVO> opUserVO) {
		// TODO Auto-generated method stub
		UserVO userVO=opUserVO.get();
		String strPassword=userVO.getPassword();
		String strEncPassword=encPassword.encode(strPassword);
		userVO.setPassword(strEncPassword);
		userVO.setEnabled(true);
		userVO.setAccountNonExpired(true);
		userVO.setAccountNonLocked(true);
		userVO.setCredentialsNonExpired(true);
		UserVO retUserVO=uDao.save(userVO);
		return retUserVO;
	}

}
