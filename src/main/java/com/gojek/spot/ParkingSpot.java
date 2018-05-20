package com.gojek.spot;

        import com.gojek.vehicle.Vehicle;


public class ParkingSpot implements Comparable<ParkingSpot> {
    private int spotNumber;
    private Vehicle vehicle;
    private SpotSize capacity;

    public ParkingSpot(int spotNumber, Vehicle vehicle, SpotSize size) {
        this.spotNumber = spotNumber;
        this.vehicle = vehicle;
        this.capacity = size;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }

    @Override
    public int hashCode() {
        int hashCode=0;
        hashCode = spotNumber * 13;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        ParkingSpot spot = (ParkingSpot) obj;
        return this.spotNumber == spot.spotNumber;
    }

    public int compareTo(ParkingSpot spot) {
        return this.spotNumber - spot.spotNumber;
    }
}
