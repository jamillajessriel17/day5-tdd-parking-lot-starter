package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_a_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(car);

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_when_fetch_given_parking_ticket_and_parked_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(car);
        ParkingTicket parkingTicket = new ParkingTicket();

        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchCar);
    }
}
