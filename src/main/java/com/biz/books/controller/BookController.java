package com.biz.books.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.books.model.BookVO;
import com.biz.books.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bks;
	
	@RequestMapping(value="book_home",method=RequestMethod.GET)
	public String Book_list(Model model) {
		
		List<BookVO> bookList = bks.selectAll();
		model.addAttribute("BOOKS",bookList);
		model.addAttribute("BODY","LIST");
		model.addAttribute("bookVO",new BookVO());
		return "home";
	}
	
	@RequestMapping(value="memo",method=RequestMethod.GET)
	public String Book(Model model) {
		
		model.addAttribute("BODY","WRITE");
		return "home";
	}
	
	@RequestMapping(value="memo",method=RequestMethod.POST)
	public String memo(@ModelAttribute BookVO vo, Model model) {
		
		
		
		String retMsg = "" ;
		String resPath = "" ;
		int ret = bks.writeDB(vo);
		if(ret > 0) { 
			resPath = "redirect:book_home";
		} else {
			retMsg = "데이터 추가 오류";
			resPath = "home";
		}
		
		model.addAttribute("BODY", "WRITE");
		model.addAttribute("MESSAGE", retMsg);
		return resPath;
	}
	
	@RequestMapping("bookview")
	public String book_view(@Param("b_id") long b_id,
				String MSG, 
				Model model) {
		BookVO vo = bks.getMemo(b_id);
		
		model.addAttribute("MSG",MSG);
		model.addAttribute("BOOKS", vo);
		model.addAttribute("BODY","VIEW");
		
		return "home" ;
	
	}
	
	@RequestMapping(value="book_update", method=RequestMethod.GET)
	public String book_update(@Param("b_id") long b_id, Model model) {
		
		BookVO vo = bks.getMemo(b_id);
		model.addAttribute("BOOKS",vo);
		model.addAttribute("BODY", "WRITE");
		return "home";
	
	}
	
	@RequestMapping("book_delete")
	public String book_delete(@Param("b_id") long b_id, Model model) {
		
		int ret = bks.delete(b_id);
		String resPath = "";
		String resMsg = "";
		
		if(ret > 0) {
			resPath = "redirect:book_home";
		} else {
			resPath = "redirect:bookview";
			resMsg = "DEL-ERR";
		}
		model.addAttribute("b_id",b_id);
		model.addAttribute("MSG", resMsg);
		return resPath ;
		
	}
}
