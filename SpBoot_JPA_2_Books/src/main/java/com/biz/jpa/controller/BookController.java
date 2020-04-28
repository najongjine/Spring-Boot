package com.biz.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.jpa.domain.BookService;
import com.biz.jpa.domain.BookVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookController {
	private final BookService bookService;
	
	@RequestMapping(value = "/pagelist",method=RequestMethod.GET)
	public String getPageList(@PageableDefault Pageable page,Model model) {
		Page<BookVO> bookList=bookService.getPageList(page);
		log.debug("!!! paged bookList: "+bookList.toString());
		model.addAttribute("bookList", bookList);
		//return bookList;
		return "bookList";
	}
	
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String findAll(Model model) {
		List<BookVO> bookList=bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "bookList";
	}
	
	@RequestMapping(value = "/findById",method=RequestMethod.GET)
	public String findById(long id,Model model) {
		Optional<BookVO> bookVO=bookService.findById(id);
		model.addAttribute("bookVO", bookVO.get());
		model.addAttribute("MODE", "view");
		return "bookForm";
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		BookVO bookVO=new BookVO();
		model.addAttribute("bookVO", bookVO);
		model.addAttribute("MODE", "insert");
		model.addAttribute("ACTION", "/book/insert");
		return "bookForm";
	}
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(BookVO bookVO) {
		bookService.save(bookVO);
		return "redirect:/book";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(long id,Model model) {
		Optional<BookVO> bookVO=bookService.findById(id);
		model.addAttribute("bookVO", bookVO.get());
		model.addAttribute("MODE", "update");
		model.addAttribute("ACTION", "/book/update");
		return "bookForm";
	}
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(BookVO bookVO) {
		bookService.save(bookVO);
		return "redirect:/book";
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(long id) {
		bookService.delete(id);
		return "redirect:/book";
	}
}
