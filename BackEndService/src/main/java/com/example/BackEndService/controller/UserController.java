package com.example.BackEndService.controller;

import com.example.BackEndService.Service.IClientService;
import com.example.BackEndService.Service.IUserService;
import com.example.BackEndService.dao.*;
import com.example.BackEndService.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    IClientService clientService;
    @Autowired
    IUserService userService;




    @PostMapping("/register")
    public ResponseEntity<User> AddClient(@RequestBody User user){
        try{
          Role userRole = userService.getRoleByName("CLIENT");
            String password = bCryptPasswordEncoder.encode(user.getPassword());

            User _user= userRepository.save(new User(user.getUsername(),password, Arrays.asList(userRole)));
            Client client = new Client(_user.getId(),"Admin");
           clientService.saveClient(client);
            System.out.println(user.getUsername());
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable(name="id") Long id,@RequestBody User user){

        User myUser = new User();
        myUser = user;
        if(user.getUsername()!=null ){
            myUser.setUsername(user.getUsername());
        }
        if(user.getPassword()!=null ){
            myUser.setPassword(user.getPassword());
        }
       System.out.println(myUser.getRoles());

            return new ResponseEntity<>(userRepository.save(myUser),HttpStatus.OK);

    }
    @GetMapping("/list")
    public ResponseEntity<List<User> > GetAllUsers(@RequestParam(required = false) String Username){
        try{
            List<User> users = new ArrayList<User>();
            List<User> userSpec = new ArrayList<>();

            if(Username == null){
             users= userRepository.findAll();
             for(User usr : users){
                 Role role = new Role();
                 role=usr.getRoles().get(0);
                 if(role.getRole()=="ADMIN"){
                     Admin admin = new Admin();
                     admin = adminRepository.getOne(usr.getId());
                     usr.setAdmin(admin);
                 }else if(role.getRole()=="CLIENT"){
                     Client client = new Client();
                     client = clientRepository.getOne(usr.getId());
                     usr.setClient(client);
                 }else if(role.getRole()=="SERVICEPROVIDER"){
                     ServiceProvider serviceProvider = new ServiceProvider();
                     serviceProvider = serviceProviderRepository.getOne(usr.getId());
                     usr.setServiceProvider(serviceProvider);
                 }

             }

            }
            else{
                userRepository.findByUsername(Username);
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") long id){
        try{
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/AddAmin")
    public ResponseEntity<User> addAdmin(@RequestBody User user){
        try{
            List<Role> userRole = roleRepository.findByRole("ADMIN");
            String password = bCryptPasswordEncoder.encode(user.getPassword());

            User _user= userRepository.save(new User(user.getUsername(),password,userRole));
            Admin admin = new Admin(_user.getId(),"Admin");
            adminRepository.save(admin);
            System.out.println(user.getUsername());
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/serviceprovider")
    public ResponseEntity<User> AddProvider(@RequestBody User user){
        try{
            List<Role> userRole = roleRepository.findByRole("SERVICEPROVIDER");
            String password = bCryptPasswordEncoder.encode(user.getPassword());

            User _user= userRepository.save(new User(user.getUsername(),password,userRole));
            ServiceProvider serviceProvider = new ServiceProvider(_user.getId(),"SERVICE provider");
            serviceProviderRepository.save(serviceProvider);
            System.out.println(user.getUsername());
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/search")
    public User getUserByUsername(@RequestParam(required = true) String username){

        System.out.println(username);
        User user = userService.getUserByUserName(username);
        if(user != null){
            System.out.println(user.getUsername());
        }
        return  user;
    }





}
