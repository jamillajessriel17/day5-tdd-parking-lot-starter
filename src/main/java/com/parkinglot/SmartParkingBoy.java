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
    public void findCarInParkingLots(ParkingTicket parkingTicket) {
        parkingLotList.stream().
                filter((element) -> element.isParkingTicketValid(parkingTicket))
                .findFirst()
                .ifPresent(element -> parkingLot = element);
    }


    @Override
    public int getNumberOfParkingLot() {
        return parkingLotList.size();
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        findCarInParkingLots(parkingTicket);
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
