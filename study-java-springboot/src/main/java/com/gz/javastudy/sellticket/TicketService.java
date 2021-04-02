package com.gz.javastudy.sellticket;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	
	@Autowired
	private Config config;
	
	public Ticket getTicket() {
		SqlSession session = config.getSqlSession();
		TicketDao dao = session.getMapper(TicketDao.class);
		return dao.selectTicket().get(0);
	}
	
	public int updateTicket(int num) {
		Ticket ticket = new Ticket();
		ticket.setId(num);
		SqlSession session = config.getSqlSession();
		TicketDao dao = session.getMapper(TicketDao.class);
		session.commit();
		return dao.updateTicket(ticket);
	}
	
}
