package com.cpg.fms.model;

public class ScheduledFlight {

	private Flight flight;
	private int availableSeats;
	private Schedule schedule;
	
	public ScheduledFlight() {
		
	}
	
	
	//Getters and Setters:
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	//FOR ADDING DEFAULT VALUES:
	public ScheduledFlight(Flight flight, int availableSeats, Schedule schedule) {
		
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
	}
	@Override
	public String toString() {
		return "\n---SCHEDULED FLIGHT---"
				+"\n["
				+ "\nFlight = " + flight.toString() 
				+ "\t| Available Seats = " + availableSeats 
				+ "\nSchedule = " + schedule.toString()
				+ "\n]";
	}	
}
