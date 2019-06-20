package com.example.fuel_app.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	private int startOdometer;
	private int odometer;
	private String title;
	private String description;
	private String regNumber;
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name="model_id")
	private VehicleModel model;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "vehicle")
	private List<Charge> charges;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public int getStartOdometer() {
		return startOdometer;
	}

	public void setStartOdometer(int startOdometer) {
		this.startOdometer = startOdometer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public VehicleModel getModel() {
		return model;
	}

	public void setModel(VehicleModel model) {
		this.model = model;
	}
}
