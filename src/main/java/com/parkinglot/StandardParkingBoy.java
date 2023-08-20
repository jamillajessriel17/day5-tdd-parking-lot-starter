package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingBoy implements ParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public StandardParkingBoy() {
    }

    public StandardParkingBoy(ParkingLot parkingLot, List<ParkingLot> parkingLotList) {
        this.parkingLot = parkingLot;
        this.parkingLotList = parkingLotList;
    }

    @Override
    public int getNumberOfParkingLot() {
        return parkingLotList.size();
    }

    @Override
    public void selectParkingLot(List<ParkingLot> parkingLotList) {
        if (parkingLotList.size() > 0) {
            parkingLot = parkingLotList.stream()
                    .filter(ParkingLot::hasAvailableCapacity)
                    .findFirst().orElseThrow(NoAvailablePositionException::new);

        }
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
    public Car fetch(ParkingTicket parkingTicket) {
        findCarInParkingLots(parkingTicket);
        return parkingLot.fetch(parkingTicket);
    }
}
