package com.codingdojo.travelBucketList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.travelBucketList.models.Destination;
import com.codingdojo.travelBucketList.repositories.DestinationRepository;

@Service
public class DestinationService {
	
	@Autowired
	private DestinationRepository desRepo;
	
	public Destination newDestination(Destination destination, BindingResult result) {
		Optional<Destination> potentialDestination = desRepo.findByName(destination.getName());
		if(result.hasErrors()) {
			return null;
		}
		if(potentialDestination.isPresent()) {
			result.rejectValue("name", "Matches", "This destination is already listed");
			return null;
		}
		return desRepo.save(destination);
	}
	
	public List<Destination> findAll() {
		return desRepo.findAll();
	}
	
	public Destination findById(Long id) {
		Optional<Destination> potentialDestination = desRepo.findById(id);
		if(potentialDestination.isPresent()) {
			return potentialDestination.get();
		} else {
			return null;
		}
	}
	
	public Destination saveDestination(Destination destination) {
		return desRepo.save(destination);
	}
	
	public void delete(Long id) {
		desRepo.deleteById(id);
	}
	
	public List<Destination> top3() {
		return desRepo.findTop3();
	}

}
