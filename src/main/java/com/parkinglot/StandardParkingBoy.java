package com.parkinglot;

public class StandardParkingBoy {

    private final ParkingLot parkingLot;


    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLot.addToTicketCarMap(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.getTicketAndCarMap().get(parkingTicket);
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
