package com.softgv.cda.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.UserService;
import com.softgv.cda.util.AuthUser;
import com.softgv.cda.util.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser) {
		Optional<User> optional = userDao.findByUsernameAndPassword(authUser.getUsername(), authUser.getPassword());
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid Credentials... Invalid Username or Password...")
					.build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Created Successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> saveUser(User user) {
//		validation not yet done...
		user.setStatus(UserStatus.IN_ACTIVE);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Saved Successfully...").body(userDao.saveUser(user)).build());
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Users Found Successfully...").body(userDao.findAllUsers()).build());
	}

}
