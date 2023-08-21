package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;

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
    public int getNumberOfParkingLot() {
        return parkingLotList.size();
    }

    //TODO: since this method had the same return from all parking boy classes, I suggest this can be put into ParkingBoy class to lessen duplicate methods
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
                    .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                    .orElseThrow(NoAvailablePositionException::new);
        }
    }
}
