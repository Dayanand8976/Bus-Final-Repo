package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bus {

	@Id
	@GeneratedValue
	@Column(name="bus_no")
	private int busNo;
	
	private String name;
	private int totalSeats;
	
	@OneToMany(mappedBy ="bus")
	private List<Seat> seats;
	
	@OneToMany(mappedBy ="bus")
	private List<Timetable> timetable;
	
	@ManyToOne
	@JoinColumn(name="route_id") // should be route_id
	private Route route;

	public int getBusNo() { 
		return busNo;
	}

	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Timetable> getTimetable() {
		return timetable;
	}

	public void setTimetable(List<Timetable> timetable) {
		this.timetable = timetable;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	//bus entity
	
}
