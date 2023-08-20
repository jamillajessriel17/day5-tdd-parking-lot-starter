package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
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
        Assertions.assertEquals(smartParkingBoy,parkingLotServiceManager.getParkingBoy());
    }

}
