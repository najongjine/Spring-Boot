package com.biz.sec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.sec.domain.UserVO;

public interface UserDao extends JpaRepository<UserVO, Long>{
	/*
	 * jpa, hibernate를 사용하면 Entity클래스 (VO 클래스에 @Entity Annotation이 부착된 클래스)
	 * 를 적용하여 findBy** method를 자동으로 생성해주는 기능이 있다.
	 */
	public Optional<UserVO> findByUsername(String username);
}
