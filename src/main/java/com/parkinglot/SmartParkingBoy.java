package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy implements ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public SmartParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public ParkingTicket park(Car car) {
        selectParkingLot(parkingLotList);
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
        if (parkingLotList.size() > 0) {
            parkingLot = parkingLotList.stream()
                    .filter(ParkingLot::hasAvailableCapacity)
                    .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                    .orElse(null);
        }
    }
}
