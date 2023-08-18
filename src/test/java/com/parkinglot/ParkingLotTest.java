package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_a_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_when_fetch_given_parking_ticket_and_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchCar);
    }

    @Test
    void should_the_right_car_when_fetch_twice_given_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        //when
        Car fetchCar = parkingLot.fetch(parkingTicket);
        Car fetchCar1 = parkingLot.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchCar);
        Assertions.assertEquals(car1, fetchCar1);
    }

    @Test
    void should_null_when_fetch_given_parking_lot_and_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //when
        Car fetchCar = parkingLot.fetch(wrongParkingTicket);
        //then
        Assertions.assertNull(fetchCar);
    }

    @Test
    void should_null_when_fetch_given_parking_lot_and_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        parkingLot.fetch(parkingTicket);
        Car fetchCarOnThe2ndTime = parkingLot.fetch(parkingTicket);
        //then
        Assertions.assertNull(fetchCarOnThe2ndTime);
    }

    @Test
    void should_return_null_when_park_given_parking_lot_without_position_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car parkedCar = new Car();
        parkingLot.park(parkedCar);
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        Assertions.assertNull(parkingTicket);
    }
}
