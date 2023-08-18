package com.parkinglot;

public class ParkingLot {


    public ParkingTicket park(Car car) {
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket parkingTicket, Car car) {
        return car;
    }
}
