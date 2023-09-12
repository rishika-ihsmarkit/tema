package com.osttra.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osttra.entity.User;
import com.osttra.entity.UserGroup;
import com.osttra.repository.UserRepository;
import com.osttra.to.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optional = userRepository.findById(username);
		
		User user = null;
		
		if(optional.isPresent()) {
			
			user = optional.get();
		}
		
		return new CustomUserDetails(user);
	}
	

	public User saveUser(User user) {
        return userRepository.save(user);
    }
	
	public List<User> getAllUser() {
        return userRepository.findAll();
    }
	
	public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
	
}