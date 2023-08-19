package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int initialCapacity = 10;

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public ParkingLot(int capacity) {
        this.initialCapacity = capacity;
    }

    public ParkingLot() {
    }

    Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    private boolean isFull() {
        return ticketCarMap.size() == initialCapacity;
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!isParkingTicketValid(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        return ticketCarMap.remove(parkingTicket);
    }

    public boolean isParkingTicketValid(ParkingTicket parkingTicket) {
        return ticketCarMap.containsKey(parkingTicket);
    }

    public void addToTicketCarMap(ParkingTicket parkingTicket, Car car) {
        ticketCarMap.put(parkingTicket, car);
    }

    public int getAvailableCapacity() {
        return getInitialCapacity() - ticketCarMap.size();
    }

}
