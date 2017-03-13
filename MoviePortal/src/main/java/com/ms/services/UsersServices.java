package com.ms.services;

import com.ms.model.User;

public interface UsersServices {

	User getUserById(String id) throws Exception;
}
