package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Route {
	
	@Id
	@GeneratedValue
	@Column(name="route_id")
	private int id;
	
	private String source;
	private String destination;
	private int fare;
	
	@OneToMany(cascade = CascadeType.MERGE,mappedBy ="route")
	private List<Bus> buses;
	
	@OneToMany(mappedBy ="route")
	private List<BusStop> busStops;
	
	@OneToMany(mappedBy ="route")
	private List<Timetable> timetable;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public List<Bus> getBuses() {
		return buses;
	}
	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	public List<BusStop> getBusStops() {
		return busStops;
	}
	public void setBusStops(List<BusStop> busStops) {
		this.busStops = busStops;
	}
	public List<Timetable> getTimetable() {
		return timetable;
	}
	public void setTimetable(List<Timetable> timetable) {
		this.timetable = timetable;
	}
	
	
	
}
