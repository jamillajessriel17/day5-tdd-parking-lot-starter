package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTicketException;

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
        if(!parkingLot.isParkingTicketValid(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        return parkingLot.getTicketAndCarMap().remove(parkingTicket);
    }
}
