package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_standard_parking_boy_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(9, parkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_a_standard_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //when
        Car fetchedCar = standardParkingBoy.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchedCar);
        Assertions.assertEquals(9, parkingLot.getAvailableCapacity());
    }
}
