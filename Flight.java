import java.util.ArrayList;

/**
 * This class creates flight records. 
 *
 * @author Divine Badibanga(201765203) and Kelachi Charles-Beke(201651312)
 * @version 10.05.2019
 */
public class Flight
{
   // instance variables
   private String airlineCode;
   private int flightNumber;
   private int gate;
   private String departureTime;
   private String arrivalTime;
   private String destination;
   private String pilot;

   /**
     * Constructor for objects of class Flight
     * @param String aircode airline code
     * @param int flightNumber the flight number
     * @param int gate the gate number
     * @param String depTime the departure time
     * @param String arrTime the arrival time
     * @param String dest the destination
     * @param String pilotName name of the pilot
     */
   public Flight(String airCode, int flightNumber, int gate, String depTime, 
                      String arrTime, String dest, String pilotName)
   {
       // initialise instance variables
       checkAirlineCode(airCode);
       checkFlightNumber(flightNumber);
       checkGate(gate);
       checkTime(depTime, arrTime);
       checkDestination(dest);
       pilot = pilotName;
   }
    
   /**
    * verify and instantiate airline code as it must be one of the following: 
    * "AC", "LH", "AF", "SQ", "UA", "AA", "WS"
    * @param airCode the airline code
    * @Return true if correctly instantiated
    * @Return false is not correct format
      */
   private boolean checkAirlineCode(String airCode)
   {
       ArrayList<String> codes = new ArrayList();
       codes.add("AC");
       codes.add("LH");
       codes.add("AF");
       codes.add("SQ");
       codes.add("UA");
       codes.add("AA");
       codes.add("WS");
       if (codes.contains(airCode)){
           airlineCode = airCode;
           return true;}
       else {
           return false;}
   }
    
   /**
    * verify and instatiate flight number so it between 1 and 9999
    * @param flightNum the flight number
    * @Return boolean true if instantiated correctly
    * @Return boolean false if incorrect format
      */
   private boolean checkFlightNumber(int flightNum)
   {
       if (flightNumber>=1 ||flightNumber<=9999){
           flightNumber = flightNum;
           return true;}
       else{
           return false;}
   }
    
   /**
    * Verify and instantiate the gate so it is between 1 and 26
    * @param gateNum the gate number
    * @Return boolean true if correctly instantiated
    * @Return boolean false if incorrect format
      */
   private boolean checkGate(int gateNum)
   {
       if (gate>=1 && gate<=26){
           gate = gateNum;
           return true;}
       else {
           return false;}
   }
    
   /**
    * verify and instantiate departure time and arrival time in the correct 
    * format 00:00
    * @param String depTime the departure time
    * @param String arrTime the arrival time
    * @Return boolean true if correctly instantiated
    * @Return boolean false if incorrect format
      */
   private boolean checkTime(String depTime, String arrTime)
   {  
       if (depTime.substring(1,2).equals(":")){
           depTime = "0" + depTime;}
       Integer depTimeHours = Integer.valueOf(depTime.substring(0,2));
       Integer depTimeMins = Integer.valueOf(depTime.substring(3,5));
       if (arrTime.substring(1,2).equals(":")){
           arrTime = "0" + arrTime;}
       Integer arrTimeHours = Integer.valueOf(arrTime.substring(0,2));
       Integer arrTimeMins = Integer.valueOf(arrTime.substring(3,5));
       
       if ((depTimeHours<24 && arrTimeHours<24) && 
       (depTimeMins<60 && arrTimeMins<60) &&
       (depTime.substring(2,3).equals(":"))&&
       (arrTime.substring(2,3).equals(":"))){
           departureTime = depTime;
           arrivalTime = arrTime;
           return true;}
       else {
           return false;}
   }
   
   /**
    * verify and instantiate destination, must be one of the following: 
    * "YYT", "YYZ", "YUL", "LHR", "FRA", "MRU", "HAV", "CDG", "MEL"
    * @param String dest the destination
    * @Return boolean true if correctly instantiated
    * @Return boolean false if incorrect format
      */
   private boolean checkDestination(String dest)
   {
       ArrayList<String> places = new ArrayList();
       places.add("YYT");
       places.add("YYZ");
       places.add("YUL");
       places.add("LHR");
       places.add("FRA");
       places.add("MRU");
       places.add("HAV");
       places.add("CDG");
       places.add("MEL");
       if (places.contains(dest)){
           destination = dest;
           return true;}
       else {
           return false;}
   }
   
   /**
    * calculate the flight duration
    * @Return int duration the flight duration
      */
   public int flightDuration()
   {
       // calculates the duration of the flight in minutes
       String depHours = departureTime.substring(0,2);
       String arrHours = arrivalTime.substring(0,2);
       Integer departHours = new Integer(depHours);
       Integer arrivHours = new Integer(arrHours);
       int departureHours = departHours.intValue();
       int arrivalHours = arrivHours.intValue();
       
       String depMin = departureTime.substring(3,5);
       String arrMin = arrivalTime.substring(3,5);
       Integer departMin = new Integer(depMin);
       Integer arrivMin  = new Integer(arrMin);
       int departureMin = departMin.intValue();
       int arrivalMin = arrivMin.intValue();
       
       if (arrivalHours < departureHours){
           arrivalHours = arrivalHours + 24;}
       int hours = arrivalHours - departureHours;
       if (departureMin != 00){
           hours = hours - 1;}
       int mins = (60-departureMin) + arrivalMin;
       
       int duration = (hours*60) + mins;
       return duration;
   }

   /**
    * prints each of the flights' information
      */
   public void printInfo()
   {
       // print all the information about the flight
       // each field on a new line plus the flight duration
       System.out.println("Airline Code: " + airlineCode);
       System.out.println("Flight Number: " + flightNumber);
       System.out.println("Gate: " + gate);
       System.out.println("Departure Time: " + departureTime);
       System.out.println("Arrival Time: " + arrivalTime);
       System.out.println("Destination: " + destination);
       System.out.println("Flight Duration: " + flightDuration());
       System.out.println("Pilot: " + pilot);
   }
   
   /**
    * returns the destination
    * @Return String destination the destination
      */
   public String getDestination()
   {
       return destination;
   }
   
   
   /**
    * returns the departure time
    * @Return int departure the departure time
      */
   public int getDepTime()
   {
       String depHours = departureTime.substring(0,2);
       String depMins = departureTime.substring(3,5);
       
       String depHoursMins = depHours+depMins;
       Integer dep = new Integer(depHoursMins);
       int departure = dep.intValue();
       
       return departure;
   }
   
   /**
    * Returns arrival time as a string
    * @Return int arrival the arrival time
   */
   public int getArrTime()
   {
       String arrHours = arrivalTime.substring(0,2);
       String arrMins = arrivalTime.substring(3,5);
       
       String arrHoursMins = arrHours + arrMins;
       Integer arriv = new Integer(arrHoursMins);
       int arrival = arriv.intValue();
       
       return arrival;
   }
   
   /**
    * Returns the airline code as a string
    * @Return String airlineCode the airline code
   */
   public String getAirlineCode()
   {
       return airlineCode;
   }
   
   /**
    * Returns the flight number as an int
    * @Return int flightNumber the flight number
   */
   public int getFlightNumber()
   {
       return flightNumber;
   }
   
   
   /**
    * Returns the gate number as an int
    * @Return int gate the gate number
   */
  public int getGate()
   {
       return gate;
   }
   
   
   /**
    * Returns the pilot of the flight as a string
    * @Return String pilot the pilot's name
   */
   public String getPilot()
   {
       return pilot;
   }
    
}
