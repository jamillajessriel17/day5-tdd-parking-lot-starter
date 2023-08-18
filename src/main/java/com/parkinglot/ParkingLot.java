package com.parkinglot;

public class ParkingLot {

    private Car car;

    public ParkingLot(Car car) {
        this.car = car;
    }

    public ParkingTicket park(Car car) {
        this.car = car;
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return car;
    }
}
