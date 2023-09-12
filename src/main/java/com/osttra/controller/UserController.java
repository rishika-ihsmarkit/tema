package com.osttra.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.osttra.entity.User;
import com.osttra.entity.UserGroup;
import com.osttra.repository.UserRepository;
import com.osttra.service.CustomUserDetailsService;
import com.osttra.service.CustomUserGroupDetailsService;

@RestController
public class UserController {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@GetMapping("/home")
	public String home() {
		System.out.println("dhsif");
		return "This is Home Page";
	}
	
	
	
	@GetMapping("/registring_page")
	public String registrationPage() {
		
		return "This isRegistration Page";
	}
	
	@GetMapping("/welcomePage")
	public String welcomePage() {
		
		return "This is Welcome Page";
	}
	
	
	@RestController
	@RequestMapping("/users")
	public class StudentController {

	    private final CustomUserDetailsService customUserDetailsService;

	    @Autowired
	    public StudentController(CustomUserDetailsService customUserDetailsService) {
	        this.customUserDetailsService = customUserDetailsService;
	    }

	    @PostMapping("/add")
	    public User addUser(@RequestBody User user) {
	        return customUserDetailsService.saveUser(user);
	    }
	    
	    @GetMapping(value="/all", produces = "application/json")
	    @ResponseBody
	    public List<User> getAllUser() {

	    	List<User> users = customUserDetailsService.getAllUser();
	    	for(User user:users) {
	    		System.out.println(user.getId());
	    	}
	    	return users;
	    }
	    
	    @GetMapping("/{userId}/groups")
	    public Set<UserGroup> getUserGroups(@PathVariable Long userId) {
	    	
	        User user = customUserDetailsService.getUserById(userId);

	        if (user == null) {
	            System.out.println("null");
	        }

	        Set<UserGroup> userGroups = user.getUserGroups();
	        
	        return userGroups;
	    }
	}

	@RestController
	@RequestMapping("/usergroups")
	public class CourseController {

	    private final CustomUserGroupDetailsService customUserGroupDetailsService;

	    @Autowired
	    public CourseController(CustomUserGroupDetailsService customUserGroupDetailsService) {
	        this.customUserGroupDetailsService = customUserGroupDetailsService;
	    }

	    @PostMapping("/add")
	    public UserGroup addUserGroup(@RequestBody UserGroup userGroup) {
	        return customUserGroupDetailsService.saveUserGroup(userGroup);
	    }
	    
	    @GetMapping("/all")
	    public List<UserGroup> getAllUserGroups() {
	        return customUserGroupDetailsService.getAllUserGroups();
	    }
	    
	    @GetMapping("/{userGroupId}/users")
	    public Set<User> getUsers(@PathVariable Long userGroupId) {

	        UserGroup userGroup = customUserGroupDetailsService.getUserGroupById(userGroupId);

	        if (userGroup == null) {
	            System.out.println("null");
	        }

	        Set<User> user = userGroup.getUsers();
	        
	        return user;
	    }
	    
	    
	}



		
}