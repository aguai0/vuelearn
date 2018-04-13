package com.learn.seckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="test")
public class TestController {

	@RequestMapping(value="/tst")
	public String test(Model model){
		
		System.out.println("-------");
		return "list";
	}
}
