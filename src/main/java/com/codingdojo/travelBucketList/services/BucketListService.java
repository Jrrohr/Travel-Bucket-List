package com.codingdojo.travelBucketList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.travelBucketList.models.BucketList;
import com.codingdojo.travelBucketList.models.User;
import com.codingdojo.travelBucketList.repositories.BucketListRepository;

@Service
public class BucketListService {
	
	@Autowired
	private BucketListRepository blRepo;
	
	public BucketList saveBucketList(BucketList list) {
		return blRepo.save(list);
	}
	
	public List<BucketList> findByUser(User user) {
		return blRepo.findAllByUser(user);
	}
	
	public BucketList findByDesAndUserId(Long desId, Long userId) {
		Optional<BucketList> bucketList = blRepo.findByDestinationIdAndUserId(desId, userId);
		if(!bucketList.isPresent()) {
			return null;
		}
		return bucketList.get();
	}
	
	public List<BucketList> findAllByDesId(Long id) {
		return blRepo.findAllByDestinationId(id);
	}
	
	public void delete(Long id) {
		blRepo.deleteById(id);
	}
	
	public BucketList findById(Long id) {
		Optional<BucketList> potentialBL = blRepo.findById(id);
		if(potentialBL.isPresent()) {
			return potentialBL.get();
		} else {
			return null;
		}
	}

}
