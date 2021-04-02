package com.gz.javastudy.sellticket.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.javastudy.sellticket.App;
import com.gz.javastudy.sellticket.service.TicketService;


@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	public int getPort(){
	  return App.PORT;
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public HashMap<String, String> hello(@RequestParam String name) {
		HashMap<String, String> returnData = new HashMap<String, String>();
		returnData.put("hello-"+getPort(), name);
		return returnData;
	}
	
	@ResponseBody
	@RequestMapping("/get")
	public HashMap<String, Object> get() {
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("num-"+getPort(), ticketService.getTicket().getId());
		return returnData;
	}
	
	@ResponseBody
	@RequestMapping("/sell")
	public HashMap<String, Object> sell() {
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("num-"+getPort(), ticketService.sell());
		return returnData;
	}

	@ResponseBody
	@RequestMapping("init")
	public HashMap<String, Object> init(@RequestParam int num) {
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("num-"+getPort(), ticketService.updateTicket(num));
		return returnData;
	}

}
