package com.lti.repository;

import java.util.List;

import com.lti.entity.Passenger;
import com.lti.entity.Ticket;

public interface BookingRepository {
	
	public Object saveTicket(Ticket t) ;

	public List<Object[]> showAllTicketsOfUser(int userId);

	public List<Passenger> getPassengersInSingleTicket(int ticketId);
}
