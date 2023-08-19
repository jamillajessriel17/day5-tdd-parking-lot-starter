package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;

public class StandardParkingBoy {

    private final ParkingLot parkingLot;


    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car){
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.addToTicketCarMap(parkingTicket,car);
        return parkingTicket ;
    }
}

//    private final List<ParkingLot> parkingLotList;
//public StandardParkingBoy(List<ParkingLot> parkingLotList) {
//    this.parkingLotList = parkingLotList;
//}

//        return parkingLotList.stream()
//                .filter(ParkingLot::hasAvailableCapacity)
//                .findFirst()
//                .orElseThrow(NoAvailablePositionException::new)
//                .park(car);
