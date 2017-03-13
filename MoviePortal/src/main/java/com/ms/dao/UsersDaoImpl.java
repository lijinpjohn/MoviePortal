package com.ms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.ms.model.User;

public class UsersDaoImpl implements UsersDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public User getUserById(String email) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		User users = (User) session.load(User.class, email);
		tx.commit();
		session.close();
		return users;
	}

}
