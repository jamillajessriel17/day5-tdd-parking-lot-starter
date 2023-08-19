package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;

import java.util.List;

public class SmartParkingBoy implements ParkingBoy {
    private ParkingLot parkingLot;

    public SmartParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {

        return parkingLot.fetch(parkingTicket);
    }

    @Override
    public void findCarInParkingLot(ParkingTicket parkingTicket) {

    }

    @Override
    public void selectParkingLot(List<ParkingLot> parkingLotList) {

    }
}
