package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SmartParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_smart_parking_boy_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(9, parkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_a_smart_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //when
        Car fetchedCar = smartParkingBoy.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchedCar);
    }
    @Test
    void should_return_the_right_car_when_fetch_given_a_parking_lot_with_two_parked_cars_and_a_smart_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);
        Car fetchCar1 = smartParkingBoy.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchCar);
        Assertions.assertEquals(car1, fetchCar1);
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_a_parking_lot_a_wrong_parking_ticket_and_a_smart_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket WrongParkingTicket = new ParkingTicket();
        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    smartParkingBoy.fetch(WrongParkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_twice_given_a_parking_lot_a_smart_parking_boy_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //when
        smartParkingBoy.fetch(parkingTicket);
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    smartParkingBoy.fetch(parkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_return_NoAvailablePositionException_when_park_given_smart_parking_boy_a_car_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car car1 = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        smartParkingBoy.park(car);
        //when
        //then
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    smartParkingBoy.park(car1);
                });
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
    @Test
    void should_park_in_the_parking_lot_with_more_empty_positions_when_park_given_a_smart_parking_boy_and_two_parking_lot_with_available_positions_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(9);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        //when
        smartParkingBoy.park(car);
        //then
        Assertions.assertEquals(1, parkingLot.getAvailableCapacity());
        Assertions.assertEquals(8, parkingLot1.getAvailableCapacity());
    }
    @Test
    void should_return_right_car_when_fetch_the_two_cars_given_smart_parking_boy_with_two_parking_lots_both_with_a_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(5);
        Car car = new Car();
        Car car1 = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        //when
        Car fetchedCar = smartParkingBoy.fetch(parkingTicket);
        Car fetchCar1 = smartParkingBoy.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchedCar);
        Assertions.assertEquals(car1, fetchCar1);
    }
    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_smart_parking_boy_with_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot, parkingLot1));
        smartParkingBoy.park(car);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    smartParkingBoy.fetch(wrongParkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }
    @Test
    void should_UnrecognizedParkingTicketException_when_fetch_given_smart_parking_boy_with_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot();
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(parkingTicket);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    smartParkingBoy.fetch(parkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }


}