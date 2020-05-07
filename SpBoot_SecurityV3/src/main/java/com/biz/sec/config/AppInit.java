package com.biz.sec.config;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.BBsDao;
import com.biz.sec.repository.UserDao;
import com.biz.sec.repository.UserRoleDao;

import lombok.RequiredArgsConstructor;

/*
 * CommandLineRunner 인터페이스를 상속받은 클래스
 * Springboot에서만 사용할수있는 특별한 class
 * 
 * 프로젝트가 시작된느 시점에 어떤 코드가 자동으로 실행하고 싶을때
 */
@RequiredArgsConstructor
@Component
public class AppInit implements CommandLineRunner{
	private final UserDao uDao;
	private final UserRoleDao urDao;
	private final BBsDao bDao;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Optional<UserVO> vo=uDao.findByUsername("1");
		if(vo.isPresent()) return;
		UserVO userVO=UserVO.builder().username("1").password("1").build();
		uDao.save(userVO);
		UserRole uRole=UserRole.builder().username("1").rolename("ADMIN").build();
		urDao.save(uRole);
		uRole=UserRole.builder().username("1").rolename("USER").build();
		urDao.save(uRole);
	}

}
