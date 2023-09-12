package com.osttra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.osttra.entity.User;
import com.osttra.entity.UserGroup;
import com.osttra.repository.UserGroupRepository;
import com.osttra.repository.UserRepository;


public class CustomUserGroupDetailsService {
	
	@Autowired
	UserGroupRepository userGroupRepository;

	public UserGroup saveUserGroup(UserGroup userGroup) {
		return userGroupRepository.save(userGroup);
	}
	
	public List<UserGroup> getAllUserGroups() {
        return userGroupRepository.findAll();
    }
	
	public UserGroup getUserGroupById(Long userId) {
        return userGroupRepository.findById(userId).orElse(null);
    }
}
