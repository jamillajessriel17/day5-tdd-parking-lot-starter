package com.parkinglot.exception;

public class UnrecognizedParkingTicketException extends RuntimeException {


    public UnrecognizedParkingTicketException() {
        super("Unrecognized parking ticket.");
    }
}
