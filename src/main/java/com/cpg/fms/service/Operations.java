package com.cpg.fms.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


import com.cpg.fms.model.*;
import com.cpg.fms.userException.UserException;
import com.cpg.fms.dao.*;

public class Operations implements OperationsInterface{

	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	UserOperationsDAO userOp = new UserOperationsDAO();

	//COMMON OPERATIONS:
	@Override
	public void updateAccount(UserOperationsDAO userOp, Database data, User user) {


		String cont = "Y";
		while(cont.equals("Y"))
		{
			
			int choice = 0;
			String temp = null;
			
			System.out.println("\n\tYOU CAN UPDATE FOLLOWING DETAILS:"
							 + " \n\t 1. MAIL ADDRESS: "+user.geteMail()
							 + ".\n\t 2. PHONE NUMBER: "+user.getPhoneNumber()
							 + ".\n\t 3. USER NAME: "+user.getUserName()
							 + ".\n\t 4. PASSWORD.");
			try {
				choice = Integer.parseInt(br.readLine());
				switch(choice) 
				{
				
				case 1:
					System.out.println("ENTER NEW E MAIL ADDRESS:");
					temp = br.readLine();
					if(validMail(temp)) {
						user.seteMail(temp);
					}
					else {
						System.out.println("INVALID MAIL");
					}
					break;
					
				case 2:
					System.out.println("ENTER NEW PHONE NUMBER:");
					temp = br.readLine();
					if(validPhoneNum(temp)) {
						user.setPhoneNumber(Long.parseLong(temp));
					}
					else {
						System.out.println("INVALID PHONE NUMBER");
					}
					break;
					
				case 3:
					System.out.println("ENTER NEW NAME: ");
					temp = br.readLine();
					if(temp.equals("")) {
						System.out.println("NAME CAN NOT BE EMPTY");
					}
					else {
						user.setUserName(temp);
					}
					break;
					
				case 4:
					System.out.println("Enter old Password to verify:");
					temp = br.readLine();
					if(user.getPassword().equals(temp)) 
					{
						System.out.println("Enter new password.");
						temp=br.readLine();
						System.out.println("Re- Enter new password:");
						if(temp.equals(br.readLine())) {
							user.setPassword(temp);
						}
						else 
						{
							System.out.println("New Password Did not match.");
						}
					}
					else 
					{
						System.out.println("Invalid Old password.");
					}
					break;
					
					default: 
						System.out.println("INVALID OPTION.");
						break;
				}
				
				
				System.out.println("Want to change more details: [ Y / N ]");
				cont = br.readLine();
			}
			catch (NumberFormatException | IOException e ) 
			{
				System.out.println(e.getMessage());
			}
			
			
		}
		
	}

	@Override
	public boolean validMail(String email) {
		if(Pattern.matches("[a-zA-Z0-9]+[@]+[a-zA-Z]+.com$", email))
			return true;
		else{
			return false;
		}
	}

	@Override
	public boolean validPhoneNum(String phone) {
		if(Pattern.matches("[1-9][0-9]{9}", phone))
			return true;
		else
			return false;
	}

