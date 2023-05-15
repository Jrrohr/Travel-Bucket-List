package com.codingdojo.travelBucketList.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.travelBucketList.models.Destination;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
	List<Destination> findAll();
	Optional<Destination> findByName(String name);
	
	@Query(value="SELECT * FROM destinations LIMIT 3;", nativeQuery = true)
	List<Destination> findTop3();
}
