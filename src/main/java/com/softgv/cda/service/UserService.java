package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;

import com.softgv.cda.util.AuthUser;

public interface UserService {

	ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser);
	
}
