package com.parkinglot.Interface;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.UnrecognizedParkingTicketException;

import java.util.List;

public interface ParkingBoy {
     ParkingTicket park(Car car);
     Car fetch(ParkingTicket parkingTicket);
      void  selectParkingLot(List<ParkingLot> parkingLotList);
      default ParkingLot findCarInParkingLots(ParkingTicket parkingTicket, List<ParkingLot>parkingLotList){
         return  parkingLotList.stream().
                   filter((element) -> element.isParkingTicketValid(parkingTicket))
                   .findFirst()
                 .orElseThrow(UnrecognizedParkingTicketException::new);

     };

     int getNumberOfParkingLot();

}
