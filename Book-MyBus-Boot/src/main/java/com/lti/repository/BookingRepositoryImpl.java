package com.lti.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Passenger;
import com.lti.entity.Ticket;
import com.lti.entity.Timetable;

@Repository
public class BookingRepositoryImpl extends GenericRepository implements BookingRepository {

	public Object saveTicket(Ticket t) {

		Object obj = entityManager.merge(t);
		return obj;
	}

//	@Override
//	public List<Object[]> showAllTicketsOfUser(int userId) {
//
//		String sql = "Select t.id,u.first_name,b.bus_no,b.name,"
//				+ "r.source,tt.start_date_time"
//				+ " FROM ticket t Inner JOIN timetable tt ON t.timetable_id = tt.timetable_id "
//				+ "Inner JOIN bus b on tt.bus_no = b.bus_no Inner JOIN route r ON tt.route_id = r.route_id "
//				+ "Inner JOIN users u on t.user_id=u.id where u.id="
//				+ userId+ "";
//
//		Query q = entityManager.createNativeQuery(sql);
//		
//		
//		
//		return q.getResultList();
//	}

	@Override
	public List<Object[]> showAllTicketsOfUser(int userId) {

		String jpql = "Select t.id,u.id,b.name,r.source,r.destination,tt.startDateTime,r.fare"
				+ " FROM Ticket t JOIN t.timetable tt " + " JOIN tt.bus b JOIN b.route r "
				+ "JOIN t.user u  where u.id=" + userId;

		Query q = entityManager.createQuery(jpql);

		return q.getResultList();
	}

	@Override
	public List<Passenger> getPassengersInSingleTicket(int ticketId) {
		
		String jpql = "Select p from Passenger p join p.ticket t where t.id = "+ticketId;
		Query q = entityManager.createQuery(jpql);
		return q.getResultList();
	}
}
