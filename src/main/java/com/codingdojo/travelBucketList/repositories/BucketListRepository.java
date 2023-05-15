package com.codingdojo.travelBucketList.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.travelBucketList.models.BucketList;
import com.codingdojo.travelBucketList.models.User;

@Repository
public interface BucketListRepository extends CrudRepository<BucketList, Long> {
	List<BucketList> findAllByUser(User user);
	Optional<BucketList> findByDestinationIdAndUserId(Long desId, Long userId);
	List<BucketList> findAllByDestinationId(Long id);
}
