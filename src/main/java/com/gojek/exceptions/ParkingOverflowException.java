package com.gojek.exceptions;

public class ParkingOverflowException extends Exception {

    public ParkingOverflowException(String message){
        super(message);
    }
}
