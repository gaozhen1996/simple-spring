package com.gz.javastudy.sellticket;


public interface TicketDao {
	
	public Ticket selectTicket();	
	
	public int updateTicket(Ticket ticket);	
}
