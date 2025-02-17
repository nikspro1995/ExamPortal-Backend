package com.exam.repo;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserName(String username);
}
