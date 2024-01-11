package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filters.ArrivalBeforeDepartureDateFilter;
import com.gridnine.testing.filters.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.filters.MoreThanTwoHoursOnTheGroundFilter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void splitOffers() {
        System.out.println(" "+"\n#####################################################################################################################################");
    }

    public static void main(String[] args) {

        List<Flight> flight = FlightBuilder.createFlights();

        splitOffers();
        System.out.println("Вывод всех рейсов: ");
        flight.forEach(System.out::println);

        splitOffers();
        System.out.println("Фильтрация которыая исключает те рейсы, у которых вылет до текущего момента времени: ");
        List<Flight> filter1 = new DepartureBeforeCurrentTimeFilter(LocalDateTime.now()).filter(flight);
        filter1.forEach(System.out::println);

        splitOffers();
        System.out.println("Фильтрация которая исключает те рейсы,у которых имеются сегменты с датой прилёта раньше даты вылета: ");
        List<Flight> filter2 = new ArrivalBeforeDepartureDateFilter().filter(flight);
        filter2.forEach(System.out::println);

        splitOffers();
        System.out.println("Фильтрация которая исключает только те рейсы, у которых общее время, проведенное на земле между сегментами, превышает 2 часа: ");
        List<Flight> filter3 = new MoreThanTwoHoursOnTheGroundFilter(2).filter(flight);
        filter3.forEach(System.out::println);
    }

}