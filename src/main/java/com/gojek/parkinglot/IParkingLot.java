package com.gojek.parkinglot;

import com.gojek.exceptions.ParkingLotNotPresent;
import com.gojek.exceptions.ParkingOverflowException;
import com.gojek.spot.ParkingSpot;
import com.gojek.vehicle.Vehicle;

import java.util.ArrayList;


public interface IParkingLot {

    public void printStatus();

    public boolean unpark(ParkingSpot spot);

    public ParkingSpot park(Vehicle input) throws ParkingOverflowException,ParkingLotNotPresent;

    public boolean createParkingLot(String size);

    public String getParkingSpotForRegistration(String number);

    public ArrayList<Integer> getParkingSpotForColour(String colour);

    public ArrayList<String> getRegistrationNumberOfColour(String colour);

}
