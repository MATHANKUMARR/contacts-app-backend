package com.phonedirectory.app.controllers;


import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.phonedirectory.app.config.JWTTokenUtil;
import com.phonedirectory.app.model.Address;
import com.phonedirectory.app.model.Contact;
import com.phonedirectory.app.model.Department;
import com.phonedirectory.app.model.JWTRequest;
import com.phonedirectory.app.model.JWTResponse;
import com.phonedirectory.app.model.UserDTO;
import com.phonedirectory.app.service.AddressService;
import com.phonedirectory.app.service.ContactService;
import com.phonedirectory.app.service.DepartmentService;
import com.phonedirectory.app.service.JWTUserDetailsService;


@RestController
// Authenticates the user with the given username and password.
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class JWTAuthenticationController {

	Logger logger;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private DepartmentService departmentService;

	
	/** 
	 * @param authenticationRequest
	 * @return ResponseEntity<?>
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<JWTResponse> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest){

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JWTResponse(token));
		} catch (Exception e) {
			logger.log(null, e);
		}
		return null;
		
	}
	
	
	/** 
	 * @param user
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/register")
	public ResponseEntity<com.phonedirectory.app.model.UserDetails> saveUser(@RequestBody UserDTO user) {
		
		try{
			com.phonedirectory.app.model.UserDetails temp = userDetailsService.save(user);

			// Temp
			Contact con = new Contact();
			con.setUserId(temp.getUserId());
			contactService.addContact(con);

			Address add = new Address();
			add.setUserId(temp.getUserId());
			addressService.addAddress(add);

			Department dep = new Department();
			dep.setUserId(temp.getUserId());
			departmentService.addDepartment(dep);

			return ResponseEntity.ok(temp);

		}
		catch(NullPointerException ne){
		logger.info(ne);
	}
		return null;
	}

	
	/** 
	 * @param username
	 * @param password
	 */
	// Authenticate using the given username and password.
	private void authenticate(String username, String password) throws UsernameNotFoundException{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException | BadCredentialsException e) {
				logger.info(e);
			
		} 
	}
	
}
