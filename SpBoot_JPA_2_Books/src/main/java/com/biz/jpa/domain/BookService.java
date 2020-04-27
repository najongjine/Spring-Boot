package com.biz.jpa.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.jpa.repository.BookDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
	private final BookDao bookDao;
	
	public List<BookVO> findAll(){
		return bookDao.findAll();
	}
	public BookVO save(BookVO bookVO) {
		return bookDao.save(bookVO);
	}
	public Optional<BookVO> findById(long id) {
		return bookDao.findById(id);
	}
	public void delete(long id) {
		bookDao.deleteById(id);
	}
	
	/*
	 * spring-data package Pageable,Page,PageRequest 를 사용하여 pagination을 구현
	 */
	public Page<BookVO> getPageList(Pageable page) {
		int pageNum=0;
		if(page.getPageNumber()==0) {
			pageNum=0;
		}else {
			pageNum=page.getPageNumber()-1;
		}
		page=PageRequest.of(pageNum, 10);
		return bookDao.findAll(page);
	}
}
