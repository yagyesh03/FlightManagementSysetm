package com.cpg.fms.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.cpg.fms.model.*;
import com.cpg.fms.dao.*;

public interface OperationsInterface 
{
	
	public Object isAdmin(User user);
	public boolean newOrOld(User user, Database data);
	public void signUp(Database data);
	public User signIn(Database data);
	public boolean validMail(String email);
	public boolean validPhoneNum(String phone);
	public void updateAccount(UserOperationsDAO userOp, Database data, User user);
	public void deleteAccount(UserOperationsDAO userOp, User user, Database data);
	
	//CUSTOMER:
	public void customerOperations( User user, Database data);
	public String getFlightNumber(HashMap<String,ScheduledFlight> availableFlights);
	public List<Passenger> getPassengers();
	public void displayBookings(User user);
	
	//ADMIN:
	public void adminOperations(User user, Database data) throws IOException;
	public Flight inputFlightInfo();
	public void modifyFlightDetails(Database data) throws IOException;
	public boolean valideFlightId(String flightId, Database data);
	public ScheduledFlight scheduleFlight(Database data);
	
}