package com.cpg.fms.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Schedule {

	private Airport sourceAirport;
	private Airport destinationAirport;
	private Date arrivalTime;
	private Date departureTime;
	private SimpleDateFormat dateFormate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	
	public Schedule() {
		
	}
	
	//FOR ADDING DEFAULT VALUES:
	
	public Schedule(Airport sourceAirport, Airport destinationAirport, Date arrivalTime, Date departureTime,
			SimpleDateFormat dateFormate) {
		
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.dateFormate = dateFormate;
	}



	//Getters and Setters:
	public Airport getSourceAirport() {
		return sourceAirport;
	}
	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	public Airport getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public SimpleDateFormat getDateFormate() {
		return dateFormate;
	}
	public void setDateFormate(SimpleDateFormat dateFormate) {
		this.dateFormate = dateFormate;
	}



	@Override
	public String toString() {
		return "\n---FLIGHT SCHEDULE---"
				+ "\n["
				+ "\nSource Airport = " + sourceAirport.toString() 
				+ "\nDestination Airport = " + destinationAirport.toString()
				+ "\nDeparture Time = " + departureTime
				+ "\t| Arrival Time = " + arrivalTime 
				+ "\n]";
	}
}