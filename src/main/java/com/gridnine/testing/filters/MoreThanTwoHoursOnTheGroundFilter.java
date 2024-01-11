package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Flights where the total time spent on the ground exceeds two hours
 */
public class MoreThanTwoHoursOnTheGroundFilter implements FlightFilter {

    private final int parkingHours;

    public MoreThanTwoHoursOnTheGroundFilter(int parkingHours) {
        this.parkingHours = parkingHours;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateParkingMinutes(flight) <= parkingHours * 60L)
                .collect(Collectors.toList());
    }
    private long calculateParkingMinutes(Flight flight) {
        return IntStream.range(0, flight.getSegments().size() - 1)
                .mapToLong(i -> ChronoUnit.MINUTES.between(
                        flight.getSegments().get(i).getArrivalDate(),
                        flight.getSegments().get(i + 1).getDepartureDate()))
                .sum();
    }
}

