package com.ms.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ms.dao.UsersDao;
import com.ms.model.User;

public class UsersServicesImpl implements UsersServices {

	private UsersDao usersDao;

	@Override
	public User getUserById(String email) throws Exception {
		return usersDao.getUserById(email);
	}

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

}
