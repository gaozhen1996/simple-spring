package com.gz.javastudy.sellticket;

import java.util.List;

public interface TicketDao {
	
	public List<Ticket> selectTicket();	
	
	public int updateTicket(Ticket ticket);	
}
