package com.biz.sec.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.domain.UserVO;
import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/join",method=RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("userVO", new UserVO());
		return "join";
	}
	@RequestMapping(value = "/join",method=RequestMethod.POST)
	public String join(UserVO userVO) {
		/*
		 * Optional에 객체를 담는방법
		 * --객체가 null이면 아예 exception을 여기서 발생
		 * Optional<Myclass> myObj=Optional.of(userVO);
		 * 
		 * --null 이어도 무시하고 진행
		 * Optional<Myclass> myObj=Optional.ofNullable(userVO);
		 */
		Optional<UserVO> opUserVO=Optional.of(userVO);
		userService.insert(opUserVO);
		return "redirect:/";
	}
}
