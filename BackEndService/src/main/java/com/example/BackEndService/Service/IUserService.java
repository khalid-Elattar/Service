package com.example.BackEndService.Service;

import java.util.List;

import com.example.BackEndService.model.Role;
import com.example.BackEndService.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface IUserService extends UserDetailsService{
	User save(User user);
	void save(Role role);
	List<User> getAllUsers();
	List<Role> getAllRoles();
	Role getRoleByName(String role);
	void cleanDataBase();
	User getUserByUserName(String Username);
}
