package com.lti.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.BusStop;
import com.lti.entity.Seat;
import com.lti.entity.Timetable;

@Repository
public class BusTimetableRepositoryImpl extends GenericRepository implements BusTimetableRepository {

	public List<Timetable> fetchScheduleByDateAndTime(LocalDateTime ldt) {

		// imp imp imp compare date & time along with only hours and minutes part
		String jpql = " select tt from Timetable tt where trunc(tt.startDateTime, 'MI') = :dt";

		Query q = entityManager.createQuery(jpql);
		q.setParameter("dt", ldt);
		List<Timetable> list = q.getResultList();
		return list;

	}

	public List<BusStop> fetchBusStopsFromSourceAndDestination(String source, String destination,
			LocalDateTime localDateTime) {

		// search based on startDate of timetable and departureDate of busstop
		String jpql = " select bs from BusStop bs join bs.route r join r.timetable tt where r.source = :sc and "
				+ "r.destination= :dn and trunc(tt.startDateTime) = trunc(:ldt) and trunc(bs.departureTime)= trunc(:ldt)";

		Query q = entityManager.createQuery(jpql);
		q.setParameter("sc", source);
		q.setParameter("dn", destination);
		q.setParameter("ldt", localDateTime);
		List<BusStop> list = q.getResultList();
		return list;
	}

	public List<Seat> fetchSeatByBusNo(int busNo) {

		String jpql = "SELECT s FROM Seat s WHERE bus.busNo = :bn";

		Query q = entityManager.createQuery(jpql);
		q.setParameter("bn", busNo);

		List<Seat> list = q.getResultList();
		return list;
	}

	public Timetable fetchTimetableId(String source, String destination, LocalDateTime localDateTime, int busNo) {
		String jpql = " select tt from Bus b join b.route r join r.timetable tt where r.source = :sc and "
				+ "r.destination= :dn and trunc(tt.startDateTime) = trunc(:ldt)"
				+ " and b.busNo = :bno";
		
		Query q = entityManager.createQuery(jpql);
		q.setParameter("sc", source);
		q.setParameter("dn", destination);
		q.setParameter("ldt", localDateTime);
		q.setParameter("bno", busNo);
		
		Timetable timetable =  (Timetable) q.getSingleResult();

		return timetable;
	}
}
