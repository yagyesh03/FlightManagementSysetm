package com.cpg.fms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cpg.fms.model.*;


public class Database {

	//1. userMap: UserId(Long) -> UserObject
		public HashMap<Long, User> userMap = new HashMap<Long, User>();
			
			//2. listScheduledFlights:
		public List<ScheduledFlight> listScheduledFlights = new ArrayList<ScheduledFlight>();
			
			//3. timeFlightMap: Time(String) -> ScheduledFlightObject
		public HashMap<String, ScheduledFlight> timeFlightMap = new HashMap<String, ScheduledFlight>();
			
			//4. dateTimeFlightMap: Date(String) -> timeFlightMap
		public HashMap<String, HashMap<String, ScheduledFlight>> dateTimeFlightMap = new HashMap<String, HashMap<String, ScheduledFlight>>();
			
			//5. flightMap: FlightNumber(String) -> FlightObject
		public HashMap<String, Flight> flightMap = new HashMap<String, Flight>();
			
			//6. airportMap: airportId -> AirportObject
		public HashMap<String, Airport> airportMap = new HashMap<String, Airport>();
			

}