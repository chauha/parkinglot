package com.gojek.spot;

import com.gojek.vehicle.Vehicle;




public class ParkingSpot {
    private int spotNumber;
    private Vehicle vehicle;
    private SpotSize capacity;
    public ParkingSpot(int spotNumber,Vehicle vehicle, SpotSize size){
        this.spotNumber = spotNumber;
        this.vehicle= vehicle;
        this.capacity=size;
    }


}
