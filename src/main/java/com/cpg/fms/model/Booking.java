package com.cpg.fms.model;


import java.util.Date;
import java.util.List;

public class Booking {

	Long bookingID;
	User userId;
	Date bookingDate;
	List<Passenger> passengerList;
	Double ticketCost;
	ScheduledFlight flight;
	Integer noOfPassenger;
	
	
	
	@Override
	public String toString() {
		return "O->\n\t\t----BOOKING---- \n"
				+ "\n\t[ \n\tBookingID = " + bookingID 
				+ "\n\tUser Id = " + userId 
				+ "\n\tBooking Date = " + bookingDate
				+ "\n\tPassenger List = " + displayPassengerList() 
				+ "\n\tTicketCost=" + ticketCost 
				+ "\n\tFlight=" + flight
				+ "\n\tNo Of Passenger=" + noOfPassenger 
				+ "\n\t]";
	}
	
	
	public String displayPassengerList() 
	{
		
		String pass = null;
		for(Passenger p: passengerList) 
		{
			pass += p.toString();
		}
		
		return pass;
	}
	
	//Getters and Setters:
	public Long getBookingID() {
		return bookingID;
	}
	public void setBookingID(Long bookingID) {
		this.bookingID = bookingID;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	public Double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}
	public ScheduledFlight getFlight() {
		return flight;
	}
	public void setFlight(ScheduledFlight flight) {
		this.flight = flight;
	}
	public Integer getNoOfPassenger() {
		return noOfPassenger;
	}
	public void setNoOfPassenger(Integer noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}
	
	
	
}

