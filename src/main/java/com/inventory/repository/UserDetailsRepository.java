package com.inventory.repository;

import com.inventory.entity.UserDetails;
import com.inventory.enums.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {
	@Query("select u from UserDetails u where u.userName=:userName and u.status=:status")
	public UserDetails findByUserName(String userName,Status status);
	public UserDetails findByUserId(Long userId);
	@Query("select u from UserDetails u where u.status=:status")
	public List<UserDetails> findAllActiveUsers(Status status);
}
