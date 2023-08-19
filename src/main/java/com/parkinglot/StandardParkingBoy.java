package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.List;

public class StandardParkingBoy {

    private ParkingLot parkingLot;

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        selectParkingLot(parkingLotList);
    }

    private void selectParkingLot(List<ParkingLot> parkingLotList) {

        parkingLot = parkingLotList.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);

    }

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (!parkingLot.hasAvailableCapacity()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.addToTicketCarMap(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!parkingLot.isParkingTicketValid(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        return parkingLot.getTicketAndCarMap().remove(parkingTicket);
    }
}
