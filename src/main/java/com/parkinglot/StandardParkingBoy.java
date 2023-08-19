package com.parkinglot;

import com.parkinglot.Interface.ParkingBoy;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

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

    public void findCarInParkingLot(ParkingTicket parkingTicket) {
        parkingLotList.stream().
                filter((element) -> element.isParkingTicketValid(parkingTicket))
                .findFirst()
                .ifPresent(element -> parkingLot = element);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        findCarInParkingLot(parkingTicket);
        return parkingLot.fetch(parkingTicket);
    }
}
