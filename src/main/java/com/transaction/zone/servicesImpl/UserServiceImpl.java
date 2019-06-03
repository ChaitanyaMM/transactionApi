package com.transaction.zone.servicesImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.zone.Entities.User;
import com.transaction.zone.Repository.UserRepository;
import com.transaction.zone.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository ;

	@Override
	public User createuser(@Valid User user) {
 		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
 		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long userId) {
 		return userRepository.findById(userId);
	}

	@Override
	public User updateuser(Long userId, User user) {
		Optional<User> fetchUser =userRepository.findById(userId);
		return userRepository.updateUser(fetchUser);
	}

	@Override
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}

	 

}
