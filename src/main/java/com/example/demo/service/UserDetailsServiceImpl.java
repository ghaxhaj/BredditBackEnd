package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import lombok.AllArgsConstructor;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import static java.util.Collections.singletonList;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No User Found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), 
				user.isEnabled(),
				/*accountNonExpired*/ true,
				/*credentialsNonExpired*/ true,
				/*accountNonLocked*/ true, 
				getAuthorities("USER")); 
		//Wrapper object with the User Credentials. Provided by SpringFrameworks and Implements UserDetailsInterface. Here we are mapping the user's details to this User object
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role){
		return singletonList(new SimpleGrantedAuthority(role));
	}
	

}
