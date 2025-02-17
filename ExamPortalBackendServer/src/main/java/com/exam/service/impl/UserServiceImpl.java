package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = userRepository.findByUserName(user.getUsername());
		if(local!=null)
		{
			throw new Exception("User Already Exists");
		}
		else {
			for (UserRole userRole : userRoles) {
				roleRepository.save(userRole.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			return userRepository.save(user);
		}
		
	}

}
