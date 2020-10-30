package com.gz.javastudy.springboot;

import java.util.HashMap;

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
	public HashMap<String, String> hello(@RequestParam String name) {
		HashMap<String, String> returnData = new HashMap<String, String>();
		returnData.put("hello", name);
		return returnData;
	}
	
	
}
