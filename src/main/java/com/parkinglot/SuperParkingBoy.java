package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;

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
        selectParkingLot(parkingLotList);
        return parkingLot.park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        if (!parkingLotList.isEmpty()) {
            parkingLot = findCarInParkingLots(parkingTicket, parkingLotList);
        }
        return parkingLot.fetch(parkingTicket);
    }

    @Override
    public void selectParkingLot(List<ParkingLot> parkingLotList) {
        if (parkingLotList.size() > 0) {
            parkingLot = parkingLotList.stream()
                    .filter(ParkingLot::hasAvailableCapacity)
                    .max((a, b) -> (int) (calculateRate(a) - calculateRate(b)))
                    .orElseThrow(NoAvailablePositionException::new);
        }
    }

    private double calculateRate(ParkingLot parkingLot) {
        return ((double) (parkingLot.getAvailableCapacity()) / (double) parkingLot.getInitialCapacity()) * 100d;
    }

    @Override
    public int getNumberOfParkingLot() {
        return parkingLotList.size();
    }
}
