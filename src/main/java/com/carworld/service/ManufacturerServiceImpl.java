package com.carworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carworld.dao.ManufacturerRepository;
import com.carworld.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

	ManufacturerRepository manufacturerRepository;
	
	//@Autowired -> Constructor DI through annotation config
	@Autowired
	public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}

	@Override
	public void addManufacturer(Manufacturer manufacturer) throws Exception {
		manufacturerRepository.addManufacturer(manufacturer);
	}

	@Override
	public List<Manufacturer> getAllManufacturer() throws Exception {
		return manufacturerRepository.getAllManufacturer();
	}

	@Override
	public Manufacturer getManufacturer(Long manufacturerId) throws Exception {
		return manufacturerRepository.getManufacturer(manufacturerId);
	}

	@Override
	public void deleteManufacturer(Long manufacturerId) throws Exception {
		manufacturerRepository.deleteManufacturer(manufacturerId);
	}
	
}
