package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilter {

    /**
     * Interface responsible for flight filtering
     * @param flights
     */
    List<Flight> filter(List<Flight> flights);
}
