package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.jpa.domain.UserVO;
import com.biz.jpa.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService uService;
	
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String user(Model model) {
		model.addAttribute("user", new UserVO());
		return "userForm";
	}
	
	/*
	 * spring boot 에서는 @responsebody 를 설정하면 별다른 dependency 설정없이 자동으로 VO등 클래스를 return 할수 있다.
	 */
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String user(UserVO userVO) {
		/*
		 * JPArepository를 사용할경우 insert 와 update는 save() method를 사용하여 수행
		 */
		UserVO vo=uService.save(userVO);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String userList(Model model) {
		List<UserVO> userList=uService.selectAll();
		model.addAttribute("userList", userList);
		return "layout";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(long id,Model model) {
		Optional<UserVO> userVO=uService.findById(id);
		model.addAttribute("user", userVO.get());
		return "userForm";
	}
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(UserVO userVO) {
		UserVO vo=uService.save(userVO);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(long id) {
		uService.delete(id);
		return "redirect:/user/list"; 
	}
}
