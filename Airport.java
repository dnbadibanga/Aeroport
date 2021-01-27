import java.util.ArrayList;
import java.util.Iterator;
/**
 * Airport class utilizes objects of class Flight and appends them to an 
 * array. 
 *
 * @author Divine Badibanga(201765203) and Kelachi Charles-Beke(201651312)
 * @version 10.05.2019
 */
public class Airport
{
    // instance variables
    private ArrayList<Flight> timetable;

    /**
     * Constructor for objects of class Airport
     */
    public Airport()
    {
        // initialise instance variables
        //array list to store flight objects
        timetable = new ArrayList();
    }
    
    /**
     * Add the flights recorded in the given filename to the timetable (replacing previous flights).
     * @param filename A CSV file of Flight records.
     */
    public void addFlightsFromFile(String filename)
    {
        FlightReader reader = new FlightReader();
        timetable = new ArrayList<>();
        timetable.addAll(reader.getFlights(filename));
    }

    /**
     * create a flight and append it to the array
     *
     * @param  String airlineCode the airline code
     * int flightNumber flight number
     * int gate gate number
     * String departureTime departure time for the flight
     * String arrivalTime arrival time for the flight
     * String destination the destination
     * String pilot pilot's name
     */
    public void addFlight(String airlineCode, int flightNumber, int gate, String depTime, 
                      String arrTime, String destination, String pilot)
    {
        // adds a single flight to the timetable
        Flight newFlight;
        newFlight = new Flight(airlineCode, flightNumber, gate, depTime, 
                        arrTime, destination, pilot);
        timetable.add(newFlight);
    }
    
    /**
     * print all flights in the timetable
    */
    public void printAllFlights()
    {
        // prints for all flights the corresponding info
        // flights are separated by an empty line
        for(Flight flight : timetable){
        flight.printInfo();}
    }
    
    /**
     * Print all flights going to a particular destination
     * @param String destination the destinatino
       */
    public void printFlightsTo(String destination)
    {
        // prints all flights to the given destination
        // flights are separated by an empty line
        timetable.stream()
        .filter(f -> f.getDestination().equals(destination))
        .forEach(f -> f.printInfo());
    }
    
    /**
     * Print all flights leaving before a specific time
     * @param String time departure time
       */
    public void printFlightsBefore(String time)
    {
        // prints all flights which depart before the given time 
        // one flight per line
        String time0 = time.substring(0,2) + time.substring(3,5);
        Integer time1 = new Integer(time0);
        int time2 = time1.intValue();
        timetable.stream()
        .filter(f -> f.getDepTime()<time2)
        .forEach(f -> f.printInfo());
    }
    
    /**
     * Print all flights going to a particular destination leaving before
     * a particular time
     * @param String destination the destination
     * @param String time departure time
       */
    public void printFlightsToBefore(String destination,String time)
    {
        // prints all flights to the given destination which depart before the given time 
        // one flight per line
        String time0 = time.substring(0,2) + time.substring(3,5);
        Integer time1 = new Integer(time0);
        int time2 = time1.intValue();
        timetable.stream()
        .filter(f->f.getDestination().equals(destination))
        .filter(f->f.getDepTime()<time2)
        .forEach(f->f.printInfo());
    }
    
    /**
     * Print all flights leaving after a particular time
     * @param String time departure time
       */
    public void printFlightsAfter(String time)
    {
        // prints all flights which depart after the given time 
        // one flight per line
        String time0 = time.substring(0,2) + time.substring(3,5);
        Integer time1 = new Integer(time0);
        int time2 = time1.intValue();
        timetable.stream()
        .filter (f->f.getArrTime()>time2)
        .forEach(f->f.printInfo());
    }
    
    /**
     * Print all flights that depart from the given gate
     * @param int gate gate number
       */
    public void gateDepartures(int gate)
    {
        // prints all flights that depart from the given gate 
        // one flight per line
        timetable.stream()
        .filter(f-> f.getGate()==(gate))
        .forEach(f-> f.printInfo());
        
    }
    
    /**
     * Returns the number of flights going to a given destination
     * @param String destination the destination
     * @Return int flightsTo the number of flights
       */
    public int numberOfFlightsTo(String destination)
    {
        // returns number of flights to a given destination
        int flightsTo;
        ArrayList<String> flights;
        flights = new ArrayList();
        
        timetable.stream()
        .filter(f-> f.getDestination().equals(destination))
        .forEach(f-> flights.add(f.getDestination()));
        
        flightsTo = flights.size();
        return flightsTo;
    }
    
    /**
     * Removes the flight with the given airline code and flight number from 
     * the timetable
     * @param String airlineCode the airline code
     * @param int flightNumber the flight number
       */
    public void cancelFlight(String airlineCode, int flightNumber)
    {
        // removes given flight from the timetable
        Iterator<Flight> it = timetable.iterator();
        while (it.hasNext()){
            Flight flightx = it.next();
            if (flightx.getAirlineCode().equals(airlineCode) &&
            flightx.getFlightNumber() == flightNumber){
                it.remove();}
        }
    }
    
    /**
     * Removes all flights going to the given destination from the timetable
     * @param String destination
       */
    public void cancelFlightsTo(String destination)
    {
        // removes all flights to the given destination from the the timetable
        Iterator<Flight> it = timetable.iterator();
        while (it.hasNext()){
            if (it.next().getDestination().equals(destination)){
                it.remove();}
            }
    }
    
    /**
     * Returns the total time a pilot will be flying
     * @param String pilot
     * @Return pilotLoad
       */
    public int getPilotLoad(String pilot)
    {
        // calculates the time a given pilot will be flying (according to the timetable) 
        int pilotLoad = timetable.stream()
        .filter(f-> f.getPilot().equals(pilot))
        .map(f-> f.flightDuration())
        .reduce(0, (total, acc) -> total + acc);
        return pilotLoad;
    }
    
    /**
     * Creates 10 flights
       */
    public void createFlight(){
        
        timetable.clear();
        
        Flight flight1 = new Flight("AC",100,2,"15:00","22:00","YYT","Maia");
        timetable.add(flight1);
        Flight flight2 = new Flight("LH",101,1,"17:02","18:00","YYZ","Luke");
        timetable.add(flight2);
        Flight flight3 = new Flight("AF",102,9,"10:00","11:45","MRU","Maia");
        timetable.add(flight3);
        Flight flight4 = new Flight("AC",103,2,"20:00","22:00","MRU","Ally");
        timetable.add(flight4);
        Flight flight5 = new Flight("AC",104,9,"12:10","15:07","YYT","Ally");
        timetable.add(flight5);
        Flight flight6 = new Flight("AF",105,5,"17:05","17:45","MEL","Maia");
        timetable.add(flight6);
        Flight flight7 = new Flight("LH",106,4,"16:22","19:00","HAV","John");
        timetable.add(flight7);
        Flight flight8 = new Flight("WS",107,2,"02:00","04:15","LHR","Bo");
        timetable.add(flight8);
        Flight flight9 = new Flight("SQ",108,1,"14:00","15:11","YYT","Maya");
        timetable.add(flight9);
        Flight flight10 = new Flight("AC",109,3,"18:30","22:00","FRA","Lorenzo");
        timetable.add(flight10);
        
    }
    
}
