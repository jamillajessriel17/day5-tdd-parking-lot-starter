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

    public void selectParkingLot(List<ParkingLot> parkingLotList) {

        if (parkingLotList.size() > 0) {
            parkingLot = parkingLotList.stream()
                    .filter(ParkingLot::hasAvailableCapacity)
                    .findFirst().orElseThrow(NoAvailablePositionException::new);

        }
    }

    public ParkingTicket park(Car car) {
        selectParkingLot(parkingLotList);
        if (!parkingLot.hasAvailableCapacity()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.addToTicketCarMap(parkingTicket, car);
        return parkingTicket;
    }

    public void findCarInParkingLot(ParkingTicket parkingTicket) {
        parkingLotList.stream().
                filter((element) -> element.isParkingTicketValid(parkingTicket))
                .findFirst()
                .ifPresent(element -> parkingLot = element);

    }

    public Car fetch(ParkingTicket parkingTicket) {
        findCarInParkingLot(parkingTicket);
        if (!parkingLot.isParkingTicketValid(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        return parkingLot.getTicketAndCarMap().remove(parkingTicket);
    }
}
