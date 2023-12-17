package es.ull.flights;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @class FlightTest
 * @brief Contiene pruebas unitarias para verificar el comportamiento de la clase Flight.
 */
class FlightTest {

    /**
     * @brief Vuelo utilizado en las pruebas.
     */
    private Flight flight;

    /**
     * @brief Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        // Inicializa un objeto Flight antes de cada prueba
        flight = new Flight("AB123", 50);
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de getFlightNumber().
     */
    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de getNumberOfPassengers().
     */
    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de addPassenger().
     */
    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    /**
     * @brief Prueba para verificar el correcto funcionamiento de removePassenger().
     */
    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    /**
     * @brief Prueba para verificar que se lance una excepción al proporcionar un número de vuelo no válido.
     */
    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidNumber", 100));
    }

    /**
     * @brief Prueba para verificar que se lance una excepción al intentar agregar un pasajero cuando no hay suficientes asientos.
     */
    @Test
    void testNotEnoughSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        smallFlight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }

    /**
     * @brief Prueba para verificar que se lance una excepción al intentar agregar un pasajero a un vuelo completo.
     */
    @Test
    void testAddPassengerToFullFlight() {
        Flight fullFlight = new Flight("CD789", 2);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        Passenger passenger3 = new Passenger("ID003", "Charlie", "CA");

        fullFlight.addPassenger(passenger1);
        fullFlight.addPassenger(passenger2);

        assertThrows(RuntimeException.class, () -> fullFlight.addPassenger(passenger3));
    }

    /**
     * @brief Prueba para verificar que removePassenger() devuelve false al intentar eliminar un pasajero que no está a bordo.
     */
    @Test
    void testRemoveNonexistentPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertFalse(flight.removePassenger(passenger));
    }
}
