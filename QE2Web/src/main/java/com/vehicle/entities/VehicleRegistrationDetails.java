package com.vehicle.entities;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "VEHICLE_DETAILS")

//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="vehicleregCache")
public class VehicleRegistrationDetails {
	public VehicleRegistrationDetails() {
		}
	
	@Id
	@GeneratedValue
	@Column(name = "UID", nullable = false)
	private int uid;
	
	@Column(name = "VRN", nullable = false)
	private String vrn;
	
	@Column(name = "VEHICLE_TYPE", nullable = false)
	private String vehicleType;

	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getVrn() {
		return vrn;
	}

	public void setVrn(String vrn) {
		this.vrn = vrn;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
