package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int capacity = 10;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
    }

    Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (isFull()) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    private boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {

        if (ticketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedParkingTicketException();
        }
        return ticketCarMap.remove(parkingTicket);
    }
}
