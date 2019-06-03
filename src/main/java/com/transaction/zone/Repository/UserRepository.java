package com.transaction.zone.Repository;


import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.zone.Entities.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{

	User updateUser(Optional<User> fetchUser);

	 
}
