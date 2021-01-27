import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class to read CSV-style records of animal sighting reports.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class FlightReader
{
    // How many fields are expected.
    private static final int NUMBER_OF_FIELDS = 7;
    // Index values for the fields in each record.
    private static final int CODE = 0,
                             NUMBER = 1,
                             GATE = 2,
                             DEPARTURE = 3,
                             ARRIVAL = 4,
                             DESTINATION =5,
                             PILOT = 6;
                             
    /**
     * Create a FlightReader.
     */
    public FlightReader()
    {
    }
    
    /**
     * Read flights in CSV format from the given file.
     * Return an ArrayList of Flight objects created from
     * the information in the file.
     * 
     * @param filename The file to be read - should be in CSV format.
     * @return A list of Flights.
     */
    public ArrayList<Flight> getFlights(String filename)
    {
        // Create a Flight from a CSV input line.
        Function<String, Flight> createFlight = 
            record -> {
                           String[] parts = record.split(",");
                           if(parts.length == NUMBER_OF_FIELDS) {
                               try {
                                   String airlineCode = parts[CODE].trim();
                                   int flightNumber = Integer.parseInt(parts[NUMBER].trim());
                                   int gate = Integer.parseInt(parts[GATE].trim());
                                   String departureTime = parts[DEPARTURE].trim();
                                   String arrivalTime = parts[ARRIVAL].trim();
                                   String destination = parts[DESTINATION].trim();
                                   String pilot = parts[PILOT].trim();
                                   return new Flight(airlineCode,flightNumber, gate, departureTime, 
                                                     arrivalTime, destination, pilot);
                               }
                               catch(NumberFormatException e) {
                                   System.out.println("Flight record has a malformed integer: " + record);
                                   return null;
                               }
                           }
                           else {
                               System.out.println("Flight record has the wrong number of fields: " + record);
                               return null;
                           }
                       };
        ArrayList<Flight> flights;
        try {
            flights = Files.lines(Paths.get(filename))
                             .filter(record -> record.length() > 0 && record.charAt(0) != '#')
                             .map(createFlight)
                             .filter(sighting -> sighting != null)
                             .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            flights = new ArrayList<>();
        }
        return flights;
    }
    
}
