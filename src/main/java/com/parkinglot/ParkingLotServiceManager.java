package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingLotServiceManager extends StandardParkingBoy{

    private ParkingBoy parkingBoy;
    private final List<ParkingBoy> parkingBoyManagementList = new ArrayList<>();

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }
    public ParkingLotServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingLotServiceManager() {
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoyManagementList.add(parkingBoy);
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
