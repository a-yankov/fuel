package com.example.fuel_app.services;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.models.VehicleReadDto;
import com.example.fuel_app.repositories.UserRepository;
import com.example.fuel_app.repositories.VehicleRepository;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    UserRepository UserRepository;

    public void saveVehicle(Vehicle vehicle){
        this.vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findOneById(long id){
        return this.vehicleRepository.findById(id);
    }

    public Iterable<Vehicle> findAll(){
        return this.vehicleRepository.findAll();
    }

    public List<Vehicle> findAllByUserId(User user) {
        return this.vehicleRepository.findAllByUserIs(user);
    }

	public List<VehicleReadDto> getVechiclesReadDtoByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
		User user = this.UserRepository.findOneByUsername(username);

		List<Vehicle> vehicles = user.getVehicles();
		if (vehicles == null)
			return Collections.emptyList();

		TypeMap<Vehicle, VehicleReadDto> typeMap = modelMapper.createTypeMap(Vehicle.class, VehicleReadDto.class);
		typeMap.addMapping(Vehicle -> Vehicle.getModel().getName(), VehicleReadDto::setModel);
		typeMap.addMapping(Vehicle -> Vehicle.getModel().getMake().getName(), VehicleReadDto::setMake);

		List<VehicleReadDto> list = new ArrayList<>(vehicles.size());
		for (Vehicle v : vehicles) {

			VehicleReadDto dto = new VehicleReadDto();

			modelMapper.map(v, dto);
			list.add(dto);
		}

		return list;
	}
}
