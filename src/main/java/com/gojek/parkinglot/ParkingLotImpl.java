package com.gojek.parkinglot;

import com.gojek.spot.ParkingSpot;
import com.gojek.spot.SpotSize;
import com.gojek.vehicle.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLotImpl implements IParkingLot {

    private Map<ParkingSpot, Car> parkedCarMap = null;
    private int park_lot_max_size;
    private TreeSet<ParkingSpot> availableSpots = null;


    @Override
    public void createParkingLot(String size) {
        int _size = Integer.parseInt(size);
        park_lot_max_size = _size;
        parkedCarMap = new HashMap<ParkingSpot, Car>();
        availableSpots = new TreeSet<ParkingSpot>();
        for (int index = 1; index < park_lot_max_size; index++) {
            availableSpots.add(new ParkingSpot(index, null, SpotSize.Large));
        }
    }

    @Override
    public void park(Car input){
        if (availableSpots == null){
            System.out.println("Woof No Parking lot present create it first");
        }
        if (availableSpots.size() ==0 && parkedCarMap.size() == park_lot_max_size ){
            System.out.println("Oops!! Parking Full");
        }

    }


}
