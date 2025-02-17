package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.entities.User;
import com.exam.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("inside loadUserByUsername method");
		System.out.println("Checking username : " + username);
		User user = userRepository.findByUserName(username);
		if(user == null)
		{
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("No User Found");
		}
		else
		{
			return user;
		}
	}

}
