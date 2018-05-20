package com.gojek.parkinglot;

import com.gojek.exceptions.ParkingLotNotPresent;
import com.gojek.exceptions.ParkingOverflowException;
import com.gojek.spot.ParkingSpot;
import com.gojek.spot.SpotSize;
import com.gojek.vehicle.Car;
import com.gojek.vehicle.Vehicle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLotImpl implements IParkingLot {
    // region members statrt
    private Object lockObj = new Object();
    private Map<ParkingSpot, Vehicle> parkedCarMap = null;
    private int park_lot_max_size;
    private TreeSet<ParkingSpot> availableSpots = null;

    public static ParkingLotImpl getObject() {
        return new ParkingLotImpl();
    }

    //constructor
    private ParkingLotImpl() {

    }


    //region methods

    public void createParkingLot(String size) {
        int _size = Integer.parseInt(size);
        park_lot_max_size = _size;
        parkedCarMap = new HashMap<ParkingSpot, Vehicle>(_size);
        availableSpots = new TreeSet<ParkingSpot>();
        for (int index = 1; index <= park_lot_max_size; index++) {
            availableSpots.add(new ParkingSpot(index, null, SpotSize.Large));
        }
    }

    public ParkingSpot park(Vehicle input) throws ParkingOverflowException, ParkingLotNotPresent {
        Car car = (Car) input;
        synchronized (lockObj) {
            if (availableSpots == null) {
                throw new ParkingLotNotPresent("!!Woof Parking Lot not Present , Create it first");
            } else if (availableSpots.size() == 0 && parkedCarMap.values().size() == park_lot_max_size) {
                throw new ParkingOverflowException("!!Oops Parking is Full");
            } else {
                ParkingSpot assignedSpot = availableSpots.first();
                parkedCarMap.put(assignedSpot, car);
                availableSpots.remove(assignedSpot);
                return assignedSpot;

            }
        }
    }

    public boolean unpark(ParkingSpot spot) {
        synchronized (lockObj) {
            if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0 || spot.getSpotNumber() > park_lot_max_size) {
                return false;
            } else {
                if (parkedCarMap.containsKey(spot)) {
                    parkedCarMap.remove(spot);
                    availableSpots.add(spot);
                } else {
                    System.out.println("Spot not present");
                }

                return true;
            }
        }
    }

    public String getParkingSpotForRegistration(String number) {
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            return "Parking is empty";
        } else {
            for (Map.Entry<ParkingSpot, Vehicle> entry : parkedCarMap.entrySet()) {
                if (entry.getValue().getLicensePlate().equalsIgnoreCase(number)) {
                    return entry.getKey().getSpotNumber() + "";
                }
            }

        }
        return "not found";

    }

    public ArrayList<Integer> getParkingSpotForColour(String colour) {
        ArrayList<Integer> spots = new ArrayList<Integer>();
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            return null;
        } else {
            for (Map.Entry<ParkingSpot, Vehicle> entry : parkedCarMap.entrySet()) {
                if (entry.getValue().getColour().equalsIgnoreCase(colour)) {
                    spots.add(entry.getKey().getSpotNumber());
                }
            }
        }
        return spots;

    }

    public ArrayList<String> getRegistrationNumberOfColour(String colour) {
        ArrayList<String> regs = new ArrayList<String>();
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            return null;
        } else {
            for (Map.Entry<ParkingSpot, Vehicle> entry : parkedCarMap.entrySet()) {
                if (entry.getValue().getColour().equalsIgnoreCase(colour)) {
                    regs.add(entry.getValue().getLicensePlate());
                }
            }
        }
        return regs;

    }

    public void printStatus() {
        if (availableSpots == null || parkedCarMap == null || parkedCarMap.size() == 0) {
            System.out.println("Parking Lot is empty");
        } else {
            System.out.println("SpotNo.\tRegistrationNo\tColour");
            for (Map.Entry<ParkingSpot, Vehicle> entry : parkedCarMap.entrySet()) {
                int slotNo = entry.getKey().getSpotNumber();
                Vehicle sampleCar = entry.getValue();
                System.out.println(slotNo + "   \t" + sampleCar.getLicensePlate() + "  \t" + sampleCar.getColour());

            }

        }
    }
}
