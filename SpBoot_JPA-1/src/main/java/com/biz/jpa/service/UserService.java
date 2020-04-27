package com.biz.jpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userDao;
	
	@Transactional
	public UserVO save(UserVO userVO) {
		/*
		 * repository.save(VO) JPA 환경에서는 JPAreposiory 를 extends 함으로써
		 * 자동으로 기본 CRUD method를 사용할수 있다.
		 */
		UserVO vo= userDao.save(userVO);
		return vo;
	}

	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	/*
	 * findByid를 실행하고나면 UserVO를 받을수 있다.
	 * findById를 실행했을때 결과값이 없으면 null을 return 한다
	 * 
	 * 이런 상황이 되면 NullPointterException이 발생할수 있다.
	 * 
	 * Optional(wrapper class)로 VO를 감싸게 되면
	 * NullPointerException을 회피할수 있다.
	 * 
	 * 필요한 VO객체를 추출하려면 optVO.get() method를 사용하여야 한다.
	 */
	public Optional<UserVO> findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

}
