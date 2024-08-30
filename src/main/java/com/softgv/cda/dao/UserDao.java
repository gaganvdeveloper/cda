package com.softgv.cda.dao;

import java.util.Optional;

import com.softgv.cda.entity.User;

public interface UserDao {

	Optional<User> findByUsernameAndPassword(String username, String password);

}
