package com.gojek.parkinglot;

import com.gojek.spot.ParkingSpot;
import com.gojek.spot.SpotSize;
import com.gojek.vehicle.Car;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLotImpl implements IParkingLot {

    private Map<ParkingSpot, Car> parkedCarMap = null;
    private int park_lot_max_size;
    private TreeSet<ParkingSpot> availableSpots = null;


    public void createParkingLot(String size) {
        int _size = Integer.parseInt(size);
        park_lot_max_size = _size;
        parkedCarMap = new HashMap<ParkingSpot, Car>();
        availableSpots = new TreeSet<ParkingSpot>();
        for (int index = 1; index < park_lot_max_size; index++) {
            availableSpots.add(new ParkingSpot(index, null, SpotSize.Large));
        }
    }

    public void park(Car input) {
        if (availableSpots == null) {
            System.out.println("Woof No Parking lot present create it first");
        } else if (availableSpots.size() == 0 && parkedCarMap.size() == park_lot_max_size) {
            System.out.println("Oops!! Parking Full");
        } else {
            parkedCarMap.put(availableSpots.first(), input);
            availableSpots.remove(availableSpots.first());
        }

    }

    public void unpark(ParkingSpot spot) {
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0 || spot.getSpotNumber() > park_lot_max_size) {
            System.out.print("Car not parked");
        } else {
            parkedCarMap.remove(spot);
            availableSpots.add(spot);
            System.out.println("Spot "spot.getSpotNumber() + " is Vacant");
        }


    }

    public String getParkingSpotForRegistration(String number) {
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            return "Parking is empty";
        } else {
            for (Map.Entry<ParkingSpot, Car> entry : parkedCarMap.entrySet()) {
                if (entry.getValue().getLicensePlate().equalsIgnoreCase(number)) {
                    return entry.getKey().getSpotNumber() + "";
                }
            }

        }
        return "not found";

    }

    public String getParkingSpotForColour(String colour) {
        String carEntries = "";
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            return "Parking is empty";
        } else {
            for (Map.Entry<ParkingSpot, Car> entry: parkedCarMap.entrySet()){
                if (entry.getValue().getColour().equalsIgnoreCase(colour)){
                    carEntries = carEntries + entry.getKey().getSpotNumber()+",";
                }
            }
        }
        return carEntries.isEmpty()?"Car not present":carEntries;

    }

    public void printStatus() {
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            System.out.println("Parking Lot is empty");
        } else {
            System.out.println("Spot No. \t Registration No \t Colour");
            for (Map.Entry<ParkingSpot, Car> entry : parkedCarMap.entrySet()) {
                int slotNo = entry.getKey().getSpotNumber();
                Car sampleCar = entry.getValue();
                System.out.println(slotNo + "\t" + sampleCar.getLicensePlate() + "\t" + sampleCar.getColour());

            }

        }
    }
}
