package com.biz.sec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.repository.BBsDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BBsService {
	private final BBsDao bDao;
	
	/*
	 * 게시판 데이터를 Pageable과 연동하여 pagination을 하기위한 데이터 return
	 */
	public Page<BBsVO> getPageBBsList(Pageable pageable) {
		int page=pageable.getPageNumber();
		if(pageable.getPageNumber()>0) {
			page=pageable.getPageNumber()-1;
		}
		//요청한 페이지에서 10개의 데이터를 추출하기 위한 설정값을 만들어라
		pageable=PageRequest.of(page,10);
		
		//페이지를 가져오기위한 설정 객체(pageable)을 dao에게 전달하여
		// 한 페이지의 데이터만 가져오도록 지시
		Page<BBsVO> pageBBsList=bDao.findAll(pageable);
		return pageBBsList;
	}

	/*
	 * 게시판 전체 데이터를 추출하여 return
	 */
	public List<BBsVO> getBBsList() {
		// TODO Auto-generated method stub
		return bDao.findAll();
	}
	
	public BBsVO insert(BBsVO bbsVO) {
		return bDao.save(bbsVO);
	}
}
