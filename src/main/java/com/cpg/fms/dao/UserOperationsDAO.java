package com.cpg.fms.dao;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


import com.cpg.fms.model.*;
import com.cpg.fms.userException.UserException;

public class UserOperationsDAO implements UserOperationsInterface
{

	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
	
		
//CUSTOMER OPERATIONS:	
	public void bookFlight(Database data, User user,ScheduledFlight flight, List<Passenger> passengerList){

		Booking booking = new Booking();
			
			try {
				booking.setBookingDate(dateFormat2.parse(LocalDate.now().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			booking.setBookingID((long) (Math.random()*10000) );			
			
			
			booking.setFlight(flight);
			booking.setUserId(user);
			booking.setNoOfPassenger(passengerList.size());
			booking.setPassengerList(passengerList);
			HashMap<Long, Booking> bookingList = user.getBookings();
				
			bookingList.put(booking.getBookingID(),booking);
			user.setBookings(bookingList);
				
			data.listScheduledFlights.get(data.listScheduledFlights.indexOf(flight)).
					setAvailableSeats(data.listScheduledFlights.get(data.listScheduledFlights.indexOf(flight)).
					getAvailableSeats() - passengerList.size());
			

	}

	public void modifyBooking(UserOperationsDAO userOp, Database data, User user) {
		
		System.out.println("OPTION NOT AVAILABLE FOR NOW :-)");
	
	}

	public void cancelBooking(User user, Long bookingId) {
		HashMap<Long, Booking> bookingList = user.getBookings();
		
		try 
		{
			bookingList.remove(bookingId);
			user.setBookings(bookingList);
			
		} 
		catch (NumberFormatException e) 
		{
			System.out.println(e.getMessage());
		} 
	

	}

	
//ADMIN OPERATIONS:
	public boolean addFlight(Flight flight, Database data) {

		data.flightMap.put(flight.getFlightNumber(), flight);
		return true;
	}

	public Flight viewFlight(String flightId, Database data) {

		return data.flightMap.get(flightId);
		
	}

	public HashMap<String, Flight> viewAllFlights(Database data) {
		return data.flightMap;
	}

	public List<ScheduledFlight> viewScheduledFlight(Database data) {

		return data.listScheduledFlights;

	}

	public void addScheduledFlight(ScheduledFlight flight, Database data) {

		data.listScheduledFlights.add(flight);
		
	}

	@Override
	public HashMap<String, ScheduledFlight> searchFlights(Database data, String array)
	{
		HashMap<String, ScheduledFlight> availableFlights = new HashMap<>();
		try 
		{
			
			String date = array.split("|")[0];
			String source = array.split("|")[1];
			String destination = array.split("|")[2];
			
			for(ScheduledFlight flight: data.listScheduledFlights) {
				
				String datetime[] =  flight.getSchedule().getDepartureTime().toString().split(" ");
				
				
				
				if(date.equals(datetime[2]+"-"+datetime[1]+"-"+datetime[5])
				   && source.equals(flight.getSchedule().getSourceAirport().getAirportCode())
				   && destination.equals(flight.getSchedule().getDestinationAirport().getAirportCode())){
					availableFlights.put(flight.getFlight().getFlightNumber(), flight);
				}
				
			}
			if(availableFlights.size() == 0) {
				throw new UserException("No flights found from given source to given destination on given date.");
			}
			
		}
		catch(UserException e)
		{
			System.out.println(e.getMessage());
		}
		return availableFlights;
			
	}
	
}
