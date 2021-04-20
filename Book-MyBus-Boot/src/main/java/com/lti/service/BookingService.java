package com.lti.service;

import java.util.List;

import com.lti.dto.TicketStatusConfirmation;
import com.lti.dto.UserTicket;

public interface BookingService {

	public TicketStatusConfirmation bookTicket(TicketStatusConfirmation tsc);
	
	public TicketStatusConfirmation printBookedTicket(TicketStatusConfirmation tsc,int ticketId);

	public List<UserTicket> showAllTicketsOfUser(int userId);
}
