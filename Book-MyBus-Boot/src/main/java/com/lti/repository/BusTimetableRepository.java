package com.lti.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.lti.entity.BusStop;
import com.lti.entity.Seat;
import com.lti.entity.Timetable;

public interface BusTimetableRepository {

	public List<Timetable> fetchScheduleByDateAndTime(LocalDateTime ldt);
	
	public List<BusStop> fetchBusStopsFromSourceAndDestination(String source, String destination,
			LocalDateTime localDateTime);
	
	public List<Seat> fetchSeatByBusNo(int busNo);
	
	public Timetable fetchTimetableId(String source, String destination, LocalDateTime localDateTime, int busNo);
}
