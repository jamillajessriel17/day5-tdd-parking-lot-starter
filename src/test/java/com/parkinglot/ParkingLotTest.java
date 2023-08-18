package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_when_fetch_given_parking_ticket_and_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        Car car = new Car();
        //when
        Car fetchCar = parkingLot.fetch(parkingTicket, car);
        //then
        Assertions.assertEquals(car, fetchCar);
    }
}
