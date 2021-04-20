package com.lti.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Timetable {
	
	@Id
	@GeneratedValue
	@Column(name="timetable_id")
	private int id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime endDateTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bus_no")
	private Bus bus;
	
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;
	
	@OneToMany(mappedBy = "timetable")
	private List<Ticket> tickets;
	
	/*
	 * @OneToMany(mappedBy = "timetable") 
	 * private List<SeatMapping> seatMappings;
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
	
	/*
	 * public List<SeatMapping> getSeatMappings() { return seatMappings; }
	 * 
	 * public void setSeatMappings(List<SeatMapping> seatMappings) {
	 * this.seatMappings = seatMappings; }
	 */

}
