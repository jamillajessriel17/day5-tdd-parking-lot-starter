package com.parkinglot.Interface;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;

import java.util.List;

public interface ParkingBoy {
     ParkingTicket park(Car car);
     Car fetch(ParkingTicket parkingTicket);

     void selectParkingLot(List<ParkingLot> parkingLotList);

}
