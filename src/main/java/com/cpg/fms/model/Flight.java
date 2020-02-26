package com.cpg.fms.model;


public class Flight {

	private String flightNumber;
	private String carrierName;
	private String flightModel;
	private int seatCapacity;
	
	
	
	public Flight() {
		
	}
	//FOR ADDING DEFAULT VALUES:
	
	public Flight(String flightNumber, String carrierName, String flightModel, int seatCapacity) {
	
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	
	
	
	//Getters and Setters:
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String string) {
		this.flightNumber = string;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getFlightModel() {
		return flightModel;
	}
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	@Override
	public String toString() {
		return "\n\t\t---FLIGHT--- \n"
				+ "["
				+ "\t Flight Number = " + flightNumber 
				+ "\t| CarrierName = " + carrierName 
				+ "\t| Model = " + flightModel
				+ "\t| Seat Capacity = " + seatCapacity 
				+ " ]";
	}
	
	
	
}
