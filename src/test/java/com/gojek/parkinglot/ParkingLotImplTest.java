package com.gojek.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;

import com.gojek.exceptions.ParkingLotNotPresent;
import com.gojek.exceptions.ParkingOverflowException;
import com.gojek.spot.ParkingSpot;
import com.gojek.spot.SpotSize;
import com.gojek.vehicle.Car;

public class ParkingLotImplTest {

	ParkingLotImpl parkingLotImpl;

	@org.junit.Test
	@Before
	public void setUp() {
		parkingLotImpl = ParkingLotImpl.getObject();
	}

	@org.junit.Test
	public void createParkingLot() {
		parkingLotImpl.createParkingLot("3");
		assertEquals(3, parkingLotImpl.getAvailableSpots().size());
		try {
			parkingLotImpl.park(new Car("Black", "1234"));
			
		} catch (ParkingOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParkingLotNotPresent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void park() {
		try {
			parkingLotImpl.createParkingLot("3");
			ParkingSpot spot = parkingLotImpl.park(new Car("BLACK", "KA-01-5674"));
			assertEquals(1, spot.getSpotNumber());

		} catch (ParkingOverflowException e) {
			assertFalse("Exception"+ e.getMessage(),1==2);
		} catch (ParkingLotNotPresent e) {
			assertFalse("Exception"+ e.getMessage(),1==2);
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void unpark() {
		try {
			parkingLotImpl.createParkingLot("3");
			parkingLotImpl.park(new Car("BLACK", "KA-01-5674"));
			Boolean flag = parkingLotImpl.unpark(new ParkingSpot(1, null, SpotSize.Large));
			assertTrue(flag);
		} catch (ParkingOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParkingLotNotPresent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void getParkingSpotForRegistration() {
		try {
			parkingLotImpl.createParkingLot("3");
			parkingLotImpl.park(new Car("BLACK", "KA-01-5674"));
			String spot = parkingLotImpl.getParkingSpotForRegistration("KA-01-5674");
			assertEquals(1, Integer.parseInt(spot));

		} catch (ParkingOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParkingLotNotPresent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@org.junit.Test
	public void getParkingSpotForColour() {
		try {
			parkingLotImpl.createParkingLot("3");
			parkingLotImpl.park(new Car("BLACK", "KA-01-5674"));
			ArrayList<Integer> spots = parkingLotImpl.getParkingSpotForColour("BLACK");
			assertTrue(spots.get(0) == 1);
		} catch (ParkingOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParkingLotNotPresent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void getRegistrationNumberOfColour() {
		try {
			parkingLotImpl.createParkingLot("3");
			parkingLotImpl.park(new Car("BLACK", "KA-01-5674"));
			ArrayList<String> regNo = parkingLotImpl.getRegistrationNumberOfColour("BLACK");
			assertTrue(regNo.get(0).equalsIgnoreCase("KA-01-5674"));

		} catch (ParkingOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParkingLotNotPresent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
