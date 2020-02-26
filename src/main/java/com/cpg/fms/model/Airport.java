package com.cpg.fms.model;



public class Airport {

	private String airportName;
	private String airportLocation;
	private String airportCode;
	
	
	public Airport() {
		
	}
	//FOR ADDING DEFAULT VALUES:
	public Airport(String airportName, String airportLocation, String airportCode) {
		this.airportName = airportName;
		this.airportLocation = airportLocation;
		this.airportCode = airportCode;
	}
	//Getters and Setters:
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportLocation() {
		return airportLocation;
	}
	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	@Override
	public String toString() {
		return  "\n\t["
				+ "\t| Name = " + airportName 
				+ "\t| Location = " + airportLocation 
				+ "\t| Airport Code = "+airportCode 
				+ " ]\n\n";
	}
	
	
	
}
