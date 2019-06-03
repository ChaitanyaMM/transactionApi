package com.transaction.zone.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.transaction.zone.Entities.User;

public interface UserService {
	
	User createuser(@Valid User user);
	
	User updateuser(Long userId,User user);
	
	List<User> getAllUsers();
	
	Optional<User> findById (Long userId);

	void delete(Long Id);
	
    
	

}
