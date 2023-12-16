package es.ull.passengers;
import es.ull.flights.Flight;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        // Initialize a Passenger object before each test
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    @Test
    void testGetIdentifier() {
        assertEquals("ID123", passenger.getIdentifier());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AB123", 50);
        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testJoinFlightWithPreviousFlight() {
        Flight flight1 = new Flight("AB123", 50);
        Flight flight2 = new Flight("CD456", 50);

        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);

        assertEquals(flight2, passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
        assertEquals(1, flight2.getNumberOfPassengers());
    }

    @Test
    void testSetFlight() {
        Flight flight = new Flight("AB123", 50);
        passenger.setFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testToString() {
        assertEquals("Passenger John Doe with identifier: ID123 from US", passenger.toString());
    }
}
