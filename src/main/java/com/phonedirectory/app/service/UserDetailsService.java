package com.phonedirectory.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonedirectory.app.model.Contact;
import com.phonedirectory.app.model.UserDetails;
import com.phonedirectory.app.repository.AddressRepository;
import com.phonedirectory.app.repository.ContactRepository;
import com.phonedirectory.app.repository.DepartmentRepository;
import com.phonedirectory.app.repository.UserDetailsRepository;

@Service
public class UserDetailsService {
    
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private ContactService contactService;
    private AddressRepository addressRepository;
    private DepartmentRepository departmentRepository;
    public List<UserDetails> getAllUserDetails(){
        List<UserDetails> users= new ArrayList<UserDetails>();
        userDetailsRepository.findAll().forEach(users:: add);        
        return users;
    }

    public Optional<UserDetails> getUser(int userId){
       return userDetailsRepository.findById(userId);
    }

    public void addUser(UserDetails userdetails){
           userDetailsRepository.save(userdetails);
            
            // Temp
            // Contact con = new Contact();
            // con.setUserId(temp.getUserId());
            // con.setCountryCode("");
            // con.setPrimaryMobileNumber("");
            // con.setSecondaryMobileNumber("");
            // contactService.addContact(con);

    }

    public void updateUserDetails(UserDetails userDetails ,int userId){ 
        userDetailsRepository.save(userDetails);
    }

    public void deleteUserDetails(int userId){
          userDetailsRepository.deleteById(userId);
    }
    
    
}
