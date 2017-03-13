package com.ms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.ms.model.Rent;

public class RentsDaoImpl implements RentsDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public List<Rent> getRentsByEmail(String email) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from rents where sysdate > expdate ");
		List<Rent> rentsList = query.list();
		tx.commit();
		session.close();
		return rentsList;
	}

	@Override
	public boolean addRents(Rent rents) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(rents);
		tx.commit();
		session.close();
		return false;
	}

}
