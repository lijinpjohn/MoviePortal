package com.ms.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.dao.RentsDao;
import com.ms.model.Rent;
import com.ms.model.User;

@RunWith(MockitoJUnitRunner.class)
public class RentsServicesImplTest {

	// object under test
	private RentsServicesImpl service;

	// mock objects
	@Mock
	private RentsDao mockRentsDao;

	@Before
	public void setup() {

		service = new RentsServicesImpl();
		service.setRentsDao(mockRentsDao);
	}

	@Test
	public void testGetRentsByEmail() throws Exception {

		final String userEmail = "junituser@gm.com";
		List<Rent> rentsList = new ArrayList<>();
		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail(userEmail);
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);
		rentsList.add(rent);
		Mockito.when(mockRentsDao.getRentsByEmail(userEmail)).thenReturn(rentsList);
		List<Rent> actualrents = service.getRentsByEmail(userEmail);

		Assert.assertNotNull("rents must not be NULL", actualrents);
		Assert.assertEquals(rentsList, actualrents);
	}

	@Test
	public void testRentMovie() throws Exception {

		// Registered user
		User user1 = new User();
		user1.setEmail("email1@abc.com");
		user1.setfName("name");
		user1.setlName("john");
		user1.setUsertype("REG");

		// Regular user
		User user2 = new User();
		user2.setEmail("email2@abc.com");
		user2.setfName("name");
		user2.setlName("john");
		user2.setUsertype("NOR");

		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail("email1@abc.com");
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);

		service.rentMovie(rent, user1);

		Assert.assertEquals("80", rent.getAmountPaid());
		Assert.assertEquals(new Date("01/08/2017"), rent.getExpDate());

		service.rentMovie(rent, user2);

		Assert.assertEquals("100", rent.getAmountPaid());
		Assert.assertEquals(new Date("01/04/2017"), rent.getExpDate());
	}

}
