package com.gz.javastudy.sellticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.javastudy.sellticket.domain.Ticket;
import com.gz.javastudy.sellticket.repository.TicketDao;

@Service
public class TicketService {
	
	@Autowired
	private TicketDao dao;
	
	public Ticket getTicket() {
		return dao.selectTicket().get(0);
	}
	
	public int updateTicket(int num) {
		Ticket ticket = new Ticket();
		ticket.setId(num);
		return dao.updateTicket(ticket);
	}
	
	public int sell() {
		int currnum = 0;
		synchronized (this) {
			currnum = this.getTicket().getId();
			if(currnum>0) {
				int temp = currnum-1;
				this.updateTicket(temp);
			}
		}
		return currnum;
	}
	
}
