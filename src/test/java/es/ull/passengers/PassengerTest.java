package es.ull.passengers;
import es.ull.flights.Flight;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @class PassengerTest
 * @brief Contiene pruebas unitarias para verificar el comportamiento de la clase Passenger.
 */
class PassengerTest {

    /**
     * @brief Pasajero utilizado en las pruebas.
     */
    private Passenger passenger;

    /**
     * @brief Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        // Inicializa un objeto Passenger antes de cada prueba
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de getIdentifier().
     */
    @Test
    void testGetIdentifier() {
        assertEquals("ID123", passenger.getIdentifier());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de getName().
     */
    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de getCountryCode().
     */
    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para verificar que getFlight() devuelve null cuando el pasajero no está asociado a ningún vuelo.
     */
    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de joinFlight().
     */
    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AB123", 50);
        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de joinFlight() cuando el pasajero ya está en otro vuelo.
     */
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

    /**
     * @brief Prueba para verificar el correcto funcionamiento de setFlight().
     */
    @Test
    void testSetFlight() {
        Flight flight = new Flight("AB123", 50);
        passenger.setFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de toString().
     */
    @Test
    void testToString() {
        assertEquals("Passenger John Doe with identifier: ID123 from US", passenger.toString());
    }
}
