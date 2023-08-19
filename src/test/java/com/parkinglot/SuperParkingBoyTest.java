package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SuperParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_super_parking_boy_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(9, parkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_a_super_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //when
        Car fetchedCar = superParkingBoy.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchedCar);
    }
    @Test
    void should_return_the_right_car_when_fetch_given_a_parking_lot_with_two_parked_cars_and_a_super_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        //when
        Car fetchCar = superParkingBoy.fetch(parkingTicket);
        Car fetchCar1 = superParkingBoy.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchCar);
        Assertions.assertEquals(car1, fetchCar1);
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_a_parking_lot_a_wrong_parking_ticket_and_a_super_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        ParkingTicket WrongParkingTicket = new ParkingTicket();
        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    superParkingBoy.fetch(WrongParkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_twice_given_a_parking_lot_a_super_parking_boy_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        //when
        superParkingBoy.fetch(parkingTicket);
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    superParkingBoy.fetch(parkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_return_NoAvailablePositionException_when_park_given_super_parking_boy_a_car_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car car1 = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot);
        superParkingBoy.park(car);
        //when
        //then
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    superParkingBoy.park(car1);
                });
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
    @Test
    void should_park_in_the_parking_lot_with_larger_position_rate_when_park_given_a_super_parking_boy_and_two_parking_lot_with_available_positions_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(3,10);
        ParkingLot parkingLot1 = new ParkingLot(4,20);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList);
        //when
        superParkingBoy.park(car);
        //then
        Assertions.assertEquals(2, parkingLot.getAvailableCapacity());
        Assertions.assertEquals(4, parkingLot1.getAvailableCapacity());
    }
    @Test
    void should_return_right_car_when_fetch_the_two_cars_given_super_parking_boy_with_two_parking_lots_both_with_a_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(5);
        Car car = new Car();
        Car car1 = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        ParkingTicket parkingTicket1 = superParkingBoy.park(car1);
        //when
        Car fetchedCar = superParkingBoy.fetch(parkingTicket);
        Car fetchCar1 = superParkingBoy.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchedCar);
        Assertions.assertEquals(car1, fetchCar1);
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_super_parking_boy_with_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(parkingLot, parkingLot1));
        superParkingBoy.park(car);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    superParkingBoy.fetch(wrongParkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_UnrecognizedParkingTicketException_when_fetch_given_super_parking_boy_with_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot();
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = superParkingBoy.park(car);
        superParkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    superParkingBoy.fetch(parkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_return_NoAvailablePositionException_when_park_given_super_parking_boy_with_two_full_parking_lots() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot(0);
        Car car = new Car();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(parkingLot, parkingLot1));
        //when
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    superParkingBoy.park(car);
                });
        //then
        Assertions.assertEquals("No available position.",noAvailablePositionException.getMessage());
    }
}
