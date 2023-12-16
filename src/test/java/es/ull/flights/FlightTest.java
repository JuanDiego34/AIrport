package es.ull.flights;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        // Initialize a Flight object before each test
        flight = new Flight("AB123", 50);
    }

    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidNumber", 100));
    }

    @Test
    void testNotEnoughSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        smallFlight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }

    @Test
    void testAddPassengerToFullFlight() {
        Flight fullFlight = new Flight("CD789", 2);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        Passenger passenger3 = new Passenger("ID003", "Charlie", "UK");

        fullFlight.addPassenger(passenger1);
        fullFlight.addPassenger(passenger2);

        assertThrows(RuntimeException.class, () -> fullFlight.addPassenger(passenger3));
    }

    @Test
    void testRemoveNonexistentPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertFalse(flight.removePassenger(passenger));
    }
}
