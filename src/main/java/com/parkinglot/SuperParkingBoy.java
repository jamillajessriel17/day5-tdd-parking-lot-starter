package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;

import java.util.ArrayList;
import java.util.List;

public class SuperParkingBoy implements ParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList = new ArrayList<>();
    public SuperParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public SuperParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }

    @Override
    public void selectParkingLot(List<ParkingLot> parkingLotList) {

    }
}
