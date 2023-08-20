package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.ParkingBoyFailedToDoTheOperationException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingLotServiceManagerTest {

    @Test
    void should_add_parking_boy_when_addParkingBoy_given_parking_lot_service_manager_a_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        //when
        parkingLotServiceManager.addParkingBoy(standardParkingBoy);
        //then
        Assertions.assertEquals(1, parkingLotServiceManager.getParkingBoyListCount());
    }

    @Test
    void should_get_parking_boy_which_has_more_available_parking_lots_when_ask_choose_parking_boy_given_parking_lot_manager_two_parking_boys_with_parking_lots() {
        //given
        ParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(), new ParkingLot()));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(), new ParkingLot(), new ParkingLot()));
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        parkingLotServiceManager.addParkingBoy(standardParkingBoy);
        parkingLotServiceManager.addParkingBoy(smartParkingBoy);
        Car car = new Car();
        //when
        parkingLotServiceManager.chooseParkingBoy();
        //then
        Assertions.assertEquals(smartParkingBoy, parkingLotServiceManager.getParkingBoy());
    }

    @Test
    void should_return_ticket_when_park_given_parking_lot_manager_and_two_parking_boys_with_parking_lots_and_a_car() {
        //given
        ParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(), new ParkingLot()));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(), new ParkingLot(), new ParkingLot()));
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        parkingLotServiceManager.addParkingBoy(standardParkingBoy);
        parkingLotServiceManager.addParkingBoy(smartParkingBoy);
        Car car = new Car();
        parkingLotServiceManager.chooseParkingBoy();
        //when
        ParkingTicket parkingTicket = parkingLotServiceManager.getParkingBoy().park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_manager_and_two_parking_boys_with_parking_lots_and_ticket() {
        //given
        ParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(new ParkingLot(), new ParkingLot()));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(), new ParkingLot(), new ParkingLot()));
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        parkingLotServiceManager.addParkingBoy(standardParkingBoy);
        parkingLotServiceManager.addParkingBoy(smartParkingBoy);
        Car car = new Car();
        parkingLotServiceManager.chooseParkingBoy();
        ParkingTicket parkingTicket = parkingLotServiceManager.getParkingBoy().park(car);
        //when
        Car fetchedCar = parkingLotServiceManager.getParkingBoy().fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_parking_ticket_given_parking_lot_manager_and_a_car_and_parking_lot() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ParkingLot());
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_given_parking_lot_manager_and_a_car_and_parking_lot() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ParkingLot());
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_service_manager_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        //when
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        //then
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertEquals(9, parkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_parked_car_when_fetch_given_parking_lot_with_parked_car_and_a_parking_lot_service_manager() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        //when
        Car fetchedCar = parkingLotServiceManager.fetch(parkingTicket);
        //then
        Assertions.assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_given_a_parking_lot_with_two_parked_cars_and_a_parking_lot_service_manager() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        //when
        Car fetchCar = parkingLotServiceManager.fetch(parkingTicket);
        Car fetchCar1 = parkingLotServiceManager.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchCar);
        Assertions.assertEquals(car1, fetchCar1);
    }

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_a_parking_lot_a_wrong_parking_ticket_and_a_parking_lot_service_manager() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        ParkingTicket WrongParkingTicket = new ParkingTicket();
        //when
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    parkingLotServiceManager.fetch(WrongParkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_twice_given_a_parking_lot_a_parking_lot_service_manager_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        //when
        parkingLotServiceManager.fetch(parkingTicket);
        //then
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    parkingLotServiceManager.fetch(parkingTicket);
                });
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailablePositionException_when_park_given_parking_lot_service_manager_a_car_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car car1 = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        parkingLotServiceManager.park(car);
        //when
        //then
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    parkingLotServiceManager.park(car1);
                });
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_park_in_the_first_parking_lot_when_park_given_a_parking_lot_service_manager_and_two_parking_lot_with_available_positions_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(9);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList);
        //when
        parkingLotServiceManager.park(car);
        //then
        Assertions.assertEquals(0, parkingLot.getAvailableCapacity());
        Assertions.assertEquals(9, parkingLot1.getAvailableCapacity());
    }

    @Test
    void should_park_on_the_second_parking_lot_when_park_given_a_parking_lot_service_manager_two_parking_lots__first_is_full_and_second_has_available_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot(8);
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(parkingLot, parkingLot1);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList);
        //when
        parkingLotServiceManager.park(car);
        //then
        Assertions.assertEquals(7, parkingLot1.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_the_two_cars_given_parking_lot_service_manager_with_two_parking_lots_both_with_a_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(5);
        Car car = new Car();
        Car car1 = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        ParkingTicket parkingTicket1 = parkingLotServiceManager.park(car1);
        //when
        Car fetchedCar = parkingLotServiceManager.fetch(parkingTicket);
        Car fetchCar1 = parkingLotServiceManager.fetch(parkingTicket1);
        //then
        Assertions.assertEquals(car, fetchedCar);
        Assertions.assertEquals(car1, fetchCar1);
    }

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_parking_lot_service_manager_with_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car car = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(List.of(parkingLot, parkingLot1));
        parkingLotServiceManager.park(car);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    parkingLotServiceManager.fetch(wrongParkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_UnrecognizedParkingTicketException_when_fetch_given_parking_lot_service_manager_with_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot();
        Car car = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(List.of(parkingLot, parkingLot1));
        ParkingTicket parkingTicket = parkingLotServiceManager.park(car);
        parkingLotServiceManager.fetch(parkingTicket);
        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class, () -> {
                    parkingLotServiceManager.fetch(parkingTicket);
                });
        //then
        Assertions.assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailablePositionException_when_park_given_parking_lot_service_manager_with_two_full_parking_lots() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingLot parkingLot1 = new ParkingLot(0);
        Car car = new Car();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(List.of(parkingLot, parkingLot1));
        //when
        NoAvailablePositionException noAvailablePositionException = Assertions
                .assertThrows(NoAvailablePositionException.class, () -> {
                    parkingLotServiceManager.park(car);
                });
        //then
        Assertions.assertEquals("No available position.", noAvailablePositionException.getMessage());
    }

    @Test
    void should_return_parking_boy_failed_to_do_the_operation_exception_when_park_given_parking_lot_manager_a_parking_boy_with_a_full_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        parkingLotServiceManager.addParkingBoy(parkingBoy);
        parkingLotServiceManager.chooseParkingBoy();
        Car car = new Car();

        //when
        ParkingBoyFailedToDoTheOperationException parkingBoyFailedToDoTheOperationException = Assertions
                .assertThrows(ParkingBoyFailedToDoTheOperationException.class, () -> {
                    parkingLotServiceManager.commandParkingBoyToPark(car);
                });
        //then
        Assertions.assertEquals("Parking boy failed to the operation.", parkingBoyFailedToDoTheOperationException.getMessage());
    }
    @Test
    void should_return_parking_boy_failed_to_do_the_operation_exception_when_fetch_given_parking_lot_manager_a_parking_boy_with_a_full_parking_lot_and_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        parkingLotServiceManager.addParkingBoy(parkingBoy);
        parkingLotServiceManager.chooseParkingBoy();
        Car car = new Car();
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        parkingLotServiceManager.commandParkingBoyToPark(car);
        //when
        ParkingBoyFailedToDoTheOperationException parkingBoyFailedToDoTheOperationException = Assertions
                .assertThrows(ParkingBoyFailedToDoTheOperationException.class, () -> {
                    parkingLotServiceManager.commandParkingBoyToFetch(wrongParkingTicket);
                });
        //then
        Assertions.assertEquals("Parking boy failed to the operation.", parkingBoyFailedToDoTheOperationException.getMessage());
    }
}
