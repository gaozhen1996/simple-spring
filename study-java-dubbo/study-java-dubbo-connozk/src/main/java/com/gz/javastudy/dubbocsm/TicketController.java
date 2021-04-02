package com.gz.javastudy.dubbocsm;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gz.javastudy.dubboapi.TicketService;


@SuppressWarnings("deprecation")
@Controller
public class TicketController {
	
	@Autowired
	private Environment environment;
	 
	public String getPort(){
	  return environment.getProperty("local.server.port");
	}
	
	@Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private TicketService ticketService;

	@ResponseBody
	@RequestMapping("/hello")
	public HashMap<String, String> hello(@RequestParam String name) {
		HashMap<String, String> returnData = new HashMap<String, String>();
		returnData.put("hello-"+getPort(), name);
		return returnData;
	}
	
	@ResponseBody
	@RequestMapping("/sell")
	public HashMap<String, String> sell() {
		HashMap<String, String> returnData = new HashMap<String, String>();
		int num = ticketService.get();
		returnData.put("num-"+getPort(), ""+num);
		if (num>0){
			ticketService.del();
		}
		return returnData;
	}
	
	@ResponseBody
	@RequestMapping("init")
	public HashMap<String, Object> init(@RequestParam int num) {
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("num", ticketService.init(num));
		return returnData;
	}

}
