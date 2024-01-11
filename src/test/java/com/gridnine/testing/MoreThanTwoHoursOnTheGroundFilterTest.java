package com.gridnine.testing;

import com.gridnine.testing.filters.MoreThanTwoHoursOnTheGroundFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MoreThanTwoHoursOnTheGroundFilterTest {

    @Test
    void moreThanTwoHoursOnTheGroundFilterTest() {
        LocalDateTime time = LocalDateTime.now();
        int hoursOnLand = 2;
        Flight flightWithFiveHoursOnLand = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2)), new Segment(time.plusHours(6), time.plusHours(7))));
        Flight flightWithoutFiveHoursOnLand = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2)), new Segment(time.plusHours(1), time.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithFiveHoursOnLand, flightWithoutFiveHoursOnLand);

        FlightFilter flightFilter = new MoreThanTwoHoursOnTheGroundFilter(hoursOnLand);
        List<Flight> flightsResult = flightFilter.filter(flights);

        List<Flight> flightsExpected = List.of(flightWithoutFiveHoursOnLand);

        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}
