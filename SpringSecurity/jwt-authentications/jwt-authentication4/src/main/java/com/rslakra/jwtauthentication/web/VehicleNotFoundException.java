package com.rslakra.jwtauthentication.web;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(Long vehicleId ) {
        super("Vehicle: " +vehicleId +" not found.");
    }
}
