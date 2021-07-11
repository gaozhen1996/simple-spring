package com.gz.javastudy.sellticket.service;

import com.gz.javastudy.sellticket.config.RedisConfig;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.javastudy.sellticket.domain.Ticket;
import com.gz.javastudy.sellticket.repository.TicketDao;

@Service
public class TicketService {
	
	@Autowired
	private TicketDao dao;

	@Autowired
	private RedisConfig redisConfig;

	public Ticket getTicket() {
		return dao.selectTicket().get(0);
	}
	
	public int updateTicket(int num) {
		Ticket ticket = new Ticket();
		ticket.setId(num);
		return dao.updateTicket(ticket);
	}
	
	public int sell() {
		RLock lock = redisConfig.getRedissonClient().getLock("ticket");
		int currnum = 0;
		try {
			lock.lock();
			currnum = this.getTicket().getId();
			if(currnum>0) {
				int temp = currnum-1;
				this.updateTicket(temp);
			}
		}finally {
			lock.unlock();
		}
		return currnum;
	}
	
}
