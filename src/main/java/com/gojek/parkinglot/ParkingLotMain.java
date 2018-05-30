package com.gojek.parkinglot;

import com.gojek.com.gojek.model.Commands;
import com.gojek.exceptions.ParkingLotNotPresent;
import com.gojek.exceptions.ParkingOverflowException;
import com.gojek.spot.ParkingSpot;
import com.gojek.spot.SpotSize;
import com.gojek.vehicle.Car;

import java.io.File;
import java.util.Scanner;

public class ParkingLotMain {


    public static void main(String[] args) {
        ParkingLotImpl parkingLot = ParkingLotImpl.getObject();
        Scanner sc;
        int spotNum;
        String command, regNo, colour;
        try {
            if (args.length > 0) {
                String fileName = args[0];
                File f = new File(fileName);
                sc = new Scanner(f);
            } else {
                for (; ; ) {
                    System.out.println("Enter Command");
                    sc = new Scanner(System.in);
                    String line = sc.nextLine().toString();
                    if (line.equalsIgnoreCase("exit")) {
                        break;
                    } else if (line.equalsIgnoreCase("")) {
                        // Skip
                    } else {
                        String[] commandLine = line.split(" ");
                        command = commandLine[0];
                        switch (Commands.getCommand(command)) {
                            case CREATE:
                                boolean result= parkingLot.createParkingLot(commandLine[1]);
                                if (result) {
                                	System.out.println("Created a Parking Lot with"+commandLine[1] +"spots"); 
                                }else {
                                	System.out.println("Failed to create Parking lot");
                                }
                                break;
                            case LEAVE:
                                spotNum = Integer.parseInt(commandLine[1]);
                                Boolean success = parkingLot.unpark(new ParkingSpot(spotNum, null, SpotSize.Large));
                                if (success) {
                                    System.out.println("Spot number " + spotNum + " is free");
                                } else {
                                    System.out.println("Error freeing up " + spotNum);
                                }
                                break;
                            case PARK:
                                regNo = commandLine[1];
                                colour = commandLine[2];
                                try {
                                    ParkingSpot spot = parkingLot.park(new Car(colour, regNo));
                                    System.out.println("Parked in " + spot.getSpotNumber());

                                } catch (ParkingOverflowException e) {
                                    System.out.println(e.getMessage());
                                } catch (ParkingLotNotPresent e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case GET_REG_NUMBERS:
                                colour = commandLine[1];
                                System.out.println(parkingLot.getRegistrationNumberOfColour(colour));
                                break;
                            case GET_SLOT_FOR_COLOUR:
                                colour = commandLine[1];
                                System.out.println(parkingLot.getParkingSpotForColour(colour));
                                break;
                            case SLOT_REG:
                                regNo = commandLine[1];
                                System.out.println(parkingLot.getParkingSpotForRegistration(regNo));
                                break;
                            case STATUS:
                                parkingLot.printStatus();
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Oops! Error in reading the input from console.");
            e.printStackTrace();
        }
    }

}

