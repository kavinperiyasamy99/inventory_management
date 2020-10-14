package com.inventory.repository;

import com.inventory.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {
	public UserDetails findByUserName(String userName);
	public List<UserDetails> findAll();
}
