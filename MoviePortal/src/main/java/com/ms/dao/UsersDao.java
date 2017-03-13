package com.ms.dao;

import com.ms.model.User;

public interface UsersDao {

	User getUserById(String email) throws Exception;

}
