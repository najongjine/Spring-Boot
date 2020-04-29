package com.biz.sec.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.domain.BBsVO;
import com.biz.sec.service.BBsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/bbs")
@Slf4j
public class BBsController {
	private final BBsService bService;
	/*
	 * JPA를 이용한 pagenation
	 * 1. list method에 Pageable 매개변수를 선언
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(@PageableDefault Pageable pageable,Model model) {
		Page<BBsVO> bbsList=bService.getBBsList(pageable);
		model.addAttribute("bbsList", bbsList);
		return "bbsList";
	}
}
