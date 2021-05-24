package com.example.BackEndService;

import com.example.BackEndService.Service.IServiceService;
import com.example.BackEndService.Service.IUserService;
import com.example.BackEndService.dao.*;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BackEndServiceApplication implements CommandLineRunner {
	@Autowired
	private IUserService userService;
	@Autowired
	private IServiceService serviceService;



	public static void main(String[] args) {
		SpringApplication.run(BackEndServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {

		userService.cleanDataBase();
		userService.save(new Role("ADMIN"));
		userService.save(new Role("CLIENT"));
		userService.save(new Role("SERVICEPROVIDER"));





		Role roleAdmin = userService.getRoleByName("ADMIN");
		Role roleClient = userService.getRoleByName("CLIENT");
		User admin1 = new User("admin1", "admin1", Arrays.asList(roleAdmin));
		User client1 = new User("client1", "client1", Arrays.asList(roleClient));




		userService.save(admin1);
		userService.save(client1);
	}
}
