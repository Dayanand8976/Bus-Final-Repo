package com.lti.repository;

import com.lti.entity.Seat;

public interface SeatRepository {
	public Seat fetchSeat(int seatNo, int bNo);
}
