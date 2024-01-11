package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Departure to the current point in time
 */
public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    private final LocalDateTime localDateTime;

    public DepartureBeforeCurrentTimeFilter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .limit(1)
                        .allMatch(segment -> !segment.getDepartureDate().isBefore(localDateTime)))
                .collect(Collectors.toList());
    }

}
