package com.vehicle.service;

import java.util.List;

import com.vehicle.entities.VehicleRegistrationDetails;

public interface VehicleRegistrationDetailsService {
	public void addVehicleRegistrationDetails(VehicleRegistrationDetails vehicleRegistrationDetail);
    public List<VehicleRegistrationDetails> listVehicleRegistrationDetails();
    public void removeVehicleRegistrationDetails(Integer id);
    public void updateEmployee(VehicleRegistrationDetails vehicleRegistrationDetail);
    public VehicleRegistrationDetails findVehicleRegistrationDetailsById(int id);
}
