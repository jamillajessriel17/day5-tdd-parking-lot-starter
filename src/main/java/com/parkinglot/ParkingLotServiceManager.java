package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingLotServiceManager {

    private ParkingBoy parkingBoy;
    private final List<ParkingBoy> parkingBoyManagementList = new ArrayList<>();

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoyManagementList.add(parkingBoy);
    }

    public ParkingLotServiceManager() {
    }

    public int getParkingBoyListCount() {
        return parkingBoyManagementList.size();
    }

    public void chooseParkingBoy() {
        parkingBoyManagementList.stream()
                .max(Comparator.comparingInt(ParkingBoy::getNumberOfParkingLot))
                .ifPresentOrElse(parkingBoy1 -> parkingBoy = parkingBoy1, () -> {
                    throw new NoAvailablePositionException();
                });
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }
}
