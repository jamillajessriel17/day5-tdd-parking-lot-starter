package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTicketException;
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

    @Test
    void should_return_the_right_car_when_fetch_given_a_parking_lot_with_two_parked_cars_and_a_standard_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //when
        Car fetchCar = standardParkingBoy.fetch(parkingTicket);
        Car fetchCar1 = standardParkingBoy.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchCar);
        Assertions.assertEquals(car1, fetchCar1);
    }

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_a_parking_lot_a_wrong_parking_ticket_and_a_standard_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket WrongParkingTicket = new ParkingTicket();
        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    standardParkingBoy.fetch(WrongParkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
}
