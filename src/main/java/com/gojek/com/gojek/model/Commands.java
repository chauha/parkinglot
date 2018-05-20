package com.gojek.com.gojek.model;


public enum Commands {

    CREATE, PARK, LEAVE, STATUS, GET_REG_NUMBERS, GET_SLOT_FOR_COLOUR, SLOT_REG;

    public static Commands getCommand(String comm) {
        if (Constants.CREATE.equals(comm))
            return CREATE;
        else if (Constants.PARK.equals(comm))
            return PARK;
        else if (Constants.LEAVE.equals(comm))
            return LEAVE;
        else if (Constants.STATUS.equals(comm))
            return STATUS;
        else if (Constants.GET_REG_NUMBERS.equals(comm))
            return GET_REG_NUMBERS;
        else if (Constants.GET_SLOT_FOR_COLOUR.equals(comm))
            return GET_SLOT_FOR_COLOUR;
        else if (Constants.GET_SLOT_FOR_REG.equals(comm))
            return SLOT_REG;
        else
            return null;

    }
}