package com.ms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.model.Rent;

@RunWith(MockitoJUnitRunner.class)
public class RentsDaoImplTest {

	// object under test
	private RentsDaoImpl dao;

	// mock objects
	@Mock
	SessionFactory mockSessionFactory;
	@Mock
	Session mockSession;
	@Mock
	Transaction tx;
	@Mock
	Criteria mockCriteria;
	@Mock
	Query mockQuery;

	@Before
	public void setup() {

		dao = new RentsDaoImpl();
		// dao.session = mockSession;
		dao.sessionFactory = mockSessionFactory;
	}

	@Test
	public void testGetRentsByEmail() throws Exception {
		final String email = "email@abc.com";
		List<Rent> rentsList = new ArrayList<>();
		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail(email);
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);
		rentsList.add(rent);

		Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.beginTransaction()).thenReturn(tx);
		Mockito.when(mockSession.createQuery("from rents where sysdate > expdate ")).thenReturn(mockQuery);
		Mockito.when(mockQuery.list()).thenReturn(rentsList);
		List<Rent> actualRentList = dao.getRentsByEmail(email);

		Assert.assertNotNull(actualRentList);
		Assert.assertEquals(rent, actualRentList.get(0));
	}

	@Test
	public void testGetUserById() throws Exception {
		final String email = "email@abc.com";
		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail(email);
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);

		Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.beginTransaction()).thenReturn(tx);
		boolean status = dao.addRents(rent);

		Assert.assertFalse(status);
	}
}
