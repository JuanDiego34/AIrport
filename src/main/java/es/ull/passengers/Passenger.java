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
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Representa a un pasajero con información como identificador, nombre, país y vuelo asociado.
 */
public class Passenger {

    /**
     * @brief Identificador único del pasajero.
     */
    private String identifier;

    /**
     * @brief Nombre del pasajero.
     */
    private String name;

    /**
     * @brief Código de país del pasajero en formato ISO 3166-1 alpha-2.
     */
    private String countryCode;

    /**
     * @brief Vuelo al que está asociado el pasajero.
     */
    private Flight flight;

    /**
     * @brief Constructor que inicializa un pasajero con identificador, nombre y código de país.
     * @param identifier Identificador único del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código de país en formato ISO 3166-1 alpha-2.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador único del pasajero.
     * @return Identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     * @return Nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código de país del pasajero.
     * @return Código de país en formato ISO 3166-1 alpha-2.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo al que está asociado el pasajero.
     * @return Vuelo asociado al pasajero.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asocia al pasajero con un vuelo, desvinculando el pasajero de su vuelo anterior si lo tenía.
     * @param flight Vuelo al que se unirá el pasajero.
     * @throws RuntimeException Si no se puede agregar o quitar al pasajero del vuelo.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Establece el vuelo asociado al pasajero.
     * @param flight Vuelo al que se asociará el pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Devuelve una representación de cadena del pasajero.
     * @return Cadena que describe al pasajero.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
