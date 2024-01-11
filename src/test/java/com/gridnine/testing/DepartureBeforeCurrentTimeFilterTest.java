package com.gridnine.testing;

import com.gridnine.testing.filters.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DepartureBeforeCurrentTimeFilterTest {

    @Test
    void departureBeforeCurrentTimeFilterTest() {
        LocalDateTime time = LocalDateTime.now();

        Flight flightWithCurrentTime = new Flight(Arrays.asList(
                new Segment(time.minusDays(2), time.plusHours(2)), new Segment(time.plusHours(6), time.plusHours(7))));
        Flight flightWithoutCurrentTime = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2)), new Segment(time.plusHours(3), time.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithCurrentTime, flightWithoutCurrentTime);


        FlightFilter flightFilter = new DepartureBeforeCurrentTimeFilter(time);
        List<Flight> flightsResult = flightFilter.filter(flights);
        List<Flight> flightsExpected = List.of(flightWithoutCurrentTime);

        Assertions.assertEquals(flightsResult, flightsExpected);
    }
}