	@Override
	public void deleteAccount(UserOperationsDAO userOp, User user, Database data) {
		System.out.println("\n\tEnter Password to Delete Account:");
		try {
			String password = br.readLine();
			if(user.getPassword().equals(password)) 
			{
				data.userMap.remove(user.getUserId());
				System.out.println("\n\tUser account Successfully deleted.");
			}
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Object isAdmin(User user) {

		try 
		{
			if(user.getUserType().toUpperCase().equals("ADMIN")) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean newOrOld(User user, Database data) {
		String signType = null;
		
			System.out.println("\n\tIF NEW USER? PRESS Y, else any other Key\n");
			
			try {
				signType =  br.readLine();
				if(signType.equals("Y"))
				return true;
			} catch (IOException e) {
				e.getMessage();
			}
			
		return false;
	}

	@Override
	public void signUp(Database data) {

		System.out.println("\n\n\t**** SIGN UP ****");
		User user = new User();
		
		try 
		{
			String temp;
			while(true) 
			{
				System.out.println("\n\tENTER TYPE: CUSTOMER OR ADMIN:");
				temp = br.readLine();
				if(temp.toLowerCase().equals("customer") || temp.toLowerCase().equals("admin"))
					break;
				else
					try 
					{
						throw new UserException("EXCEPTION: INVALID INPUT, Please Enter either CUSTOMER or ADMIN");
					}
					catch(UserException e) {
						System.out.println(e.getMessage());
					}
			}
			user.setUserType(temp);
			
			while(true)
			{
				temp = "";
				System.out.println("\n\tENTER NAME:");
				temp = br.readLine();
				
				if(!Pattern.matches("[0-9a-zA-Z]+", temp)) {
					try {
						throw new UserException("\n\tEXCEPTION: INVALID USER NAME."
								+ "\n\tPlease Re - Enter the Name.");
					}
					catch(UserException e) {
						System.out.println(e.getMessage());
					}
				}
				else
					break;
				
			}	
			user.setUserName(temp);
			
			while(true)
			{
				System.out.println("\n\tENTER AADHAR NUMBER:");
				temp = br.readLine();
				
				try
				{
					if(data.userMap.get(Long.parseLong(temp)) != null) 
						throw new UserException("EXCEPTION: USER WITH SAME ID ALREADY EXIST");
					if(!Pattern.matches("[0-9]{3}", temp)) 
					{
						throw new UserException("ID MUST BE OF 3 DIGITS.");
					}
					break;
				} 
				catch (UserException e) 
				{
					System.out.println(e.getMessage());
				}
				
			}	
			user.setUserId(Long.parseLong(temp));
			
			while(true)
			{
				System.out.println("\n\tENTER PHONE NUMBER:");
				temp = br.readLine();
				if(validPhoneNum(temp)) 
					break;
				System.out.println("Phone should have an exact 10 digit number and the number should not start with zero.\r\n");	
				
			}	
			user.setPhoneNumber(Long.parseLong(temp));
				
			while(true)
			{
				System.out.println("\n\tENTER E MAIL ADDRESS: ");
				temp = br.readLine();
				if(validMail(temp)) 
					break;
				else 
				{
					try {
						throw new UserException("\n\tINVALID E-MAIL ADDRESS.");
					}
					catch(UserException e) {
						System.out.println(e.getMessage());
					}
				}
				
			}	
			user.seteMail(temp);
				
			while(true)
			{
				System.out.println("\n\tENTER PASSWORD:");
				temp = br.readLine();
				if(temp.length() <= 5) 
				{
					try 
					{
						throw new UserException("EXCEPTION: PLEASE ENTER STRONG PASSWORD.");
					}
					catch(UserException e) 
					{
						System.out.println(e.getMessage());
					}
				}
				else 
				{
					break;
				}
			}
			user.setPassword(temp);
				
			data.userMap.put(user.getUserId(),user);
				
			System.out.println("\nUSER \""+user.getUserName()+"\" SUCCESSFULLY CREATED.\n");
			
		}	
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public User signIn(Database data) {

		System.out.println("\n\n\t**** SIGN IN ****");
		Long userId = (long) 0;
		String password;
		User user = new User();
		System.out.println("\n\tEnter UserID:");
		try
		{
			userId = Long.parseLong(br.readLine());
			
			System.out.println("\tEnter Password:");
			password = br.readLine();
		
			String t = data.userMap.get(userId).getPassword();
			if(!t.equals(password)) 
			{
				throw new NullPointerException();
			}
			else 
			{
				user = data.userMap.get(userId);
				System.out.println("\n\tSIGN IN SUCCESSFUL.\n\n");
				System.out.println("\n\tWELCOME "+user.getUserName());
				return user;
			}
			
		} 
		catch(NullPointerException | IOException e) {
			System.out.println("INVALID USER ID or PASSWORD");
			System.out.println("\n\nWANT TO CHANGE THE PASSWORD ? [ Y / N ]");
			String check = "N";
			try {
				check = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(check.equals("Y")) {
				changePass(user,data);
			}
		}
		
		return null;
	}

	public void changePass(User user, Database data) 
	{
		String pass = null;
		while(true)
		{
			System.out.println("\n\tEnter New Password:");
			try {
				pass = br.readLine();
				if(pass.length() < 5)
					throw new UserException("\n\tEXCEPTION: PLEASE ENTER STRONG PASSWORD.");	
				else 
					break;	
			} 
			catch (IOException | UserException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		data.userMap.get(user.getUserId()).setPassword(pass);
	}
	
	//ADMIN:
	public void adminOperations(User user, Database data) throws IOException {
		String cont = "Y";
		while(cont.equals("Y")) 
		{
			
			System.out.println("\n\n\tWhat task you want to perform:"
							+ "\n\tPress KEY -> TASK"
							+ "\n\t 1 -> Add New Flight"
							+ "\n\t 2 -> Modify Flight Details."
							+ "\n\t 3 -> View Flight By ID"
							+ "\n\t 4 -> View Flight List"
							+ "\n\t 5 -> Schedule a Flight"
							+ "\n\t 6 -> View SCheduled Flights."
							+ "\n\t 7 -> Update Profile Details."
							+ "\n\t 8 -> Delete Profile."
							+ "\n\t 9 -> Sign Off."
							+ "\n\t 0 -> Any Customer Task.");
			int choice = 11;
		
			try {
				String tempChoice = br.readLine();
				if(!Pattern.matches("[0-9]", tempChoice)) 
					throw new UserException("INVALID CHOICE, PLEASE ENTER SINGLE DIGIT INTEGER.");
				else {
					choice = Integer.parseInt(tempChoice);
				}
			} catch (IOException | UserException e) {
				System.out.println(e.getMessage());
				choice = 10;
			}	
			
			
			switch(choice) {
			
				case 0:
					customerOperations(user, data);
					break;
					
				case 1:
					userOp.addFlight(inputFlightInfo(), data);
					System.out.println("FLIGHT SUCCESSFULLY ADDED.");
					break;
				
				case 2:
					modifyFlightDetails(data);
					break;
				
				case 3:
					System.out.println("\n\tEnter Flight ID:");
					String flightId  = br.readLine();
					if(valideFlightId(flightId, data)) {
					System.out.println(userOp.viewFlight(flightId, data).toString());
					}
					else {
						try {
						throw new UserException("Invalid Flight ID");
						}
						catch(UserException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
				case 4:
					userOp.viewAllFlights(data).entrySet().stream().forEach(i -> System.out.println(i.toString()));
					break;
				
				case 5:
					ScheduledFlight sFlight = scheduleFlight(data);
					System.out.println(sFlight.toString());
					userOp.addScheduledFlight(sFlight, data);
					System.out.println("\n\tNEW FLIGHT IS SCHEDULED.");
					break;

				case 6:
					data.listScheduledFlights.stream().forEach(i -> System.out.println(i.toString()));
					break;
					
				case 7:
					updateAccount(userOp, data, user);
					break;
				
				case 8:
					deleteAccount(userOp, user, data);
					break;
				
				case 9:
					System.out.print("SIGNING OFF");
					try {
						Thread.sleep(500);
						System.out.print(".......");
						Thread.sleep(500);
						System.out.print(".......");
						Thread.sleep(500);
						System.out.print(".......");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					return;
					
				default:
					break;
					
			}
			
			System.out.println("Want to perform more admin operations? [ Y / N ]");
			try {
				cont = br.readLine();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ScheduledFlight scheduleFlight(Database data)  
	{
		
		ScheduledFlight newFlight = new ScheduledFlight();
		Flight flight = inputFlightInfo();
		
		Schedule schedule = new Schedule();
		
		try 
		{
			Date date1 = null;
			Date date2 = null;
			while(true) 
			{
				System.out.println("\n\tEnter Departure Date and Time: [ dd-MM-yyyy hh:mm:ss ] ");
				date1 = dateFormat.parse(br.readLine());
				
	
				System.out.println("\n\tEnter Arrival Date and Time: [ dd-MM-yyyy hh:mm:ss ] ");
				date2 = dateFormat.parse(br.readLine());
				
			
				
				if(date1.compareTo(date2) <= 0)
					break;
				else {
					try {
						throw new UserException("Departure date can not be after arrival date.");
					}
					catch(UserException e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
			
			schedule.setDepartureTime(date1);
			schedule.setArrivalTime(date2);
			
			System.out.println("\n\tFollowing are the Airports:\n");
			data.airportMap.entrySet().stream().forEach(i -> System.out.println(i.getKey()+ " "+ i.getValue().getAirportName()));
			
			
			System.out.println("\n\tEnter ID as a Source of Flight:");
			Airport tempAirport = data.airportMap.get(br.readLine());
			schedule.setSourceAirport(tempAirport);
			
			
			System.out.println("\n\tEnter ID as a Destination of Flight:");
			tempAirport = data.airportMap.get(br.readLine());
			schedule.setDestinationAirport(tempAirport);
			
			int seats = 0;
			while(true)
			{
				System.out.println("\n\tEnter Number of seats.");
				seats= Integer.parseInt(br.readLine());
				if(seats > flight.getSeatCapacity()) {
					try {
						throw new UserException("EXCEPTION: NUMBER OF SEATS ECXCEEDING AVAILABLE SEATS.");
					}
					catch(UserException e) {
						System.out.println(e.getMessage());
					}
				}
				else
					break;
			}

			newFlight.setAvailableSeats(seats);
			
			newFlight.setFlight(flight);

			newFlight.setSchedule(schedule);

			
			return newFlight;
		}
		catch(ParseException | IOException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	public void modifyFlightDetails(Database data) throws IOException {
		String flightId = null;
		String choice = "0";
		try {
			System.out.println("\n\tFollowing are the flights we have:");
			data.flightMap.entrySet().stream().forEach(i -> {
				System.out.println("\n\t"+i.getValue().getFlightNumber());
			});
			System.out.println("\n\tEnter flight ID you want to Modify.");
			flightId = br.readLine();
			System.out.println("Press Key to modify respective attribute of flight:"
					+ "\n\t 1 -> Carrier Name."
					+ "\n\t 2 -> Flight model."
					+ "\n\t 3 -> Seat Capacity.");
			choice = br.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		switch(choice) {
		
			case "1":
				System.out.println("\n\tEnter new Carrier Name.");
				data.flightMap.get(flightId).setCarrierName(br.readLine());
				break;
				
			case "2":
				System.out.println("\n\tEnter New Flight Model:");
				data.flightMap.get(flightId).setFlightModel(br.readLine());
				break;
				
			case "3":
				System.out.println("\n\tEnter New Seat Capacity:");
				data.flightMap.get(flightId).setSeatCapacity(Integer.parseInt(br.readLine()));
				break;
				
			default:
				System.out.println("Ivalid Choice.");
				break;
				
		}
		
	}
	
	public Flight inputFlightInfo() {
		
		
		Flight newFlight = new Flight();
		System.out.println("\n\tENTER CARRIER NAME:");
		try {
			newFlight.setCarrierName(br.readLine());
			System.out.println("\n\tENTER FLIGHT MODEL:");
			newFlight.setFlightModel(br.readLine());
			System.out.println("\n\tENTER SEAT CAPACITY:");
			newFlight.setSeatCapacity(Integer.parseInt(br.readLine()));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		newFlight.setFlightNumber(newFlight.getCarrierName().split("")[0]
								 +newFlight.getCarrierName().split("")[1]
								 + String.valueOf((int)(Math.random()*10000)));
		return newFlight;
	}

	
	//CUSTOMER:
	public void customerOperations( User user, Database data)
	{
		
		while(true) 
		{
			
			System.out.println("\n\n\tWhat task you want to perform:"
							+ "\n\tPress KEY -> TASK"
							+ "\n\t 1 -> Book Flight."
							+ "\n\t 2 -> View the bookings made."
							+ "\n\t 3 -> Cancel Booking."
							+ "\n\t 4 -> Modify Booking. [COMING SOON]"
							+ "\n\t 5 -> Update Profile Details."
							+ "\n\t 6 -> Delete Profile."
							+ "\n\t 7 -> Sign Off.");
			int choice = 0;
			
			try {
				String tempChoice = br.readLine();
				if(!Pattern.matches("[0-9]", tempChoice)) 
					throw new UserException("INVALID CHOICE, PLEASE ENTER SINGLE DIGIT INTEGER.");
				else {
					choice = Integer.parseInt(tempChoice);
				}
			} catch (IOException | UserException e) {
				System.out.println(e.getMessage());
				choice = 10;
			}	
			
			if(choice >= 2 && choice <= 4 && (user.getBookings().size() == 0)) {
				System.out.println("You Have not booked any flights yet.");
				continue;
			}
			
			
			
			switch(choice) {
			
				case 1:
						String array = null;
						try {
							array = inputSourceDestDate();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						HashMap<String, ScheduledFlight> availableFlights = userOp.searchFlights(data, array);
						if(availableFlights.size() != 0) 
						{
							String flightNumber  = getFlightNumber(availableFlights);
							userOp.bookFlight(data, user, availableFlights.get(flightNumber),  getPassengers());
							System.out.println("BOOKING SUCCESSFULL");
						}
					
					break;
					
				case 2:
						displayBookings(user);
					
					break;
				
				case 3:
						HashMap<Long, Booking> bookingList = user.getBookings();
						
						System.out.println("\n\tFollowing are your Booking IDs:");
						bookingList.entrySet().stream().forEach(i -> System.out.println("\n\t"+i.getKey()));
						System.out.println("\n\tEnter booking ID you want to cancel.");
						Long bookingId = null;
						try {
							bookingId = Long.parseLong(br.readLine());
						} catch (NumberFormatException | IOException e) {
							e.printStackTrace();
						}
						
						userOp.cancelBooking(user, bookingId);
						
						System.out.println("\n\tBooking Deleted.");
						System.out.println(user.getBookings());
					
					break;
					
				case 4:
					user.getBookings().entrySet().stream().forEach(i -> System.out.println(i.getKey()));
					userOp.modifyBooking(userOp, data, user);
					break;
					
				case 5:
					updateAccount(userOp, data, user);
					break;
					
				case 6:
					deleteAccount(userOp, user, data);
					break;
				
				default:
					System.out.println("SIGNING OFF.");
					
					try {
						Thread.sleep(500);
						System.out.print(".......");
						Thread.sleep(500);
						System.out.print(".......");
						Thread.sleep(500);
						System.out.print(".......");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}return;
			}	
		}
	}
	
	public String inputSourceDestDate() throws IOException 
	{
		
		System.out.println("Enter Date: [Ex: 02-Feb-2020]");
		String date = br.readLine();
		System.out.println("Enter Source Airport code: [Ex: DEL]");
		String source = br.readLine();
		System.out.println("Enter Destination Airport code: [Ex: HYD]");
		String destination = br.readLine();
		
		return date+"|"+source+"|"+destination;
	}
	
	public String getFlightNumber(HashMap<String,ScheduledFlight> availableFlights) 
	{
		System.out.println("\n\tWE FOUND FOLLOWING FLIGHTS, JUST FOR YOU:");
		availableFlights.entrySet().stream().forEach(i -> {
			
			System.out.println("\nFLIGHT NUMBER: "+i.getValue().getFlight().getFlightNumber()
							  +"\nFLIGHT CARRIER: "+i.getValue().getFlight().getCarrierName()
							  +"\nFLIGHT SEAT CAPACITY:"+i.getValue().getFlight().getSeatCapacity()
							  +"\nFLIGHT MODEL"+i.getValue().getFlight().getFlightModel()
							  +"\n"
							  +"\nDEPARTURE TIME: "+i.getValue().getSchedule().getDepartureTime()
							  +"\nARRIVAL TIME: "+i.getValue().getSchedule().getArrivalTime()
							  +"\n"
							  +"\nAVAILABLE SEATS: "+i.getValue().getAvailableSeats()
							  +"\n----");
		});
		String flightNumber = null;
		while(true)
		{
			System.out.println("\n\tEnter Flight Number: ");
			try {
				flightNumber = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try
			{
				if(!availableFlights.get(flightNumber).equals("")) 
				{
					break;
				}
			}
			catch(NullPointerException e ) 
			{
				System.out.println(e.getMessage());
			}
		}	
		return flightNumber;

	}
	
	public List<Passenger> getPassengers()
	{
		
		try 
		{
			System.out.println("\n\tEnter Number of Passengers:");
			int passNum = Integer.parseInt(br.readLine());
			List<Passenger> passengerList = new ArrayList<>();
			
			for(int i = 0; i < passNum; i++) 
			{	
				Passenger passenger = new Passenger();
				System.out.println("\n\tEnter Paseenger "+(i+1)+" Details:");
					
					System.out.println("\n\tEnter Name:");
					passenger.setPassengerName(br.readLine());
		
					System.out.println("\n\tEnter Age:");
					passenger.setPassengerAge(Integer.parseInt(br.readLine()));
					
					System.out.println("\n\tEnter Luggage:");
					passenger.setLuggage(Double.parseDouble(br.readLine()));
					
					System.out.println("\n\tEnter Unique ID number");
					passenger.setPassengerUIN(Long.parseLong(br.readLine()));
		
					passenger.setPnrNumber((long) ((Math.random()*500000)+12423));
					
					passengerList.add(passenger);
				}
			return passengerList;
			
		}
		catch(NumberFormatException | IOException e) {
			e.getStackTrace();
		}
		return null;
	}

	public void displayBookings(User user) 
{
		
		HashMap<Long, Booking> tempBooking = new HashMap<>();
		tempBooking = user.getBookings();
		
		tempBooking.entrySet().stream().forEach(i -> {
			
			System.out.println("\n\t Booking ID: "+i.getValue().getBookingID()
							  +"\n\t Passenger List: "+ i.getValue().getPassengerList()
							  +"\n\t Flight Schedule: "+i.getValue().getFlight().getSchedule().toString()
							  +"\n\t Flight Details: "+i.getValue().getFlight().getFlight().toString());
			
		});
 		
	}

	public boolean valideFlightId(String flightId, Database data) {
		
		if(data.flightMap.get(flightId) != null) {
			return true;
		}
		
		return false;
		
	}

}