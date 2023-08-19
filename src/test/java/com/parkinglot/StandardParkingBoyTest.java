package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}
