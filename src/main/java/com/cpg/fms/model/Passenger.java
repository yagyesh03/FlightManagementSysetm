package com.cpg.fms.model;


public class Passenger {

	private Long pnrNumber;
	private Long passengerUIN;
	private String passengerName;
	private int passengerAge; 
	private Double luggage;
	
	public Passenger(){}
	
	//TO SET DEFAULT VALUES:
	public Passenger(Long pnrNumber, Long passengerUIN, String passengerName, int passengerAge) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerUIN = passengerUIN;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
	}
	
	

	@Override
	public String toString() {
		return " [\tPNR Number = " + pnrNumber + "\t| PassengerUIN = " + passengerUIN + "\t| Name = "
				+ passengerName + "\t| Age = " + passengerAge + "\t| Luggage = " + luggage + " ]";
	}

	//Getters and Setters:
	public Long getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(Long pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public Long getPassengerUIN() {
		return passengerUIN;
	}
	public void setPassengerUIN(Long passengerUIN) {
		this.passengerUIN = passengerUIN;
	}
	public Double getLuggage() {
		return luggage;
	}
	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}
	
}