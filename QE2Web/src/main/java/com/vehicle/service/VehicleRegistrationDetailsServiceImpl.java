package com.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.dao.VehicleRegistrationDAO;
import com.vehicle.entities.VehicleRegistrationDetails;

@Service
public class VehicleRegistrationDetailsServiceImpl implements
		VehicleRegistrationDetailsService {
	
	@Autowired
	private VehicleRegistrationDAO vehicleRegistrationDAO;
	
	@Transactional
	public void addVehicleRegistrationDetails(
			VehicleRegistrationDetails vehicleRegistrationDetail) {
		vehicleRegistrationDAO.addVehicleRegistrationDetails(vehicleRegistrationDetail);

	}
	@Transactional
	public List<VehicleRegistrationDetails> listVehicleRegistrationDetails() {
		
		 return vehicleRegistrationDAO.listVehicleRegistrationDetails();
	}
	@Transactional
	public void removeVehicleRegistrationDetails(Integer id) {
		vehicleRegistrationDAO.removeVehicleRegistrationDetails(id);

	}
	
	@Transactional
	public void updateEmployee(
			VehicleRegistrationDetails vehicleRegistrationDetail) {	
		vehicleRegistrationDAO.updateEmployee(vehicleRegistrationDetail);
	}
	@Transactional
	public VehicleRegistrationDetails findVehicleRegistrationDetailsById(int id) {
		return vehicleRegistrationDAO.findVehicleRegistrationDetailsById(id);
	}

}


