package com.gz.javastudy.sellticket.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gz.javastudy.sellticket.domain.Ticket;

@Mapper
public interface TicketDao {
	
	public List<Ticket> selectTicket();	
	
	public int updateTicket(Ticket ticket);	
}
