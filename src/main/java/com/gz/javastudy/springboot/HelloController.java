package com.gz.javastudy.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		return "Hello,"+name;
	}
	
	
}
