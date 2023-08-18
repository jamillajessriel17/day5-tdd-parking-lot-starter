package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {


    Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {

        return ticketCarMap.get(parkingTicket);
    }
}
