package com.cpg.fms.dao;

import java.util.HashMap;
import java.util.List;

import com.cpg.fms.model.*;

public interface UserOperationsInterface {

	//CUSTOMER OPERATIONS:
	public void bookFlight(Database data, User user,ScheduledFlight flight, List<Passenger> passengerList);
	public void modifyBooking(UserOperationsDAO userOp, Database data, User user);
	public void cancelBooking(User user, Long bookingId);
	public HashMap<String, ScheduledFlight> searchFlights(Database data, String array);
	
	//ADMIN OPERATIONS:
	public boolean addFlight(Flight flight, Database data);
	public Flight viewFlight(String flightId, Database data);
	public HashMap<String, Flight> viewAllFlights(Database data);
	public List<ScheduledFlight> viewScheduledFlight(Database data);
	public void addScheduledFlight(ScheduledFlight flight,Database data);

}