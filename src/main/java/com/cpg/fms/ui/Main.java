package com.cpg.fms.ui;


import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cpg.fms.dao.Database;
import com.cpg.fms.model.*;
import com.cpg.fms.service.*;

public class Main 
{
	
//GLOBAL VARIABLES:
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
	User user = new User();
	Database data = new Database();
	
	

//MAIN:
	public static void main(String[] args) 
	{
		System.out.println("\n\t*** !! 'Welcome Note' !! ***\n\n");
		Operations operation  = new Operations();
		Main services = new Main();
		services.setDefaultData();
		services.control(operation);
		
	}
	
//CONTROLING FUNCTION:
	public void control(Operations operation) 
	{
		
		String choice = "Y";
		while(choice.equals("Y")) 
		{
		
			System.out.println("\t***************************");
			System.out.println("\t*                         *");
			System.out.println("\t*                         *");
			System.out.println("\t*       127.0.0.1         *");
			System.out.println("\t*                         *");
			System.out.println("\t*                         *");
			System.out.println("\t***************************");
		
		//SignUP or SingIN:
			if(operation.newOrOld(user, data))
				operation.signUp(data);
			
			user = operation.signIn(data);
			
			
			try
			{
				// Administration OR Customer:
				Object t = operation.isAdmin(user);
				
				if(!t.equals(""))
				{
					if((boolean) t) 
					{
						operation.adminOperations(user, data);
					}
					else {
						operation.customerOperations(user, data);
					}
						
				}
			}
			catch (NullPointerException | NumberFormatException | IOException e) {
				System.out.println("INVALID CHOICE.");
				e.getStackTrace();
			}
			
			
			System.out.println("\n\tWANT TO SIGN UP OR SIGN IN AGAIN? [ Y / N ]");
			try 
			{
				choice = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
//SETTING UP DEFAULT DATA:
	public void setDefaultData()
		{
			
			//Creating demo/default ADMIN:
			User testAdmin = new User("ADMIN", "YAGYESH", "123", 123);
			data.userMap.put(testAdmin.getUserId(), testAdmin);
			
			//Creating demo/default CUSTOMER:
			User tempCustomer = new User("CUSTOMER", "JOHN", "124", 124);
			data.userMap.put(tempCustomer.getUserId(), tempCustomer);
			
			//Creating Airports:
			Airport airport1 = new Airport("Mumbai Chattrapathi Shivaji International Airport",
											"MUMBAI", "BOM");
			
			Airport airport2 = new Airport("Bangalore Bengaluru International Airport ",
											"Bangalore", "BLR");
			
			Airport airport3 = new Airport("New Delhi Indira Gandhi International Airport",
											"New Delhi ", "DEL");

			Airport airport4 = new Airport("Hyderabad Rajiv Gandhi International Airport",
											"Hyderabad", "HYD");

			data.airportMap.put(airport1.getAirportCode(), airport1);
			data.airportMap.put(airport2.getAirportCode(), airport2);
			data.airportMap.put(airport3.getAirportCode(), airport3);
			data.airportMap.put(airport4.getAirportCode(), airport4);
			
			//Creating DateTime Objects:
			
			Date dateTime1 = null;
			Date dateTime2 = null;
			Date dateTime3 = null;
			Date dateTime4 = null;
			try
			{
				dateTime1 = dateFormat.parse("12-01-2020 05:12:00");
				dateTime2 = dateFormat.parse("02-02-2020 23:45:00");
				dateTime3 = dateFormat.parse("12-01-2020 07:12:00");
				dateTime4 = dateFormat.parse("03-02-2020 01:45:00");
			}
			catch (ParseException e) 
			{
				e.getMessage();
			}
			
			
			//Creating Schedules:
			Schedule schedule1 = new Schedule(airport1, airport2, dateTime3, dateTime1, dateFormat);
			Schedule schedule2 = new Schedule(airport2, airport3, dateTime3, dateTime1, dateFormat);
			Schedule schedule3 = new Schedule(airport4, airport1, dateTime4, dateTime2, dateFormat);
			Schedule schedule4 = new Schedule(airport3, airport4, dateTime4, dateTime2, dateFormat);
			
			//Creating Flights:
			Flight flight1 = new Flight("AB3456", "INDIGO", "A330", 293);
			Flight flight2 = new Flight("BO6789", "JET AIRWAYS", "777", 440);
			data.flightMap.put(flight1.getFlightNumber(), flight1);
			data.flightMap.put(flight2.getFlightNumber(), flight2);
			
			//Creating Scheduled Flights:
			ScheduledFlight scheduledFlight1 = new ScheduledFlight(flight1, 293, schedule1);
			ScheduledFlight scheduledFlight2 = new ScheduledFlight(flight2, 440, schedule2);
			ScheduledFlight scheduledFlight3 = new ScheduledFlight(flight1, 293, schedule3);
			ScheduledFlight scheduledFlight4 = new ScheduledFlight(flight2, 440, schedule4);
			
			//Creating List of Scheduled Flights:
			data.listScheduledFlights.add(scheduledFlight1);
			data.listScheduledFlights.add(scheduledFlight2);
			data.listScheduledFlights.add(scheduledFlight3);
			data.listScheduledFlights.add(scheduledFlight4);

		}

}