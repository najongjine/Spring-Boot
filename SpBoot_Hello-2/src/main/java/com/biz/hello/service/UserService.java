package com.biz.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.hello.domain.UserVO;

@Service
public class UserService {
	public List<UserVO> getUserList(){
		List<UserVO> userList=new ArrayList<>();
		userList.add( UserVO.builder().username("홍길동").password("1111").email("sdfsf@.com").phone("010...")
				.address("wghee").build() );
		userList.add( UserVO.builder().username("홍2").password("1111").email("sdfsf22@.com").phone("01022...")
				.address("wghee22").build() );
		userList.add( UserVO.builder().username("홍3").password("1111").email("sdfsf33@.com").phone("01033...")
				.address("wghee33").build() );
		return userList;
	}
}