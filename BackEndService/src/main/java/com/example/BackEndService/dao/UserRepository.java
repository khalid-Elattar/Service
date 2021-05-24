package com.example.BackEndService.dao;

import com.example.BackEndService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);



}
