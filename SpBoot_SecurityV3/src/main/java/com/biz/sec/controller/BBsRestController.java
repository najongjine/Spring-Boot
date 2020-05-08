package com.biz.sec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * RestController
 * return type에 관계없이 모든 값을 json 객체형으로 web에 되돌려준다.
 */
@RestController
@RequestMapping(value = "/bbs/api")
@RequiredArgsConstructor
@Slf4j
public class BBsRestController {
	private final BBsService bService;
	
	@RequestMapping(value = "/json",method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<BBsVO> getBBsList(){
		List<BBsVO> bbsList=bService.getBBsList();
		return bbsList;
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public BBsVO insert(BBsVO bbsVO) {
		BBsVO retBBsVO=bService.insert(bbsVO);
		log.debug("# bbs data: "+bbsVO.toString());
		return retBBsVO;
	}
	
	@RequestMapping(value = "/detail",method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public BBsVO detail(long bbsid) {
		Optional<BBsVO> bbsVO=bService.findById(bbsid);
		log.debug("# bbsVO: "+bbsVO.get());
		return bbsVO.get();
	}
	
	@RequestMapping(value = "/delete/{bbsid}")
	@CrossOrigin(origins = "http://localhost:3000")
	public String delete(@PathVariable("bbsid") long bbsid) {
		log.debug("# bbsid in dlete: "+bbsid);
		bService.delete(bbsid);
		return "OK";
	}
}
