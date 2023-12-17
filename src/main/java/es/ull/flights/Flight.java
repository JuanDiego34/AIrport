/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Representa un vuelo con un número de vuelo y un número de asientos disponibles.
 */
public class Flight {

    /**
     * @brief Número de vuelo en el formato "XX###" o "XX####".
     */
    private String flightNumber;

    /**
     * @brief Número total de asientos disponibles en el vuelo.
     */
    private int seats;

    /**
     * @brief Conjunto de pasajeros a bordo del vuelo.
     */
    private Set<Passenger> passengers = new HashSet<>();

    /**
     * @brief Expresión regular para validar el formato del número de vuelo.
     */
    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";

    /**
     * @brief Patrón para la expresión regular del número de vuelo.
     */
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    /**
     * @brief Constructor que inicializa un vuelo con un número de vuelo y un número de asientos.
     * @param flightNumber Número de vuelo a asignar al vuelo.
     * @param seats Número total de asientos disponibles en el vuelo.
     * @throws RuntimeException Si el número de vuelo no cumple con el formato especificado.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Número de vuelo no válido");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Obtiene el número de vuelo.
     * @return Número de vuelo.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Obtiene el número de pasajeros a bordo del vuelo.
     * @return Número de pasajeros.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Añade un pasajero al vuelo.
     * @param passenger Pasajero a añadir.
     * @return true si el pasajero se añadió correctamente, false si no hay asientos disponibles.
     * @throws RuntimeException Si no hay suficientes asientos disponibles.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("No hay suficientes asientos para el vuelo " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Elimina un pasajero del vuelo.
     * @param passenger Pasajero a eliminar.
     * @return true si el pasajero se eliminó correctamente, false si el pasajero no estaba a bordo.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
