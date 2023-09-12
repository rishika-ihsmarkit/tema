package com.osttra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.osttra.entity.User;
import com.osttra.entity.UserGroup;
import com.osttra.repository.UserGroupRepository;
import com.osttra.repository.UserRepository;
import com.osttra.service.CustomUserDetailsService;

@SpringBootApplication
public class TemaApplication {
	
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserGroupRepository userGroupRepository;

	public static void main(String[] args) {
		SpringApplication.run(TemaApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		Long userId = 1L; 
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user != null) {
//            System.out.println("User Name: " + user.getName());
//        } else {
//            System.out.println("User not found.");
//        }
//        
//        if (user != null) {
//            Set<UserGroup> userGroups = user.getUserGroups();
//            
//            for( UserGroup hehe : userGroups)
//            System.out.println(hehe);
//            
//        } else {
//            System.out.println("User not found.");
//        }	
//		
//	}
//	


}
