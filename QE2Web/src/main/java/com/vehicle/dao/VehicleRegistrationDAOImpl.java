package com.vehicle.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.entities.VehicleRegistrationDetails;


@Repository
public class VehicleRegistrationDAOImpl implements VehicleRegistrationDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	
		
	public void addVehicleRegistrationDetails(
			VehicleRegistrationDetails vehicleRegistrationDetail) {
		 sessionFactory.getCurrentSession().save(vehicleRegistrationDetail);

	}
	
	@Transactional
	public List<VehicleRegistrationDetails> listVehicleRegistrationDetails() {	
		 //System.out.println("m in dao");
		 return sessionFactory.getCurrentSession().createCriteria(VehicleRegistrationDetails.class).list();
		// return sessionFactory.getCurrentSession().createQuery("FROM VehicleRegistrationDetails").setCacheable(true).list();
	}

	public void removeVehicleRegistrationDetails(Integer id) {
		
		VehicleRegistrationDetails vehicleRegistrationDetails = (VehicleRegistrationDetails) sessionFactory.getCurrentSession().load(
				VehicleRegistrationDetails.class, id);
		    if (null != vehicleRegistrationDetails) {
		        sessionFactory.getCurrentSession().delete(vehicleRegistrationDetails);
		    }
	}

	public void updateEmployee(
			VehicleRegistrationDetails vehicleRegistrationDetail) {		
		sessionFactory.getCurrentSession().update(vehicleRegistrationDetail);
	}

		
	public VehicleRegistrationDetails findVehicleRegistrationDetailsById(int id) {
		return (VehicleRegistrationDetails) sessionFactory.getCurrentSession().get(VehicleRegistrationDetails.class, id);
	}
}



