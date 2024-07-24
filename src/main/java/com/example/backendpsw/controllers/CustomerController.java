package com.example.backendpsw.controllers;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import com.example.backendpsw.entities.Customer;
import com.example.backendpsw.exceptions.InvalidCredentials;
import com.example.backendpsw.exceptions.UserAlreadyExistsException;
import com.example.backendpsw.exceptions.UserNotFoundException;
import com.example.backendpsw.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getUsers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/register")
    public Customer addUser(@RequestBody @Valid Customer user) throws Exception, InvalidCredentials {
        return customerService.registerUser(user);
    }

    @GetMapping("/getUser")
    public Customer getUserU(@RequestParam String username) throws UserNotFoundException {
        return customerService.getUser(username);
    }

    @GetMapping("/getName")
    public List<Customer> getAllUsersWithName(@RequestParam String name){
        return customerService.getUsers(name);
    }

    @GetMapping("/getID")
    public long getUserId(@RequestParam String username) {
        return customerService.getUserId(username);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Customer utente){
        customerService.removeUser(utente);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody Customer utente) throws Exception, InvalidCredentials {
        customerService.updateUser(utente);
    }




}
