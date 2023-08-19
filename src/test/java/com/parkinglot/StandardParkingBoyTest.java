package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_twice_given_a_parking_lot_a_standard_parking_boy_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //when
        standardParkingBoy.fetch(parkingTicket);
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    standardParkingBoy.fetch(parkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailablePositionException_when_park_given_a_car_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car car1 = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        standardParkingBoy.park(car);
        //when
        //then
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    standardParkingBoy.park(car1);
                });
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_park_in_the_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lot_with_available_positions_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(9);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLotList);
        //when
        standardParkingBoy.park(car);
        //then
        Assertions.assertEquals(0, parkingLot.getAvailableCapacity());
        Assertions.assertEquals(9, parkingLot1.getAvailableCapacity());
    }

    @Test
    void should_park_on_the_second_parking_lot_when_park_given_a_standard_parking_boy_two_parking_lots__first_is_full_and_second_has_available_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot(8);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLotList);
        //when
        standardParkingBoy.park(car);
        //then
        Assertions.assertEquals(7, parkingLot1.getAvailableCapacity());
    }
}
