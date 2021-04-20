package com.lti.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.PassengerSeat;
import com.lti.dto.TicketStatusConfirmation;
import com.lti.dto.UserTicket;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Ticket;
import com.lti.entity.Timetable;
import com.lti.entity.User;
import com.lti.repository.BookingRepository;
import com.lti.repository.BusTimetableRepository;
import com.lti.repository.GenericRepository;
import com.lti.repository.SeatRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BusTimetableRepository busTimetableRepository;

	@Autowired
	private GenericRepository genericRepository;

	@Autowired
	private SeatRepository seatRepository;

	public TicketStatusConfirmation bookTicket(TicketStatusConfirmation tsc) {
		// userId,timetableId,source,destination,startDateTime,busNo,passengerSeat

		User user = (User) genericRepository.fetch(User.class, tsc.getUserId());

		if (user == null) {
			throw new RuntimeException("user does not exist");
		}

		Timetable timetable = busTimetableRepository.fetchTimetableId(tsc.getSource(), tsc.getDestination(),
				tsc.getStartDateTime(), tsc.getBusNo());

		tsc.setTimetableId(timetable.getId());

		Ticket ticket = new Ticket();
		ticket.setUser(user);
		ticket.setTimetable(timetable);
		ticket.setStatus("Pending");

		List<Passenger> passengers = new ArrayList<Passenger>();

		// mapping for passenger and seat
		for (PassengerSeat ps : tsc.getPassengerSeat()) {
			Passenger passenger = new Passenger();
			passenger.setName(ps.getName());
			passenger.setTicket(ticket);

			Seat seat = seatRepository.fetchSeat(ps.getSeatNo(), tsc.getBusNo());
			seat.setStatus("pending");
			passenger.setSeat(seat);

			passengers.add(passenger);
		}

		ticket.setPassengers(passengers);

		Ticket t = (Ticket) bookingRepository.saveTicket(ticket);

		return printBookedTicket(tsc, t.getId());
	}

	@Override
	public TicketStatusConfirmation printBookedTicket(TicketStatusConfirmation tsc, int ticketId) {
		tsc.setTicketId(ticketId);
		return tsc;
	}

	@Override
	public List<UserTicket> showAllTicketsOfUser(int userId) {
		List<UserTicket> userTickets = new ArrayList<UserTicket>();

		List<Object[]> list = bookingRepository.showAllTicketsOfUser(userId);

		for (Object[] obj : list) {
			UserTicket userTicket = new UserTicket();
			userTicket.setTicketId((int) obj[0]);
			userTicket.setUserId((int) obj[1]);
			userTicket.setBusName((String) obj[2]);
			userTicket.setSource((String) obj[3]);
			userTicket.setDestination((String) obj[4]);
			userTicket.setDepartureDateTime((LocalDateTime) obj[5]);
			userTicket.setRouteFare((int) obj[6]);

			List<Passenger> passengers = bookingRepository.getPassengersInSingleTicket(userTicket.getTicketId());
			int passengerCount = 0;
			String seatNo = "";
			
			for (Passenger p : passengers) {
				passengerCount++;
				seatNo = seatNo + " " + p.getSeat().getSeatNo();
			}
			
			userTicket.setNoOfPassengers(passengerCount);
			userTicket.setSeatNo(seatNo);

			userTickets.add(userTicket);
		}
		return userTickets;
	}

}
